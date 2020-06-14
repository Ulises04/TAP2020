package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Eventos.*;

import java.io.File;

public class Taquimecanografo extends Stage
{
    private Scene escena;
    private ToolBar tlbMenu;
    private Button btnAbrir;
    private TextArea txaTexto, txaEscrituta;
    private VBox vPrincipal, vTeclado;
    private HBox hTeclas3, hTeclas4, hTeclas5, hTeclas6;
    private Button[] arBotones3;
    private Button[] arBotones4;
    private Button[] arBotones5;
    private Button[] arBotones6;
    private String[] arTeclas3 = {"↹","Q","W","E","R","T","Y","U","I","O","P","´","+","<--"};
    private String[] arTeclas4 = {"Bloq Mayús","A","S","D","F","G","H","J","K","L","Ñ","{","Enter"};
    private String[] arTeclas5 = {"↑","Z","X","C","V","B","N","M",",",".","-","↑"};
    private String[] arTeclas6 = {"Ctrl","fn","⊞","alt","SPACE","alt gr","<","◄","▲","▼","►"};
    private FileChooser flcArchivos;

    EventoTaquimecanografo objEvento;

    public Taquimecanografo()
    {
        CrearGUI();
        this.setTitle("Mi tutor de mecanografía");
        this.setScene(escena);
        this.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventoVentanaTaqui());
        this.show();
    }

    public void CrearGUI()
    {
        tlbMenu = new ToolBar();
        btnAbrir = new Button();
        //btnAbrir.setPrefSize(15,15);
        btnAbrir.setGraphic(new ImageView("sample/Imagenes/Open.png"));//código para agregar un imagen como icono al ToolBar
        btnAbrir.setOnAction(event -> AbrirExplorador());

        tlbMenu.getItems().add(btnAbrir);

        txaTexto = new TextArea();
        txaTexto.setPrefRowCount(5);
        txaTexto.setEditable(false);

        txaEscrituta = new TextArea();
        txaEscrituta.setPrefRowCount(5);

        hTeclas3 = new HBox();
        hTeclas3.setSpacing(5);
        hTeclas3.setId("hbox-custom3");

        hTeclas4 = new HBox();
        hTeclas4.setSpacing(5);
        hTeclas4.setId("hbox-custom4");

        hTeclas5 = new HBox();
        hTeclas5.setSpacing(5);
        hTeclas5.setId("hbox-custom5");

        hTeclas6 = new HBox();
        hTeclas6.setSpacing(5);
        hTeclas6.setId("hbox-custom6");

        arBotones3 = new Button[14];
        arBotones4 = new Button[13];
        arBotones5 = new Button[12];
        arBotones6 = new Button[11];
        for (int i = 0; i <  14; i++)
        {
            arBotones3[i] = new Button(arTeclas3[i]);
            hTeclas3.getChildren().addAll(arBotones3[i]);
            if( i != 0 && i != 13)
                arBotones3[i].setId("btnRow3");
            else
                arBotones3[i].setId("btnRow3_013");
        }
        System.out.println("Se Agregaron los elementos al arreglo 3");

        for (int i = 0; i <  13; i++)
        {
            arBotones4[i] = new Button(arTeclas4[i]);
            hTeclas4.getChildren().addAll(arBotones4[i]);
            if( i != 0 && i != 12)
                arBotones4[i].setId("btnRow4");
            else
                arBotones4[i].setId("btnRow4_012");
        }
        System.out.println("Se Agregaron los elementos al arreglo 4");

        for (int i = 0; i <  12; i++)
        {
            arBotones5[i] = new Button(arTeclas5[i]);
            hTeclas5.getChildren().addAll(arBotones5[i]);
            if( i != 0 && i != 11)
                arBotones5[i].setId("btnRow5");
            else
                arBotones5[i].setId("btnRow5_011");
        }
        System.out.println("Se Agregaron los elementos al arreglo 5");

        for (int i = 0; i < 11; i++)
        {
            arBotones6[i] = new Button(arTeclas6[i]);
            hTeclas6.getChildren().addAll(arBotones6[i]);
            if( i != 4)
                arBotones6[i].setId("btnRow6");
            else
                arBotones6[i].setId("btnRow6_010");
        }
        System.out.println("Se Agregaron los elementos al arreglo 6");

        objEvento = new EventoTaquimecanografo(arBotones3, arBotones4, arBotones5, arBotones6);
        txaEscrituta.setOnKeyPressed(objEvento);
        txaEscrituta.setOnKeyReleased(objEvento);

        vTeclado = new VBox();
        vTeclado.getChildren().addAll(hTeclas3,hTeclas4,hTeclas5,hTeclas6);

        vPrincipal = new VBox();
        vPrincipal.setSpacing(5);
        vPrincipal.getChildren().addAll(tlbMenu, txaTexto, txaEscrituta, vTeclado);

        escena = new Scene(vPrincipal, 880, 350);
        escena.getStylesheets().addAll("sample/Estilos/estilos_taquimecanografo.css");
    }

    private void AbrirExplorador()
    {
        flcArchivos = new FileChooser();
        flcArchivos.setTitle("Archivo para Taquimecanografo");
        File objFile = flcArchivos.showOpenDialog(this);
    }
}