package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion
{
    private static final String host = "localhost";
    private static final String user = "root";
    private static final String pwd = "12345";
    private static final String db = "oxxo";
    public static Connection con;

    public static void  crearConexion()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //Estamos abriendo el socket hacia el SGBD
            con = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+db+"?useSSl=false",user,pwd);
            System.out.println("Se iniciado la conexión con la base de datos");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}