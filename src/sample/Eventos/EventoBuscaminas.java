package sample.Eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.createFile;
import java.io.RandomAccessFile;

public class EventoBuscaminas implements EventHandler
{
    private TextField txtNoRows, txtNoCols;
    private GridPane gdpCampo;
    public static Button[][] arBtnCeldas;
    private VBox vBox;
    private createFile archivo;
    public static int[][] arr2;
    public static Label lblBombas;
    public static int minas;
    public static int id = 1;


    public EventoBuscaminas(TextField txtNR, TextField txtNC, Button[][] arBtnCeldas, GridPane gdpCampo , VBox vBox, Label lblBombas)
    {
        this.txtNoRows = txtNR;
        this.txtNoCols = txtNC;
        this.arBtnCeldas = arBtnCeldas;
        this.gdpCampo = gdpCampo;
        this.vBox = vBox;
        this.lblBombas = lblBombas;
    }


    @Override
    public void handle(Event event)
    {
        int nc;
        int nr = Integer.parseInt(txtNoRows.getText());
        if(txtNoCols.getText().equals(""))
            nc = nr;
        else
            nc = Integer.parseInt(txtNoCols.getText());

        archivo=new createFile(nr,nc);
        arr2 = archivo.createArr();
        arr2= archivo.fixArr(arr2);
        RandomAccessFile file = archivo.createFile(arr2);
        if(arBtnCeldas != null)
        {
            vBox.getChildren().removeAll(gdpCampo);
        }

        minas = archivo.print_array(arr2);
        lblBombas.setText(Integer.toString(minas));


        arBtnCeldas = new Button[nr][nc];
        gdpCampo = new GridPane();
        gdpCampo.getChildren().removeAll();
        gdpCampo.setPadding(new Insets(15));

        for (int i = 0; i < nr ; i++)
        {
            for (int j = 0; j < nc ; j++)
            {
                arBtnCeldas[i][j] = new Button();
                arBtnCeldas[i][j].setPrefSize(100  ,100);
                arBtnCeldas[i][j] .setStyle("-fx-base: #949494;-fx-background-radius: 10;");
                gdpCampo.add(arBtnCeldas[i][j],j,i);
                arBtnCeldas[i][j].setOnMouseClicked(new botones(arBtnCeldas[i][j],i,j));
                arBtnCeldas[i][j].setId("0");
            }
        }
        vBox.getChildren().add(gdpCampo);
    }
}