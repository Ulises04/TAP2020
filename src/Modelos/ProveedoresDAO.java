package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProveedoresDAO
{
    private int idProveedor;
    private String nomProveedor;
    private String dirProveedor;
    private String telProveedor;
    private String mailProveedor;

    public int getIdProveedor() { return idProveedor; }

    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public String getNomProveedor() { return nomProveedor; }

    public void setNomProveedor(String nomProveedor) { this.nomProveedor = nomProveedor; }

    public String getDirProveedor() { return dirProveedor; }

    public void setDirProveedor(String dirProveedor) { this.dirProveedor = dirProveedor; }

    public String getTelProveedor() { return telProveedor; }

    public void setTelProveedor(String telProveedor) { this.telProveedor = telProveedor; }

    public String getMailProveedor() { return mailProveedor; }

    public void setMailProveedor(String mailProveedor) { this.mailProveedor = mailProveedor; }

    /*public void insProveedor()
    {
        String query = "INSERT INTO tbl_Productos" +
                "(nomProveedor, dirProveedor, telProveedor, mailProveedor) " +
                "values('"+nomProveedor+"','"+dirProveedor+"','"+telProveedor+"','"+mailProveedor+"');";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }*/

    /*public void updProveedor()
    {
        String query = "update tbl_Proveeedores nomProveedor='"+nomProveedor+"'" +
                ",dirProveedor="+dirProveedor+",telProveedor="+dirProveedor+",telProveedor="+telProveedor+"," +
                "mailProveedor="+mailProveedor+" where " + "idProveedor="+idProveedor;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }*/

    /*public void delProveedor()
    {
        String query = "delete from tbl_Proveedores where idProveedor="+idProveedor;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){}
    }*/

    ObservableList<ProveedoresDAO> listaP = FXCollections.observableArrayList();
    ProveedoresDAO objP = null;

    public ObservableList<ProveedoresDAO>  selAllProveedor()
    {
        String query = "SELECT * FROM tbl_Proveedores ORDER BY nomProveedor";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new ProveedoresDAO();
                objP.setIdProveedor(res.getInt("idProveedor"));
                objP.setNomProveedor(res.getString("nomProveedor"));
                objP.setDirProveedor(res.getString("dirProveedor"));
                objP.setTelProveedor(res.getString("telProveedor"));
                objP.setMailProveedor(res.getString("mailProveedor"));
                listaP.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listaP;
    }

    public  void getProvById()
    {
        String query = "SELECT * FROM tbl_Proveedores WHERE idProveedor = "+idProveedor;
        try
        {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if( res.next() )
            {
                nomProveedor = res.getString("nomProveedor");
                dirProveedor = res.getString("dirProveedor");
                telProveedor = res.getString("telProveedor");
                mailProveedor = res.getString("mailProveedor");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nomProveedor + " (" + mailProveedor + ")";
    }
}