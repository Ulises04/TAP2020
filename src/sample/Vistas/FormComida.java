package sample.Vistas;

import ModelosTAQ.ComidaDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormComida extends Stage
{
    Scene escena;

    TextField txtNombre, txtPrecio, txtDescripcion;
    Label lblNombre, lblPrecio, lblDescripción;
    VBox vBox;
    Button btnAgregar, btnActualizar;

    TableView<ComidaDAO> tbvComida;

    ComidaDAO objC;

    public FormComida(TableView<ComidaDAO> tbvComida, ComidaDAO obj, int bandera)
    {
        if( obj != null) {
            System.out.println("Objeto Con Contenido");
            objC = obj;
        }
        else {
            objC = new ComidaDAO();
            System.out.println("Objeto Vacío :(");
        }
        this.tbvComida = tbvComida;
        CrearGUI(bandera);
        this.setTitle("Gestión Comida :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI(int bandera)
    {
        vBox = new VBox();

        txtNombre = new TextField();
        txtNombre.setPromptText("Ingrese el nombre de la comida");
        txtNombre.setText(objC.getNombre());

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Ingrese el precio de la comida");
        txtPrecio.setText(objC.getPrecio()+"");

        txtDescripcion = new TextField();
        txtDescripcion.setPromptText("Ingrese la descripción de la comida");
        txtDescripcion.setText(objC.getDescripcion());

        lblNombre = new Label("Nombre");
        lblDescripción = new Label("Descripción");
        lblPrecio = new Label("Precio");

        btnAgregar = new Button("Agregar Comida");
        btnAgregar.setOnAction(actionEvent -> enviarDatos(1));

        btnActualizar = new Button("Actualizar Comida");
        btnActualizar.setOnAction(actionEvent -> enviarDatos(2));

        if(bandera == 1)
        {
            btnActualizar.setDisable(true);
        }
        else
        {
            btnAgregar.setDisable(true);
        }

        vBox.getChildren().addAll(lblNombre,txtNombre,lblPrecio,txtPrecio,lblDescripción, txtDescripcion,btnAgregar, btnActualizar);

        escena = new Scene(vBox,400, 300);
    }

    private void enviarDatos(int proceso)
    {
        objC.setNombre(txtNombre.getText());
        objC.setPrecio(Float.parseFloat(txtPrecio.getText()));
        objC.setDescripcion(txtDescripcion.getText());

        if( proceso  == 1 )
        {
            objC.insComida();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Comida Registrada");
            alert.setHeaderText(null);
            alert.setContentText("La Comida se Registro Correctamente");
            alert.showAndWait();
        }
        else
        {
            objC.updComida();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Comida Registrada");
            alert.setHeaderText(null);
            alert.setContentText("La Comida se ha Actualizado Correctamente");
            alert.showAndWait();
        }

        tbvComida.setItems(objC.selAllComidas());
        tbvComida.refresh();

        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");
    }
}