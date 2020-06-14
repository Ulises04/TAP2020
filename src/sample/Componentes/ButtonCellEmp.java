package sample.Componentes;

import ModelosTAQ.EmpleadoDAO;
import javafx.scene.control.*;
import sample.Vistas.FormEmpleado;

import java.util.Optional;

public class ButtonCellEmp extends TableCell<EmpleadoDAO,String>
{
    private Button btnCelda;
    private EmpleadoDAO objEm;

    public ButtonCellEmp(int opc)
    {
        if( opc == 1) {
            btnCelda = new Button(("Editar"));
            btnCelda.setOnAction(event ->
            {
                TableView<EmpleadoDAO> tbvTemp;
                tbvTemp = ButtonCellEmp.this.getTableView();
                objEm = ButtonCellEmp.this.getTableView().getItems().get(ButtonCellEmp.this.getIndex());
                new FormEmpleado(tbvTemp, objEm,2);
                //ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            });
        }
        else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Confirmando Acción");
                alert.setContentText("¿Deseas eliminar al Empleado :(?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objEm = ButtonCellEmp.this.getTableView().getItems().get(ButtonCellEmp.this.getIndex());
                    if( objEm != null )
                        System.out.println("Objeto con Index Control 1");
                    else
                        System.out.println("Ya mejor date de bajan 1");
                    objEm.delEmpleado();

                    //Refrescar la tabla
                    ButtonCellEmp.this.getTableView().setItems(objEm.selAllEmpleados());
                    ButtonCellEmp.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty)
    {
        super.updateItem(item, empty);
        if( !empty )
            setGraphic(btnCelda);
    }
}