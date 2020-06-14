package ModelosTAQ;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.Vistas.OrdenCliente;

import java.io.IOException;

public class GenerarPdf
{
    CuentaDAO dao = new CuentaDAO();
    EmpleadoDAO objEm;
    OrdenCliente orden;

    public static final String Icono = "src/sample/Imagenes/Icono_Taqueria.png";

    //films By Category
    public void GenerarTicket(int id_empleado, int id_mesa, int id_taqueria){


        dao.setId_empleado(id_empleado);
        dao.setId_mesa(id_mesa);
        dao.setId_taqueria(id_taqueria);

        objEm = new EmpleadoDAO();
        objEm.setId_empleado(id_empleado);
        objEm.DatosTicket();
        objEm.DatosTAQ();

        ObservableList<CuentaDAO> Ticket = dao.GenerarTicket();
        dao.GenerarTotal();

        try {
            //Initialize PDF writer
            PdfWriter writer = new PdfWriter(OrdenCliente.DEST);

            //Initialize PDF document
            PdfDocument pdf = new PdfDocument(writer);

            // Initialize document
            Document document = new Document(pdf, PageSize.A4.rotate());
            document.setTextAlignment(TextAlignment.CENTER);
            document.setMargins(20, 20, 20, 20);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            PdfFont font1 = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            PdfFont font2 = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);

            //DottedLine dottedLine = new DottedLine(1);
            DashedLine dashedLine = new DashedLine(1);

            Image icono = new Image(ImageDataFactory.create(Icono));
            icono.setWidth(100).setHeight(150);

            Text text1 = new Text("Ticket de Pago ").setFont(font1).setFontSize(18).setBold();
            Paragraph p3 = new Paragraph().add(text1).add("\n\n");
            p3.setTextAlignment(TextAlignment.CENTER);

            Text text2 = new Text("Teléfono : " + objEm.telefonoTAQ ).setFont(font2).setFontSize(14);
            Text text3 = new Text("Mesa : " + id_mesa).setFont(font2).setFontSize(14);
            Text text4 = new Text("Taquería : " + objEm.taqueria ).setFont(font2).setFontSize(14);
            Text text5 = new Text("Direción : " + objEm.direccion ).setFont(font2).setFontSize(14);
            Text text6 = new Text("Nombre del Mesero : " + objEm.nombreEmp ).setFont(font2).setFontSize(14);
            Paragraph p2 = new Paragraph().add(text2).add("\n").add(text3).add("\n").add(text4).add("\n").add(text5).add("\n").add(text6);

            float propina = (float) (dao.total * 0.10);
            Text text7 = new Text("Propina: $ " + propina + "pesos").setFont(font1).setFontSize(18);
            Text text8 = new Text("Total : $ " + dao.total + "pesos\n").setFont(font1).setFontSize(18);
            Paragraph p4 = new Paragraph().add(text8).add(text7);

            document.add(new Paragraph("\n\n")).add(p2).add(new LineSeparator(dashedLine)).add(p3);

            Table table = new Table(UnitValue.createPercentArray(new float[]{3, 3, 8})) //Crea una tabla, los valores son el ancho de sus 7 columnas
                    .useAllAvailableWidth();

            process(table, null, bold, true); //Imprime los encabezados
            for (CuentaDAO cu : Ticket) {
                process(table, cu, font, false);
            }

            document.add(table);
            document.add(new Paragraph("\n\n")).add(p4);
            //Close document
            document.close(); //genera el archivo pdf

        } catch (IOException e) {
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Imprimiendo Ticket");
        alert.setHeaderText(null);
        alert.setContentText("Se ah creado el TICKET");
        alert.showAndWait();
    }

    public void process(Table table, CuentaDAO cu, PdfFont font, boolean isHeader) {

        if (isHeader)
        { //Si es encabezado agrega celdas
            table.addHeaderCell(new Cell().add(new Paragraph("Cantidad").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre").setFont(font)));
            table.addHeaderCell(new Cell().add(new Paragraph("Total").setFont(font)));

        }
        else
        {   //Agrega
            table.addCell(new Cell().add(new Paragraph("" + cu.getCantidad()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph("" + cu.getNombre()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph("" + cu.getPrecio_final()).setFont(font)));
        }
    }
}