package sample.Vistas;

import Modelos.ProductosDAO;
import Modelos.ProveedoresDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FrmProducto extends Stage
{
    private ProductosDAO objP;
    private TableView<ProductosDAO> tbvProductos;
    private VBox vBox;
    private TextField txtDesc, txtCosto, txtPrecio, txtExistencia;
    private ComboBox<String> cbxVigente;
    private ComboBox<ProveedoresDAO> cbxProveedor;
    private Button btnGuardar;
    private Scene escena;

    public FrmProducto(TableView<ProductosDAO> tbvProductos, ProductosDAO obj)
    {
        if( obj != null)
            objP = obj;
        else
            objP = new ProductosDAO();
        this.tbvProductos = tbvProductos;
        CrearGUI();
        this.setTitle("Gestión de Productos :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {
        vBox = new VBox();

        txtDesc = new TextField();
        txtDesc.setPromptText("Introduce la descripción");
        txtDesc.setText(objP.getNomProducto());

        txtCosto = new TextField();
        txtCosto.setPromptText("Introduce el Costo");
        txtCosto.setText(objP.getCosto()+"");

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Intoduce el Precio");
        txtPrecio.setText(objP.getPrecio()+"");

        txtExistencia = new TextField();
        txtExistencia.setPromptText("Intoduce la existencia");
        txtExistencia.setText(objP.getExistencia()+"");

        ObservableList<String> listVigente = FXCollections.observableArrayList();
        listVigente.addAll("Vigente", "Descontinuado");

        cbxVigente = new ComboBox();
        cbxVigente.setItems(listVigente);
        String val = ( objP.isVigente() == true ) ? "Vigente" : "Descontinuado";
        cbxVigente.setValue(val);

        cbxProveedor = new ComboBox();
        cbxProveedor.setItems(new ProveedoresDAO().selAllProveedor());
        ProveedoresDAO objPr = new ProveedoresDAO();
        objPr.setIdProveedor(objP.getIdProveedor());
        objPr.getProvById();
        cbxProveedor.setValue(objPr);


        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarDatos());

        vBox.getChildren().addAll(txtDesc,txtCosto,txtPrecio,txtExistencia, cbxVigente,cbxProveedor,btnGuardar);

        escena = new Scene(vBox, 250, 250);
    }

    private void guardarDatos()
    {
        objP.setNomProducto(txtDesc.getText());
        objP.setCosto(Float.parseFloat(txtCosto.getText()));
        objP.setPrecio(Float.parseFloat(txtPrecio.getText()));
        objP.setExistencia(Integer.parseInt(txtExistencia.getText()));

        boolean ban = (cbxVigente.getValue() == "Vigente") ? true : false;
        objP.setVigente(ban);

        ProveedoresDAO objTemp = cbxProveedor.getValue();

        objP.setIdProveedor(objTemp.getIdProveedor());

        if( objP.getIdProducto() >= 1)
            objP.updProducto();
        else
            objP.insProducto();

        tbvProductos.setItems(objP.selAllProducto());
        tbvProductos.refresh();

        this.close();
    }
}