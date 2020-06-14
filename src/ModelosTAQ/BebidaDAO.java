package ModelosTAQ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BebidaDAO
{
    private int id_bebida;
    private String nombre;
    private float precio;
    private String descripcion;


    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
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

    public BebidaDAO()
    {
        con = ConexionTAQ.con;
    }

    public ObservableList<BebidaDAO> selAllBebidas()
    {
        ObservableList<BebidaDAO> listaB = FXCollections.observableArrayList();
        BebidaDAO objB = null;
        String query = "select * from bebida order by id_bebida";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objB = new BebidaDAO();
                objB.setId_bebida(res.getInt("id_bebida"));
                objB.setNombre(res.getString("nombre"));
                objB.setPrecio(res.getFloat("precio"));
                objB.setDescripcion(res.getString("descripcion"));

                listaB.add(objB);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaB;
    }

    public void insBebida()
    {
        String query = "insert into bebida(nombre,precio,descripcion) values('"+nombre+"',"+precio+",'"+ descripcion +"');";
        try {
            Statement stm = con.createStatement();
            stm.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delBebida()
    {
        String query = "delete from bebida where id_bebida="+id_bebida;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){}
    }

    public void updBebida()
    {
        String query = "update bebida set nombre='"+nombre+"'" +
                ",precio="+precio+",descripcion='"+descripcion+"' where " +
                "id_bebida="+id_bebida;
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