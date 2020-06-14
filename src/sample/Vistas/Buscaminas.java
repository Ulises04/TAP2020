package sample.Vistas;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Eventos.EventoBuscaminas;

public class Buscaminas extends Stage implements EventHandler
{
    private Label lblNoRows, lblNoCols, lblBom;
    public static Label lblBombas;
    private TextField txtNoRows, txtNoCols;
    private Button btnMinar;
    private Button re;
    private GridPane gdpCampo;

    private Button[][] arBtnCeldas;//Arreglo de controles para hacer dinámico el programa

    private Scene escena;

    private HBox hBox, hBox1;
    private VBox vBox,vBox1;

    public Buscaminas()
    {
        CrearGUI();
        this.setTitle("El Proyecto Más Dificl de Todos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI()
    {

        ImageView img =new ImageView("sample/Imagenes/uwu.png");

        vBox = new VBox();



        lblNoRows = new Label("No. filas: ");
        lblNoCols = new Label("No. Columnas: ");
        lblBom = new Label("No. Bombas");
        lblBombas = new Label("0");

        hBox1 = new HBox(lblBom,lblBombas);

        txtNoRows = new TextField();
        txtNoCols = new TextField();

        btnMinar = new Button("He ganado!");
        re=new Button();
        re.setGraphic(img);
        img.setFitHeight(30);
        img.setFitWidth(30);

        EventoBuscaminas e = new EventoBuscaminas(txtNoRows, txtNoCols, arBtnCeldas, gdpCampo , vBox,lblBombas);
        re.addEventHandler(MouseEvent.MOUSE_CLICKED,e);

        btnMinar.setOnMouseClicked(event -> {
            int[][] auxArr = EventoBuscaminas.arr2;
            Button[][] auxBtn = EventoBuscaminas.arBtnCeldas;
            int cont = 0;
            boolean graficos = true;

            if(auxBtn[0][0].isDisable() || auxBtn[0][0].getGraphic() != null){
                for (int i = 0; i < auxArr.length; i++) {
                    for (int j = 0; j < auxArr[i].length; j++) {
                        int id = Integer.parseInt(auxBtn[i][j].getId());
                        if(auxArr[i][j] == -1 && id >= 1){
                            cont++;
                        }
                        if(auxBtn[i][j].getGraphic() == null)
                            graficos = false;

                    }
                }
                if(cont == EventoBuscaminas.minas && graficos){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Felicidades!");
                    a.setContentText("Has encontrado todas las minas!");
                    a.show();
                }else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText("Huy!");
                    a.setContentText("Seguro que estan marcadas todas la minas?");
                    a.show();
                }
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Que paso padre!?");
                a.setContentText("Minimo dale a una mina!");
                a.show();
            }
        });


        //btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);//Cuando el método handler se encuentra en la misma clase
        //btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventoBuscaminas(txtNoRows, txtNoCols, arBtnCeldas, gdpCampo , vBox, lblBombas));//Cuando el método handler se encuentra en una clase independiente
        /*btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()//Cuando el método handler se hace en la misma lineas de clase
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                System.out.println("Mi tercer Evento :)");
            }
        });*/
        //btnMinar.setOnAction(event -> {System.out.println("Mi Cuarto Evento :)");});//Cuanto Utilizamos setOnAction
        //btnMinar.setOnAction(event -> Evento());

        vBox1 = new VBox(lblNoRows,hBox1);

        hBox = new HBox();
        hBox.getChildren().addAll(vBox1,txtNoRows,re,lblNoCols, txtNoCols,btnMinar);
        vBox.getChildren().addAll(hBox);

        escena = new Scene(vBox,600,500);
        escena.getStylesheets().add("sample/Estilos/estilos_buscaminos.css");
    }

    private void Evento()
    {
        System.out.println("Mi Quinto Evento :)");
    }

    @Override
    public void handle(Event event)
    {
        System.out.println("Mi primer Evento :) ");
    }
}