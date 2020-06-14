package sample.Vistas;

import ModelosTAQ.BebidaDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellBeb;

public class CRUDBebida extends Stage
{
    private Scene escena;
    private VBox vbox;
    private TableView<BebidaDAO> tbvBebida;
    private Button btnAgregar;
    private BebidaDAO objB;

    public CRUDBebida(){
        objB = new BebidaDAO();
        CrearGUI();
        this.setTitle("CRUD Productos :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvBebida = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Bebida");
        btnAgregar.setOnAction(event -> AgregarBebida());
        vbox.getChildren().addAll(tbvBebida,btnAgregar);
        escena = new Scene(vbox,750,450);
    }

    private void CrearTabla() {

        TableColumn<BebidaDAO,Integer> tbcIdBebida = new TableColumn<>("ID");
        tbcIdBebida.setCellValueFactory(new PropertyValueFactory<>("id_bebida"));

        TableColumn<BebidaDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<BebidaDAO,Float> tbcPrecio = new TableColumn<>("PRECIO");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<BebidaDAO,String> tbcDescripcion = new TableColumn<>("DESCRIPCIÓN");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<BebidaDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<BebidaDAO, String>, TableCell<BebidaDAO, String>>() {
                    @Override
                    public TableCell<BebidaDAO, String> call(TableColumn<BebidaDAO, String> param) {
                        return new ButtonCellBeb(2);
                    }
                }
        );

        TableColumn<BebidaDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<BebidaDAO, String>, TableCell<BebidaDAO, String>>() {
                    @Override
                    public TableCell<BebidaDAO, String> call(TableColumn<BebidaDAO, String> param) {
                        return new ButtonCellBeb(1);
                    }
                }
        );


        tbvBebida.getColumns().addAll(tbcIdBebida,tbcNombre,tbcPrecio,tbcDescripcion,tbcEditar,tbcBorrar);
        tbvBebida.setItems(objB.selAllBebidas());
    }

    private void AgregarBebida()
    {
        new FormBebida(tbvBebida, null,1);
    }
}