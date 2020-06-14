package sample.Vistas;

import ModelosTAQ.EmpleadoDAO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Login_Taqueria extends Stage
{
    private Scene escena;
    private VBox vbox;
    private Label lblIcono, lblSign, lblUser, lblPwd;
    private TextField txtUser;
    private PasswordField txtPassword;
    private Button btnIngresar;
    private EmpleadoDAO objEmp;
    private int empleado, taqueria;

    public Login_Taqueria()
    {
        objEmp = new EmpleadoDAO();
        CrearGUI();
        this.setTitle("Taqueria Login)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {
        vbox = new VBox();

        lblIcono = new Label();
        Image icono = new Image("sample/Imagenes/Icono_Taqueria.png");
        ImageView imagen = new ImageView(icono);
        imagen.setFitHeight(200);
        imagen.setFitWidth(200);
        lblIcono.setGraphic(imagen);

        lblSign = new Label("Sign In");
        lblUser = new Label("Usuario");
        lblPwd = new Label("Contraseña");

        txtUser = new TextField();
        txtUser.setPromptText("Ingrese la contrasela");
        txtUser.setMaxSize(450,150);

        txtPassword = new PasswordField();
        txtUser.setPromptText("Ingrese su Contraseña");
        txtPassword.setMaxSize(450,150);

        btnIngresar = new Button("Login");
        btnIngresar.setOnAction(event -> validaDatos());

        vbox.getChildren().addAll(lblIcono, lblSign, lblUser,txtUser,lblPwd,txtPassword,btnIngresar);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        escena = new Scene(vbox, 500, 500);
    }

    private void validaDatos()
    {
        objEmp.setNombre(txtUser.getText());
        objEmp.setPassword(txtPassword.getText());

        objEmp.sellOneEmpleado();

        if(objEmp.bandera == 1 && txtUser.getText().equals("Ulises"))
        {
            Alerta("Iniciaste Sesión como Administrador");
            new PanelAdministrador();
            this.close();
        }
        else
        {
            if(objEmp.bandera == 1)
            {
                Alerta("Iniciaste Sesión como empleado");
                new Mesas(objEmp.id_empleado, objEmp.id_taqueria);
                this.close();
            }
            else
            {
                if( objEmp.bandera == 0 )
                {
                    Alerta("Usuario o Contraseña incorrecta. Vuelve a Intentar");
                    txtUser.setText("");
                    txtPassword.setText("");
                }
                else
                {
                    Alerta("A ocurrido algún error");
                }
            }
        }


    }

    private void Alerta(String contenido)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inicio de Sesión");
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}