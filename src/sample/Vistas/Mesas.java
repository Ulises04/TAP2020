package sample.Vistas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Mesas extends Stage
{
    private Scene escena;
    GridPane gridPane;
    Button btnMesa1, btnMesa2, btnMesa3, btnMesa4, btnMesa5, btnMesa6;
    int bandera = 0;
    int empleado, taqueria;

    public Mesas(int empleado, int taqueria)
    {
        this.empleado = empleado;
        this.taqueria = taqueria;
        CrearGUI();
        this.setTitle("Mesas Disponibles :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {
        Image icono = new Image("sample/Imagenes/Mesa.png");
        ImageView imagen1 = new ImageView(icono);
        imagen1.setFitHeight(150);
        imagen1.setFitWidth(150);

        ImageView imagen2 = new ImageView(icono);
        imagen2.setFitHeight(150);
        imagen2.setFitWidth(150);

        ImageView imagen3 = new ImageView(icono);
        imagen3.setFitHeight(150);
        imagen3.setFitWidth(150);

        ImageView imagen4 = new ImageView(icono);
        imagen4.setFitHeight(150);
        imagen4.setFitWidth(150);

        ImageView imagen5 = new ImageView(icono);
        imagen5.setFitHeight(150);
        imagen5.setFitWidth(150);

        ImageView imagen6 = new ImageView(icono);
        imagen6.setFitHeight(150);
        imagen6.setFitWidth(150);

        btnMesa1 = new Button("Mesa 1\n",imagen1);
        btnMesa2 = new Button("Mesa 2\n",imagen2);
        btnMesa3 = new Button("Mesa 3\n",imagen3);
        btnMesa4 = new Button("Mesa 4\n",imagen4);
        btnMesa5 = new Button("Mesa 5\n",imagen5);
        btnMesa6 = new Button("Mesa 6\n",imagen6);

        btnMesa1.setOnAction(handler);
        btnMesa2.setOnAction(handler);
        btnMesa3.setOnAction(handler);
        btnMesa4.setOnAction(handler);
        btnMesa5.setOnAction(handler);
        btnMesa6.setOnAction(handler);

        gridPane = new GridPane();
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(btnMesa1,0,0);
        gridPane.add(btnMesa2,0,1);
        gridPane.add(btnMesa3,0,2);

        gridPane.add(btnMesa4,1,0);
        gridPane.add(btnMesa5,1,1);
        gridPane.add(btnMesa6,1,2);

        escena = new Scene(gridPane, 650,650);
    }

    EventHandler<ActionEvent> handler= new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent)
        {
            if( actionEvent.getSource() == btnMesa1 )
            {
                bandera = 1;
            }
            else
            {
                if( actionEvent.getSource() == btnMesa2 )
                {
                    bandera = 2;
                }
                else
                {
                    if( actionEvent.getSource() == btnMesa3 )
                    {
                        bandera = 3;
                    }
                    else
                    {
                        if( actionEvent.getSource() == btnMesa4 )
                        {
                            bandera = 4;
                        }
                        else
                        {
                            if( actionEvent.getSource() == btnMesa5 )
                            {
                                bandera = 5;
                            }
                            else
                            {
                                bandera = 6;
                            }
                        }
                    }
                }
            }

            new OrdenCliente(bandera, empleado, taqueria);
        }
    };
}