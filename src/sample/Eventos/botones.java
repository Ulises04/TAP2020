package sample.Eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

class botones implements EventHandler<MouseEvent> {
    int x, y;
    Button boton;
    boolean bandera;

    public botones(Button owo, int x, int y) {
        this.x = x;
        this.y = y;
        boton = owo;
    }

    @Override
    public void handle(MouseEvent event) {
        int aux = 0;
        aux = Integer.parseInt(EventoBuscaminas.lblBombas.getText());
        if (event.getButton() == MouseButton.SECONDARY) {
            if (boton.getGraphic() == null && aux > 0) {
                ImageView a = new ImageView("sample/Imagenes/band.png");
                a.setFitWidth(30);
                a.setFitHeight(30);
                boton.setGraphic(a);
                boton.setId(Integer.toString(EventoBuscaminas.id));
                EventoBuscaminas.id++;
                aux--;
            } else {
                boton.setGraphic(null);
                boton.setId("0");
                aux++;
            }
            EventoBuscaminas.lblBombas.setText(Integer.toString(aux));
        } else {

            if (EventoBuscaminas.arr2[x][y] == -1) {
                ImageView imageView = new ImageView("sample/Imagenes/minaexp.png");
                imageView.setFitHeight(30);
                imageView.setFitWidth(30);
                for (int i = 0; i < EventoBuscaminas.arBtnCeldas.length; i++) {
                    for (int j = 0; j < EventoBuscaminas.arBtnCeldas[i].length; j++) {
                        EventoBuscaminas.arBtnCeldas[i][j].setDisable(true);
                        if(EventoBuscaminas.arr2[i][j] == -1){
                            ImageView img2 = new ImageView("sample/Imagenes/minanormal.png");
                            img2.setFitWidth(30);
                            img2.setFitHeight(30);
                            EventoBuscaminas.arBtnCeldas[i][j].setGraphic(img2);
                        }
                    }
                }
                EventoBuscaminas.arBtnCeldas[x][y].setGraphic(imageView);
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Fin del juego");
                a.setContentText("Perdiste carnal, no sirves para esto...");
                a.show();

            } else {
                buscarMinitas(x,y);
            }

        }

    }

    private boolean isBordes(int x, int y){
        return ((x >= 0) && (y>=0) && (x < EventoBuscaminas.arr2.length) && (y < EventoBuscaminas.arr2[0].length ));
    }

    private void buscarMinitas(int x, int y) {
        int a = EventoBuscaminas.arr2[x][y];
        if (EventoBuscaminas.arr2[x][y] >= 0) {
            EventoBuscaminas.arBtnCeldas[x][y].setGraphic(new ImageView(String.format("sample/Imagenes/%o.PNG", EventoBuscaminas.arr2[x][y])));
            EventoBuscaminas.arBtnCeldas[x][y].setDisable(true);
        }
        if (a == 0) {
            for (int i = (x - 1); i <= (x + 1); i++) {
                for (int j = (y - 1); j <= (y + 1); j++) {
                    if ( isBordes(i,j) && EventoBuscaminas.arBtnCeldas[i][j].getGraphic()==null) {
                        Button b = EventoBuscaminas.arBtnCeldas[i][j];
                        if (!b.isDisable()) {
                            if (EventoBuscaminas.arr2[i][j] > 0) {
                                b.setGraphic(new ImageView(String.format("sample/Imagenes/%o.PNG", EventoBuscaminas.arr2[i][j])));
                                b.setDisable(true);
                            } else if (EventoBuscaminas.arr2[i][j] == 0) {
                                buscarMinitas(i, j);
                            }
                        }
                    }
                }
            }

        }
    }
}