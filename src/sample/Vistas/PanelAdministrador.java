package sample.Vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelAdministrador extends Stage
{
    private Scene escena;
    private GridPane gridPane;
    private Button btnComida, btnBebida, btnEmpleado, btnVentas;
    private Label lblComida, lblBebida, lblEmpleado, lblVentas;

    public PanelAdministrador(){
        CrearGUI();
        this.setTitle("Panel del Control :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {
        gridPane = new GridPane();


        Image icono1 = new Image("sample/Imagenes/Icono_Comida.png");
        ImageView imagen1 = new ImageView(icono1);
        imagen1.setFitHeight(100);
        imagen1.setFitWidth(100);
        btnComida = new Button("Agregar Nueva Comida",imagen1);
        btnComida.setOnAction(handler);

        Image icono2 = new Image("sample/Imagenes/Icono_Bebida.png");
        ImageView imagen2 = new ImageView(icono2);
        imagen2.setFitHeight(100);
        imagen2.setFitWidth(100);
        btnBebida = new Button("Agregar Nueva Bebida",imagen2);
        btnBebida.setOnAction(handler);

        Image icono3 = new Image("sample/Imagenes/Usuario.png");
        ImageView imagen3 = new ImageView(icono3);
        imagen3.setFitHeight(100);
        imagen3.setFitWidth(100);
        btnEmpleado = new Button("Agregar Nuevo Empleado", imagen3);
        btnEmpleado.setOnAction(handler);


        gridPane.add(btnComida,0,0);
        gridPane.add(btnBebida,0, 1);
        gridPane.add(btnEmpleado, 1,0);

        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);


        escena = new Scene(gridPane, 600, 600);
    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if( actionEvent.getSource()==btnComida )
            {
                new CRUDComida();
            }
            else
            {
                if( actionEvent.getSource() == btnBebida )
                {
                    new CRUDBebida();
                }
                else
                {
                    if( actionEvent.getSource() == btnEmpleado )
                    {
                        new CRUDEmpleado();
                    }
                }
            }
        }
    };
}