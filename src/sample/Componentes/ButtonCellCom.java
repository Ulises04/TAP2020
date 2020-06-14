package sample.Componentes;

import ModelosTAQ.ComidaDAO;
import javafx.scene.control.*;
import sample.Vistas.FormComida;

import java.util.Optional;

public class ButtonCellCom extends TableCell<ComidaDAO,String>
{
    private Button btnCelda;
    private ComidaDAO objC;

    public ButtonCellCom(int opc)
    {
        if( opc == 1) {
            btnCelda = new Button(("Editar"));
            btnCelda.setOnAction(event ->
            {
                TableView<ComidaDAO> tbvTemp;
                tbvTemp = ButtonCellCom.this.getTableView();
                objC = ButtonCellCom.this.getTableView().getItems().get(ButtonCellCom.this.getIndex());
                if( objC != null )
                    System.out.println("Objeto Con Contenido 1");
                else
                    System.out.println("Objeto Vacío :( 1");
                new FormComida(tbvTemp, objC,2);
                //ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            });
        }
        else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Confirmando Acción");
                alert.setContentText("¿Deseas eliminar la Bebida :(?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK) {
                    objC = ButtonCellCom.this.getTableView().getItems().get(ButtonCellCom.this.getIndex());
                    if( objC != null )
                        System.out.println("Objeto con Index Control 1");
                    else
                        System.out.println("Ya mejor date de bajan 1");
                    objC.delComida();

                    //Refrescar la tabla
                    ButtonCellCom.this.getTableView().setItems(objC.selAllComidas());
                    ButtonCellCom.this.getTableView().refresh();
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