package sample.Vistas;

import ModelosTAQ.ComidaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellCom;

public class CRUDComida extends Stage
{
    private Scene escena;
    private VBox vbox;
    private TableView<ComidaDAO> tbvComida;
    private Button btnAgregar;
    private ComidaDAO objC;

    public CRUDComida(){
        objC = new ComidaDAO();
        CrearGUI();
        this.setTitle("CRUD Comida :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvComida = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Comida");
        btnAgregar.setOnAction(event -> AgregarComida());
        vbox.getChildren().addAll(tbvComida,btnAgregar);
        escena = new Scene(vbox,750,450);
    }

    private void CrearTabla() {

        TableColumn<ComidaDAO,Integer> tbcIdComida = new TableColumn<>("ID");
        tbcIdComida.setCellValueFactory(new PropertyValueFactory<>("id_comida"));

        TableColumn<ComidaDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<ComidaDAO,Float> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<ComidaDAO,String> tbcDescripcion = new TableColumn<>("DESCRIPCIÓN");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<ComidaDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<ComidaDAO, String>, TableCell<ComidaDAO, String>>() {
                    @Override
                    public TableCell<ComidaDAO, String> call(TableColumn<ComidaDAO, String> param) {
                        return new ButtonCellCom(2);
                    }
                }
        );

        TableColumn<ComidaDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<ComidaDAO, String>, TableCell<ComidaDAO, String>>() {
                    @Override
                    public TableCell<ComidaDAO, String> call(TableColumn<ComidaDAO, String> param) {
                        return new ButtonCellCom(1);
                    }
                }
        );


        tbvComida.getColumns().addAll(tbcIdComida,tbcNombre,tbcPrecio,tbcDescripcion,tbcEditar,tbcBorrar);
        tbvComida.setItems(objC.selAllComidas());
    }

    private void AgregarComida()
    {
        new FormComida(tbvComida, null,1);
    }
}