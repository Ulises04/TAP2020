package ModelosTAQ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CuentaDAO
{

    public float total;

    private int id_taqueria;
    private int id_empleado;
    private int id_comida;
    private int id_bebida;
    private int id_mesa;
    private float precio_final;
    private int cantidad;
    private String nombre;
    private float precio;
    private String descripcion;

    public int getId_taqueria() {
        return id_taqueria;
    }

    public void setId_taqueria(int id_taqueria) {
        this.id_taqueria = id_taqueria;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_comida() {
        return id_comida;
    }

    public void setId_comida(int id_comida) {
        this.id_comida = id_comida;
    }

    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public float getPrecio_final() {
        return precio_final;
    }

    public void setPrecio_final(float precio_final) {
        this.precio_final = precio_final;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public CuentaDAO() { con = ConexionTAQ.con; }

    public ObservableList<CuentaDAO> selAllProductos()
    {
        ObservableList<CuentaDAO> listaPr = FXCollections.observableArrayList();
        CuentaDAO objPr = null;
        String query = "SELECT * FROM comida UNION ( SELECT * FROM bebida );";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objPr = new CuentaDAO();
                objPr.setId_comida(res.getInt("id_comida"));
                objPr.setNombre(res.getString("nombre"));
                objPr.setPrecio(res.getFloat("precio"));
                objPr.setDescripcion(res.getString("descripcion"));

                listaPr.add(objPr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaPr;
    }

    public void insOrden()
    {
            String query = "insert into cuenta " +
                    "(id_taqueria, id_empleado, id_comida, id_bebida, id_mesa , precio, cantidad , precio_final) " +
                    "values(" + id_taqueria + "," + id_empleado + "," + id_comida + "," + id_bebida +"," + id_mesa + "," + precio + "," + cantidad + "," + precio_final + ");";
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CuentaDAO> GenerarTicket()
    {
        ObservableList<CuentaDAO> listaPr = FXCollections.observableArrayList();
        CuentaDAO objPr = null;
        String query = "select nombre, SUM(cantidad) as cantidad ,SUM(c.precio_final) as precio_final " +
                        "from comida co join cuenta c on  c.id_comida = co.id_comida " +
                        "where id_taqueria = "+ id_taqueria+" and id_empleado = " + id_empleado + " and id_mesa = " + id_mesa + "  " +
                        "group by 1 " +
                        "UNION (select nombre, SUM(cantidad) as cantidad ,SUM(c.precio_final) as precio_final " +
                        "from bebida b join cuenta c on  c.id_bebida = b.id_bebida " +
                "where id_taqueria = "+ id_taqueria+" and id_empleado = " + id_empleado + " and id_mesa = " + id_mesa + "  " +
                        "group by 1);";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objPr = new CuentaDAO();
                objPr.setNombre(res.getString("nombre"));
                objPr.setCantidad(res.getInt("cantidad"));
                objPr.setPrecio_final(res.getFloat("precio_final"));

                listaPr.add(objPr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaPr;
    }

    public void GenerarTotal()
    {
        CuentaDAO objPr = null;
        String query = "select SUM(precio_final) as Total " +
                        "from cuenta " +
                        "where id_taqueria = " + id_taqueria + " and id_empleado = " + id_empleado + " and id_mesa = " + id_mesa +";";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                total = res.getFloat("Total");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}