package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionUsuarios {
    
    public static Connection con;
    private static final String driver="com.mysql.cj.jdbc.Driver";
    private static final String user="root";
    private static final String pass="osca160203";
    private static final String url="jdbc:mysql://localhost/amanecer";

    public Connection conectar()
    {
        con=null;
        try
        {
        con=(Connection) DriverManager.getConnection(url, user, pass);
        if(con!=null){
        }
        }catch(SQLException e)
        {
        JOptionPane.showMessageDialog(null, "Error"+ e.toString());
        }
        return con;
    }
}


