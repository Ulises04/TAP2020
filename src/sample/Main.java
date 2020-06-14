package sample;

import Modelos.Conexion;
import ModelosTAQ.ConexionTAQ;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Vistas.*;

public class Main extends Application {

    /*private Button btn1, btn2, btn3, btn4;
    private HBox hBox;
    private VBox vBox;*/

    MenuBar mnbProyecto;
    Menu menCompetencia1, menCompetencia2, menSalir;
    MenuItem mitPractica1, mitBye, mitPractica2, mitPractica3, mitPractica4;
    Scene escena;
    BorderPane brpPrincipal;

    @Override
    public void start(Stage primaryStage) throws Exception{
       //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

       brpPrincipal = new BorderPane();
       mnbProyecto = new MenuBar();
       brpPrincipal.setTop(mnbProyecto);

       menCompetencia1 = new Menu("1er. Competencia");
       menCompetencia2 = new Menu("2da. Competencia");
       menSalir = new Menu("Salir");

       mitPractica1 = new MenuItem("Buscaminas");
       mitPractica1.setOnAction(event -> OptionMenu(1));

       mitPractica2 = new MenuItem("Taquimecanografo");
       mitPractica2.setOnAction(event -> OptionMenu(2));

        mitPractica3 = new MenuItem("CRUD Productos");
        mitPractica3.setOnAction(event -> OptionMenu(3));

        mitPractica4 = new MenuItem("CRUD Taqueria");
        mitPractica4.setOnAction(actionEvent -> OptionMenu(4));

       mitBye = new MenuItem("Bye");
       mitBye.setOnAction(event -> OptionMenu(20));

       menCompetencia1.getItems().addAll(mitPractica1, mitPractica2);
       menCompetencia2.getItems().addAll(mitPractica3, mitPractica4);
       menSalir.getItems().add(mitBye);

       //Cargamos los menis al menuBar
        mnbProyecto.getMenus().addAll(menCompetencia1, menCompetencia2, menSalir);
        escena = new Scene(brpPrincipal,300,275);
        escena.getStylesheets().add("sample/Estilos/estilos_principal.css");

       //Instanciar un botón NO USAR AWT
        //Button btnHola = new Button("Saludar: ");
        //btn1 = new Button()

        //Creamos la conexión a la bd
        Conexion.crearConexion();

        ConexionTAQ.crearConexion();

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Tópicos avanzados de Programación :)");
        primaryStage.setScene(escena);
        primaryStage.show();
    }

    private void OptionMenu(int i)
    {
        switch (i)
        {
            case 1:
                new Buscaminas();
                break;
            case 2:
                new Taquimecanografo();
                break;
            case 3:
                new CRUDProductos();
                break;
            case 4:
                new Login_Taqueria();
                break;
            case 20:
                System.exit(0);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
