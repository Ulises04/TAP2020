package sample.Vistas;

import ModelosTAQ.EmpleadoDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCellEmp;

public class CRUDEmpleado extends Stage
{
    private Scene escena;
    private VBox vbox;
    private TableView<EmpleadoDAO> tbvEmpleado;
    private Button btnAgregar;
    private EmpleadoDAO objEm;

    public CRUDEmpleado(){
        objEm = new EmpleadoDAO();
        CrearGUI();
        this.setTitle("CRUD Empleado :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();
        tbvEmpleado = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Empleado");
        btnAgregar.setOnAction(event -> AgregarEmpleado());
        vbox.getChildren().addAll(tbvEmpleado,btnAgregar);
        escena = new Scene(vbox,750,450);
    }

    private void CrearTabla() {

        TableColumn<EmpleadoDAO,Integer> tbcIdEmpleado = new TableColumn<>("ID");
        tbcIdEmpleado.setCellValueFactory(new PropertyValueFactory<>("id_empleado"));

        TableColumn<EmpleadoDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<EmpleadoDAO,String> tbcPassword = new TableColumn<>("PASSWORD");
        tbcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<EmpleadoDAO,String> tbcApaterno = new TableColumn<>("APELLIDO PARTENO");
        tbcApaterno.setCellValueFactory(new PropertyValueFactory<>("apaterno"));

        TableColumn<EmpleadoDAO,String> tbcAmaterno = new TableColumn<>("APELLIDO MATERNO");
        tbcAmaterno.setCellValueFactory(new PropertyValueFactory<>("amaterno"));

        TableColumn<EmpleadoDAO,String> tbcTelefono = new TableColumn<>("TELÉFONO");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        TableColumn<EmpleadoDAO,Integer> tbcIdTaqueria = new TableColumn<>("TAQUERIA");
        tbcIdTaqueria.setCellValueFactory(new PropertyValueFactory<>("id_taqueria"));

        TableColumn<EmpleadoDAO,String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<EmpleadoDAO, String>, TableCell<EmpleadoDAO, String>>() {
                    @Override
                    public TableCell<EmpleadoDAO, String> call(TableColumn<EmpleadoDAO, String> param) {
                        return new ButtonCellEmp(2);
                    }
                }
        );

        TableColumn<EmpleadoDAO,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<EmpleadoDAO, String>, TableCell<EmpleadoDAO, String>>() {
                    @Override
                    public TableCell<EmpleadoDAO, String> call(TableColumn<EmpleadoDAO, String> param) {
                        return new ButtonCellEmp(1);
                    }
                }
        );


        tbvEmpleado.getColumns().addAll(tbcIdEmpleado,tbcNombre,tbcPassword,tbcApaterno,tbcAmaterno,tbcTelefono,tbcIdTaqueria,tbcEditar,tbcBorrar);
        tbvEmpleado.setItems(objEm.selAllEmpleados());
    }

    private void AgregarEmpleado()
    {
        new FormEmpleado(tbvEmpleado, null,1);
    }
}