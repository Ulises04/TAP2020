package sample.Eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EventoTaquimecanografo implements EventHandler<KeyEvent>
{
    //Button objBoton;
    Button[] arBotones3, arBotones4, arBotones5, arBotones6;
    boolean ban = true;
    int pos;
    int variable;

    public EventoTaquimecanografo(Button[] arreglo3, Button[] arreglo4, Button[] arreglo5, Button[] arreglo6)
    {
        arBotones3 = arreglo3;
        arBotones4 = arreglo4;
        arBotones5 = arreglo5;
        arBotones6 = arreglo6;
    }

    @Override
    public void handle(KeyEvent keyEvent)
    {
        //Object source = keyEvent.getSource();
        //System.out.println("Presione una tecla");
        //System.out.println("Se precionó la tecla: "+keyEvent.getText());//recupera el texto que se genera en el teclado
        System.out.println("Se preciono la tecla: "+keyEvent.getCode().getName());//Recupera el caracter que se teclea en el TextArea
        /*switch (keyEvent.getCode().getName())
        {
            case "0":
                System.out.println("Precionaste el 0");
                break;
            case "Backspace":
                System.out.println("Eliminaste el caracter");
        }*/
        if(keyEvent.getCode() == KeyCode.TAB || keyEvent.getCode() == KeyCode.CAPS || keyEvent.getCode() == KeyCode.SHIFT)
            pos = 0;

        if (keyEvent.getCode() == KeyCode.Q || keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.Z)
            pos = 1;

        if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.X)
            pos = 2;

        if (keyEvent.getCode() == KeyCode.E || keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.C)
            pos = 3;

        if (keyEvent.getCode() == KeyCode.R || keyEvent.getCode() == KeyCode.F || keyEvent.getCode() == KeyCode.V)
            pos = 4;

        if (keyEvent.getCode() == KeyCode.T || keyEvent.getCode() == KeyCode.G || keyEvent.getCode() == KeyCode.B)
            pos = 5;

        if (keyEvent.getCode() == KeyCode.Y || keyEvent.getCode() == KeyCode.H || keyEvent.getCode() == KeyCode.N)
            pos = 6;

        if (keyEvent.getCode() == KeyCode.U || keyEvent.getCode() == KeyCode.J || keyEvent.getCode() == KeyCode.M)
            pos = 7;

        if (keyEvent.getCode() == KeyCode.I || keyEvent.getCode() == KeyCode.K || keyEvent.getCode() == KeyCode.COMMA)
            pos = 8;

        if (keyEvent.getCode() == KeyCode.O || keyEvent.getCode() == KeyCode.L || keyEvent.getCode() == KeyCode.PERIOD)
            pos = 9;

        if (keyEvent.getCode() == KeyCode.P || keyEvent.getCode() == KeyCode.UNDEFINED || keyEvent.getCode() == KeyCode.MINUS)
            pos = 10;

        if (keyEvent.getCode() == KeyCode.DEAD_ACUTE || keyEvent.getCode() == KeyCode.BRACELEFT || keyEvent.getCode() == KeyCode.SHIFT)
            pos = 11;

        if (keyEvent.getCode() == KeyCode.PLUS || keyEvent.getCode() == KeyCode.ENTER)
            pos = 12;

        if (keyEvent.getCode() == KeyCode.BACK_SPACE)
            pos = 13;

        if(keyEvent.getCode() == KeyCode.CAPS)
            pos = 20;

        if (keyEvent.getCode() == KeyCode.A)
            pos = 21;

        if (keyEvent.getCode() == KeyCode.S)
            pos = 22;

        if (keyEvent.getCode() == KeyCode.D)
            pos = 23;

        if (keyEvent.getCode() == KeyCode.F)
            pos = 24;

        if (keyEvent.getCode() == KeyCode.G)
            pos = 25;

        if (keyEvent.getCode() == KeyCode.H)
            pos = 26;

        if (keyEvent.getCode() == KeyCode.J)
            pos = 27;

        if (keyEvent.getCode() == KeyCode.K)
            pos = 28;

        if (keyEvent.getCode() == KeyCode.L)
            pos = 29;

        if (keyEvent.getCode() == KeyCode.UNDEFINED)
            pos = 30;

        if (keyEvent.getCode() == KeyCode.BRACELEFT)
            pos = 31;

        if (keyEvent.getCode() == KeyCode.ENTER)
            pos = 32;

        if(keyEvent.getCode() == KeyCode.SHIFT)
            pos = 40;

        if (keyEvent.getCode() == KeyCode.Z)
            pos = 41;

        if (keyEvent.getCode() == KeyCode.X)
            pos = 42;

        if (keyEvent.getCode() == KeyCode.C)
            pos = 43;

        if (keyEvent.getCode() == KeyCode.V)
            pos = 44;

        if (keyEvent.getCode() == KeyCode.B)
            pos = 45;

        if (keyEvent.getCode() == KeyCode.N)
            pos = 46;

        if (keyEvent.getCode() == KeyCode.M)
            pos = 47;

        if (keyEvent.getCode() == KeyCode.COMMA)
            pos = 48;

        if (keyEvent.getCode() == KeyCode.PERIOD)
            pos = 49;

        if (keyEvent.getCode() == KeyCode.MINUS)
            pos = 50;

        if (keyEvent.getCode() == KeyCode.SHIFT)
            pos = 51;

        if(keyEvent.getCode() == KeyCode.CONTROL)
            pos = 60;

        if (keyEvent.getCode() == KeyCode.FIND)
            pos = 61;

        if (keyEvent.getCode() == KeyCode.WINDOWS)
            pos = 62;

        if (keyEvent.getCode() == KeyCode.ALT)
            pos = 63;

        if (keyEvent.getCode() == KeyCode.SPACE)
            pos = 64;

        if (keyEvent.getCode() == KeyCode.ALT_GRAPH)
            pos = 65;

        if (keyEvent.getCode() == KeyCode.LESS)
            pos = 66;

        if (keyEvent.getCode() == KeyCode.LEFT)
            pos = 67;

        if (keyEvent.getCode() == KeyCode.UP)
            pos = 68;

        if (keyEvent.getCode() == KeyCode.DOWN)
            pos = 69;

        if (keyEvent.getCode() == KeyCode.RIGHT)
            pos = 70;

        System.out.println(pos);

        if(pos >= 0 && pos <= 13 )
        {
            if (ban)
                arBotones3[pos].setStyle("-fx-base: #9E9D00;");
            else
                arBotones3[pos].setStyle("-fx-base: #CCCC00;");
            ban = !ban;
            //pos = 0;
        }
        else
        {
            if(pos >= 20 && pos <=32)
            {
                if (ban)
                    arBotones4[pos-20].setStyle("-fx-base: #9E9D00;");
                else
                    arBotones4[pos-20].setStyle("-fx-base: #CCCC00;");
                ban = !ban;
                //pos = 0;
            }
            else
            {
                if(pos >= 40 && pos <=51)
                {
                    if (ban)
                        arBotones5[pos-40].setStyle("-fx-base: #9E9D00;");
                    else
                        arBotones5[pos-40].setStyle("-fx-base: #CCCC00;");
                    ban = !ban;
                    //pos = 0;
                }
                else
                {
                    if(pos >= 60 && pos <=70)
                    {
                        if (ban)
                            arBotones6[pos-60].setStyle("-fx-base: #9E9D00;");
                        else
                            arBotones6[pos-60].setStyle("-fx-base: #CCCC00;");
                        ban = !ban;
                        //pos = 0;
                    }
                    else
                        System.out.println("Error xD");
                }
            }
        }
    }
}