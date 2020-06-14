package sample.Componentes;

public class Hilos extends Thread
{
    @Override
    public void run(){
        System.out.println("Inicia Corredor"+getName());
        for (int i = 1; i < 5 ; i++) {
            try {
                sleep( (long)( Math.random() * 2000) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
