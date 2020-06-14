package sample.Vistas;

import ModelosTAQ.BebidaDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormBebida extends Stage
{
    Scene escena;

    TextField txtNombre, txtPrecio, txtDescripcion;
    Label lblNombre, lblPrecio, lblDescripción;
    VBox vBox;
    Button btnAgregar, btnActualizar;

    TableView<BebidaDAO> tbvBebida;

    BebidaDAO objB;

    public FormBebida(TableView<BebidaDAO> tbvBebida, BebidaDAO obj, int bandera)
    {
        if( obj != null) {
            System.out.println("Objeto Con Contenido");
            objB = obj;
        }
        else {
            objB = new BebidaDAO();
            System.out.println("Objeto Vacío :(");
        }
        this.tbvBebida = tbvBebida;
        CrearGUI(bandera);
        this.setTitle("Agregar Bebida :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI(int bandera)
    {
        vBox = new VBox();

        txtNombre = new TextField();
        txtNombre.setPromptText("Ingrese el nombre de la bebida");
        txtNombre.setText(objB.getNombre());

        txtPrecio = new TextField();
        txtPrecio.setPromptText("Ingrese el precio de la bebida");
        txtPrecio.setText(objB.getPrecio()+"");

        txtDescripcion = new TextField();
        txtDescripcion.setPromptText("Ingrese la descripción de la bebida");
        txtDescripcion.setText(objB.getDescripcion());

        lblNombre = new Label("Nombre");
        lblDescripción = new Label("Descripción");
        lblPrecio = new Label("Precio");

        btnAgregar = new Button("Agregar Bebida");
        btnAgregar.setOnAction(actionEvent -> enviarDatos(1));

        btnActualizar = new Button("Actualizar Bebida");
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
        objB.setNombre(txtNombre.getText());
        objB.setPrecio(Float.
                parseFloat(txtPrecio.getText()));
        objB.setDescripcion(txtDescripcion.getText());

        if( proceso  == 1 )
        {
            objB.insBebida();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bebida Registrada");
            alert.setHeaderText(null);
            alert.setContentText("La Bebida Registro Correctamente");
            alert.showAndWait();
        }
        else
        {
            objB.updBebida();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bebida Registrada");
            alert.setHeaderText(null);
            alert.setContentText("La Bebida se ha Actualizado Correctamente");
            alert.showAndWait();
        }

        tbvBebida.setItems(objB.selAllBebidas());
        tbvBebida.refresh();

        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");
    }
}