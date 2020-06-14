package ModelosTAQ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ComidaDAO
{
    private int id_comida;
    private String nombre;
    private float precio;
    private String descripcion;

    public int getId_comida() {
        return id_comida;
    }

    public void setId_comida(int id_comida) {
        this.id_comida = id_comida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    private Connection con;
    public ComidaDAO()
    {
        con = ConexionTAQ.con;
    }

    public ObservableList<ComidaDAO> selAllComidas()
    {
        ObservableList<ComidaDAO> listaC = FXCollections.observableArrayList();
        ComidaDAO objC = null;
        String query = "select * from comida order by id_comida";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new ComidaDAO();
                objC.setId_comida(res.getInt("id_comida"));
                objC.setNombre(res.getString("nombre"));
                objC.setPrecio(res.getFloat("precio"));
                objC.setDescripcion(res.getString("descripcion"));

                listaC.add(objC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaC;
    }

    public void insComida()
    {
        String query = "insert into comida " +
                "(nombre,precio,descripcion) " +
                "values('"+nombre+"',"+precio+",'"+ descripcion +"');";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delComida()
    {
        String query = "delete from comida where id_comida="+ id_comida;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){}
    }

    public void updComida()
    {
        String query = "update comida set nombre='"+nombre+"'" +
                ",precio="+precio+",descripcion='"+descripcion+"' where " +
                "id_comida="+ id_comida;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}