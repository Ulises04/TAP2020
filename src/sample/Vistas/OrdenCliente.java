package sample.Vistas;

import ModelosTAQ.CuentaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ModelosTAQ.GenerarPdf;

import java.io.File;

public class OrdenCliente extends Stage
{
    public static String DEST = "results/Ticket.pdf";

    Scene escena;
    VBox vBoxTabla;
    HBox hBoxprincipal;
    GridPane gridPanePanel;
    TableView<CuentaDAO> tbvCuenta;
    Button btnOrdenar, btnTicket;
    CuentaDAO objCu;
    int empleado, taqueria, mesa;
    TextField txtTaqueria, txtEmpleado, txtComida, txtMesa, txtPrecio, txtCantidad, txtPrecioFinal, txtNombre, txtDescripcion;
    Label lblTaqueria, lblEmpelado, lblMesa, lblPrecio, lblCantidad, lblPrecioFinal, lblNombre, lblDescripcion, lblNumero;

    GenerarPdf pdf = new GenerarPdf();

    ObservableList<String> producto = FXCollections.observableArrayList("Comida","Bebida");
    ComboBox<String> comboBoxProducto;

    public OrdenCliente(int mesa, int empleado, int taqueria)
    {
        this.mesa = mesa;
        this.empleado = empleado;
        this.taqueria = taqueria;
        objCu = new CuentaDAO();
        CrearGUI();
        this.setTitle("Orden de la mesa #"+mesa+"  :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {
        vBoxTabla = new VBox();
        hBoxprincipal = new HBox();
        gridPanePanel = new GridPane();

        txtTaqueria = new TextField();
        txtEmpleado = new TextField();
        txtComida = new TextField();
        txtMesa = new TextField();
        txtPrecio = new TextField();
        txtPrecioFinal = new TextField();
        txtCantidad = new TextField();
        txtNombre = new TextField();
        txtDescripcion = new TextField();

        lblTaqueria = new Label("Taquería: ");
        lblEmpelado = new Label("Empleado: ");
        lblMesa = new Label("Mesa: ");
        lblPrecio = new Label("Precio: ");
        lblCantidad = new Label("Cantidad: ");
        lblPrecioFinal = new Label("Precio Final: ");
        lblNombre = new Label("Nombre: ");
        lblDescripcion = new Label("Descipción: ");
        lblNumero = new Label("0");

        btnOrdenar = new Button("Ordenar");
        btnTicket = new Button("Pagar");

        comboBoxProducto = new ComboBox<>();
        comboBoxProducto.setItems(producto);

        txtTaqueria.setText(String.valueOf(taqueria));
        txtTaqueria.setEditable(false);
        txtTaqueria.setAlignment(Pos.CENTER);
        gridPanePanel.add(lblTaqueria,1,0);
        gridPanePanel.add(txtTaqueria,2,0);

        txtMesa.setText(String.valueOf(mesa));
        txtMesa.setEditable(false);
        txtMesa.setAlignment(Pos.CENTER);
        gridPanePanel.add(lblMesa,1,1);
        gridPanePanel.add(txtMesa,2,1);

        txtEmpleado.setText(String.valueOf(empleado));
        txtEmpleado.setEditable(false);
        txtEmpleado.setAlignment(Pos.CENTER);
        gridPanePanel.add(lblEmpelado, 1,2);
        gridPanePanel.add(txtEmpleado,2,2);

        txtNombre.setEditable(false);
        txtNombre.setAlignment(Pos.CENTER);
        gridPanePanel.add(lblNombre,1,3);
        gridPanePanel.add(txtNombre,2,3);

        txtDescripcion.setEditable(false);
        gridPanePanel.add(lblDescripcion,1,4);
        gridPanePanel.add(txtDescripcion,2,4);

        txtPrecio.setEditable(false);
        txtPrecio.setAlignment(Pos.CENTER);
        gridPanePanel.add(lblPrecio,1,5);
        gridPanePanel.add(txtPrecio,2,5);

        gridPanePanel.add(comboBoxProducto,1,6);

        gridPanePanel.add(lblCantidad,1,7);
        gridPanePanel.add(txtCantidad,2,7);

        txtPrecioFinal.setEditable(false);
        gridPanePanel.add(lblPrecioFinal,1,8);
        gridPanePanel.add(txtPrecioFinal,2,8);

        gridPanePanel.add(btnOrdenar,1,9);
        gridPanePanel.add(btnTicket, 2, 9);

        gridPanePanel.setVgap(15);

        tbvCuenta = new TableView<>();
        tbvCuenta.setOnMouseClicked(mouseEventEventHandler);
        CrearTabla();

        btnOrdenar.setOnAction(event -> OrdenarComida());
        btnTicket.setOnAction(handler);


        vBoxTabla.getChildren().addAll(tbvCuenta);
        vBoxTabla.setPrefSize(500,450);

        hBoxprincipal.getChildren().addAll(vBoxTabla,gridPanePanel);
        hBoxprincipal.setSpacing(25);

        escena = new Scene(hBoxprincipal,750,450);
    }

    private void OrdenarComida()
    {
        objCu.setId_taqueria(taqueria);
        objCu.setId_mesa(mesa);
        objCu.setId_empleado(empleado);
        int numProductos = Integer.parseInt(txtCantidad.getText());
        float costo = Float.parseFloat(txtPrecio.getText());
        objCu.setPrecio(costo);
        objCu.setCantidad(numProductos);

        float total = numProductos*costo;

        txtPrecioFinal.setText(String.valueOf(total));
        objCu.setPrecio_final(Float.parseFloat(txtPrecioFinal.getText()));

        String eleccion = comboBoxProducto.getValue();

        if(eleccion.equals("Comida"))
        {
            objCu.setId_comida(Integer.parseInt(txtComida.getText()));
            objCu.setId_bebida(0);
            objCu.insOrden();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ordenar Comida");
            alert.setHeaderText(null);
            alert.setContentText("Comida Ordenada");
            alert.showAndWait();
            txtPrecioFinal.setText("");
            txtCantidad.setText("");
        }
        else
        {
            if( eleccion.equals("Bebida"))
            {
                objCu.setId_bebida(Integer.parseInt(txtComida.getText()));
                objCu.setId_comida(0);
                objCu.insOrden();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ordenar Bebida");
                alert.setHeaderText(null);
                alert.setContentText("Bebida Ordenada");
                alert.showAndWait();
                txtPrecioFinal.setText("");
                txtCantidad.setText("");
            }
        }
    }

    private void CrearTabla() {

        TableColumn<CuentaDAO,Integer> tbcIdProducto = new TableColumn<>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("id_comida"));

        TableColumn<CuentaDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<CuentaDAO,Float> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<CuentaDAO,String> tbcDescripcion = new TableColumn<>("DESCRIPCIÓN");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tbvCuenta.getColumns().addAll(tbcIdProducto,tbcNombre,tbcPrecio,tbcDescripcion);
        tbvCuenta.setItems(objCu.selAllProductos());
    }

    EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent mouseEvent)
        {
            CuentaDAO cuentaDAO = tbvCuenta.getSelectionModel().getSelectedItem();

            txtNombre.setText(cuentaDAO.getNombre());
            txtPrecio.setText(String.valueOf(cuentaDAO.getPrecio()));
            txtComida.setText(String.valueOf(cuentaDAO.getId_comida()));
            txtDescripcion.setText(cuentaDAO.getDescripcion());
        }
    };

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent actionEvent)
        {
            File file = new File(DEST);
            file.getParentFile().mkdir();
            pdf.GenerarTicket(empleado, mesa, taqueria);
        }
    };
}