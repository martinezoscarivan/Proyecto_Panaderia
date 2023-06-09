
package Conexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class RellenarCombo {
    public void RellenarComboBox(String tabla, String valor, JComboBox combo)
    {
        String sql="Select * from " + tabla;
        Statement st;
        ConexionUsuarios con = new ConexionUsuarios();
        Connection conexion= con.conectar();
        try
        {
           st=conexion.createStatement();
           ResultSet rs=st.executeQuery(sql);
           while(rs.next())
            {
               combo.addItem(rs.getString(valor));
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
        }
    }
    
    public void RellenarComboBoxS(String tabla, String valor, JComboBox combo)
    {
        String sql="Select nombre from usuarios where idPuesto=2 ";
        Statement st;
        ConexionUsuarios con = new ConexionUsuarios();
        Connection conexion= con.conectar();
        try
        {
           st=conexion.createStatement();
           ResultSet rs=st.executeQuery(sql);
           while(rs.next())
            {
               combo.addItem(rs.getString(valor));
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
        }
    }   
    public void eliminarregistro(String idProducto)
{
int res=0;
String sql="Delete *from productos where idProductos"+ idProducto;

}
    
    /*public boolean actualizarStock(int cantidad, String stock)
    {
        String sql ="UPDATE productos set stock= ? Where idProducto=?";
        Statement st;
        PreparedStatement ps;
        ConexionUsuarios con = new ConexionUsuarios();
        Connection conexion= con.conectar();
        try
        {
           st=conexion.createStatement();
           ResultSet rs=st.executeQuery(sql);
           while(rs.next())
            {
               ps.setInt(1, cantidad);
               ps.setString(2, stock);
               ps.execute();
               
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
        }
        
    }
    */
    
}


