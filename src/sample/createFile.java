package sample;

import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class createFile {

    private int col,arrw;
    private String PATH = "Buscaminas.dat";
    private  RandomAccessFile file;
    private double MINAS;
    private int minas;
    private int minaux;

    public createFile(int col, int arrw){
        this.col = col;
        this.arrw = arrw;
        MINAS = 0.16;
    }

    public int[][] createArr(){
        minas = (int)((col*arrw)*MINAS);
        minaux = minas;
        int[][] array = new int [arrw][col];
        // poner minas
        while(minas > 0){
            int x = (int)(Math.random() * arrw);
            int y = (int)(Math.random() * col);
            if (array[x][y] != -1){
                array[x][y] = -1;
                minas--;
            }

        }
        for (int i = 0; i <arrw ; i++) {
            for (int j = 0; j <col ; j++) {
                array[i][j] = array[i][j]!=-1?0:-1;
            }
        }
        return array;
    }

    public int[][] fixArr(int[][] array){

        for (int i = 0; i < arrw; i++) {
            for (int j = 0; j < col; j++) {
                int con = 0;
                if(array[i][j] == 0){
                    if(i == 0 && j == 0){
                        if(array[i][j+1] == -1)
                            con+=1;
                        if(array[i+1][j] == -1)
                            con+=1;
                        if(array[i+1][j+1] == -1)
                            con+=1;
                    }else if(i == 0 && j == (col-1)){
                        if(array[i][j-1] == -1)
                            con+=1;
                        if(array[i+1][j] == -1)
                            con+=1;
                        if(array[i+1][j-1] == -1)
                            con+=1;
                    }else if(i == (arrw-1) && j == 0){
                        if(array[i-1][j] == -1)
                            con+=1;
                        if(array[i][j+1] == -1)
                            con+=1;
                        if(array[i-1][j+1] == -1)
                            con+=1;
                    }else if(i == (arrw-1) && j == (col-1)){
                        if(array[i-1][j] == -1)
                            con+=1;
                        if(array[i][j-1] == -1)
                            con+=1;
                        if(array[i-1][j-1] == -1)
                            con+=1;
                    }else if(i == 0  && j < (col-1)){
                        con += array[i][j-1] == -1?1:0;
                        con += array[i+1][j-1] == -1?1:0;
                        con += array[i+1][j] == -1?1:0;
                        con += array[i+1][j+1] == -1?1:0;
                        con += array[i][j+1] == -1?1:0;
                    }else if(i < (arrw-1) && j == (col-1)){
                        con += array[i-1][j] == -1?1:0;
                        con += array[i-1][j-1] == -1?1:0;
                        con += array[i][j-1] == -1?1:0;
                        con += array[i+1][j-1] == -1?1:0;
                        con += array[i+1][j] == -1?1:0;
                    }else if(i == (arrw-1) && j < (col-1)){
                        con += array[i][j-1] == -1?1:0;
                        con += array[i-1][j-1] == -1?1:0;
                        con += array[i-1][j] == -1?1:0;
                        con += array[i-1][j+1] == -1?1:0;
                        con += array[i][j+1] == -1?1:0;
                    }else if(i < (arrw-1) && j == 0){
                        con += array[i-1][j] == -1?1:0;
                        con += array[i-1][j+1] == -1?1:0;
                        con += array[i][j+1] == -1?1:0;
                        con += array[i+1][j+1] == -1?1:0;
                        con += array[i+1][j] == -1?1:0;
                    }else{
                        con += array[i-1][j] == -1?1:0;
                        con += array[i-1][j+1] == -1?1:0;
                        con += array[i][j+1] == -1?1:0;
                        con += array[i+1][j+1] == -1?1:0;
                        con += array[i+1][j] == -1?1:0;
                        con += array[i+1][j-1] == -1?1:0;
                        con += array[i][j-1] == -1?1:0;
                        con += array[i-1][j-1] == -1?1:0;
                    }
                    //System.out.println( con);
                    array[i][j] = con;
                }
            }
        }

        return array;
    }

    public int print_array(int[][] array){
        for (int i = 0; i <array.length ; i++) {
            for (int j = 0; j <array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        return minaux;
    }

    public RandomAccessFile createFile(int[][] array){
        String cadena = "";
        for (int i = 0; i < array.length; i++) {
            cadena += "(";
            for (int j = 0; j < array[0].length; j++) {
                cadena += array[i][j];
                if(j != col-1){
                    cadena +=  " , ";
                }
            }
            cadena += ")\n";

        }
        try{
            file = new RandomAccessFile(PATH, "rw");
            //String cadena = file.readLine();
            file.writeUTF(cadena);


        }catch (Exception e){
            e.printStackTrace();
        }
        return  file;
    }

    public void readFile(RandomAccessFile a){
        try{
            a.seek(0);
            while(true){
                System.out.println(a.readUTF());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}