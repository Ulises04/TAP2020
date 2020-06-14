package sample.Vistas;

import ModelosTAQ.EmpleadoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormEmpleado extends Stage
{
    Scene escena;
    TextField txtNombre, txtPassword, txtApaterno, txtAmaterno, txtTelefono, txtIdTqueria;
    VBox vBox;
    Button btnAgregar, btnActualizar;
    EmpleadoDAO objEmp;
    TableView<EmpleadoDAO> tbvEmpleado;


    public FormEmpleado(TableView<EmpleadoDAO> tbvEmpleado, EmpleadoDAO obj, int bandera)
    {
        if( obj != null) {
            objEmp = obj;
        }
        else {
            objEmp = new EmpleadoDAO();
        }
        this.tbvEmpleado = tbvEmpleado;
        crearGUI(bandera);
        this.setTitle("Agregar Empleado :)");
        this.setScene(escena);
        this.show();
    }

    private void crearGUI(int bandera)
    {
        vBox = new VBox();

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del Empleado");
        txtNombre.setText(objEmp.getNombre());

        txtPassword = new TextField();
        txtPassword.setPromptText("Contraseña");
        txtPassword.setText(objEmp.getPassword());

        txtApaterno = new TextField();
        txtApaterno.setPromptText("Apellido Paterno");
        txtApaterno.setText(objEmp.getApaterno());

        txtAmaterno = new TextField();
        txtAmaterno.setPromptText("Apellido Matenro");
        txtAmaterno.setText(objEmp.getAmaterno());

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Teléfono");
        txtTelefono.setText(objEmp.getTelefono());

        txtIdTqueria = new TextField();
        txtIdTqueria.setPromptText("Taqueria donde Trabaja");
        txtIdTqueria.setText(String.valueOf(objEmp.getId_taqueria()));

        btnAgregar = new Button("Agregar Empleado");
        btnAgregar.setOnAction(actionEvent -> onPress(1));

        btnActualizar = new Button("Actualizar Empleado");
        btnActualizar.setOnAction(actionEvent -> onPress(2));

        vBox.getChildren().addAll(txtNombre, txtPassword, txtApaterno, txtAmaterno, txtTelefono, txtIdTqueria, btnAgregar,btnActualizar);
        vBox.setSpacing(15);

        if(bandera == 1)
        {
            btnActualizar.setDisable(true);
        }
        else
        {
            btnAgregar.setDisable(true);
        }

        escena = new Scene(vBox, 450,450);
    }

    private void onPress(int proceso)
    {
        objEmp.setNombre(txtNombre.getText());
        objEmp.setPassword(txtPassword.getText());
        objEmp.setApaterno(txtApaterno.getText());
        objEmp.setAmaterno(txtAmaterno.getText());
        objEmp.setTelefono(txtTelefono.getText());
        objEmp.setId_taqueria(Integer.parseInt(txtIdTqueria.getText()));

        if( proceso  == 1 )
        {
            objEmp.insEmpleado();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empleado Registrada");
            alert.setHeaderText(null);
            alert.setContentText("El empleado se Registro Correctamente");
            alert.showAndWait();
        }
        else
        {
            objEmp.updEmpleado();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empleado Actualizado");
            alert.setHeaderText(null);
            alert.setContentText("El Empleado se ha Actualizado Correctamente");
            alert.showAndWait();
        }

        tbvEmpleado.setItems(objEmp.selAllEmpleados());
        tbvEmpleado.refresh();

        txtNombre.setText("");
        txtPassword.setText("");
        txtApaterno.setText("");
        txtAmaterno.setText("");
        txtTelefono.setText("");
        txtIdTqueria.setText("");
    }
}