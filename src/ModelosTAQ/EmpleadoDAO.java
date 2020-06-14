package ModelosTAQ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadoDAO
{
    public int id_empleado;
    private String nombre;
    private String password;
    private String apaterno;
    private String amaterno;
    private String telefono;
    public int id_taqueria;

    public String nombreEmp = "";
    public String taqueria, direccion, telefonoTAQ;

    private EmpleadoDAO objEm;

    public int bandera=0;

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_taqueria() {
        return id_taqueria;
    }

    public void setId_taqueria(int id_taqueria) {
        this.id_taqueria = id_taqueria;
    }

    private Connection con;

    public EmpleadoDAO(){
        con = ConexionTAQ.con;
    }


    public int sellOneEmpleado()
    {

        String query = "select id_empleado, id_taqueria, nombre, password from empleado where nombre='"+nombre+"' and password='"+password+"';";

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                objEm = new EmpleadoDAO();
                objEm.setId_empleado(res.getInt("id_empleado"));
                objEm.setId_taqueria(res.getInt("id_taqueria"));
                bandera = 1;
                id_empleado = objEm.getId_empleado();
                id_taqueria = objEm.getId_taqueria();
            }
            else {
                bandera = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bandera;
    }

    public int DatosTicket()
    {

        String query = "select * from empleado where id_empleado='"+id_empleado+"';";

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                objEm = new EmpleadoDAO();
                objEm.setNombre(res.getString("nombre"));
                objEm.setApaterno(res.getString("apaterno"));
                objEm.setAmaterno(res.getString("amaterno"));
                nombreEmp =  objEm.getNombre()+" "+objEm.getApaterno()+" "+objEm.getAmaterno();
            }
            else {
                bandera = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bandera;
    }

    public ObservableList<EmpleadoDAO> selAllEmpleados()
    {
        ObservableList<EmpleadoDAO> listaEm = FXCollections.observableArrayList();
        EmpleadoDAO objEm = null;
        String query = "select * from empleado order by id_empleado";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objEm = new EmpleadoDAO();
                objEm.setId_empleado(res.getInt("id_empleado"));
                objEm.setNombre(res.getString("nombre"));
                objEm.setPassword(res.getString("password"));
                objEm.setApaterno(res.getString("apaterno"));
                objEm.setAmaterno(res.getString("amaterno"));
                objEm.setTelefono(res.getString("telefono"));
                objEm.setId_taqueria(res.getInt("id_taqueria"));

                listaEm.add(objEm);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaEm;
    }

    public void insEmpleado()
    {
        String query = "insert into empleado " +
                "(nombre,password,apaterno,amaterno,telefono,id_taqueria) " +
                "values('"+nombre+"','"+password+"','"+apaterno+"','"+amaterno+"','"+telefono+"',"+id_taqueria+");";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delEmpleado()
    {
        String query = "delete from empleado where id_empleado="+ id_empleado+";";
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch (Exception e){}
    }

    public void updEmpleado()
    {
        String query = "update empleado set nombre='"+nombre+"'" +
                ",password='"+password+"',apaterno='"+apaterno+"',amaterno='"+amaterno+"',telefono='"+telefono+"'" +
                ",id_taqueria="+id_taqueria+" where " + "id_empleado="+ id_empleado+";";
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

    public int DatosTAQ()
    {

        String query = "select t.nombre, direccion, t.telefono " +
                        "from taqueria t join empleado e on t.id_taqueria = e.id_taqueria " +
                        "where id_empleado = "+id_empleado+";";

        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                objEm = new EmpleadoDAO();
                taqueria = res.getString("nombre");
                direccion = res.getString("direccion");
                telefonoTAQ = res.getString("telefono");
            }
            else {
                bandera = 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bandera;
    }
}
