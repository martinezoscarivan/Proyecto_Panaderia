
package Conexion;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Venta {
    int id;
    String descripcion;
    int cantidad;
    int eliminar;
    double precio;
    double importe;  
  
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Connection getConnection() {
        try {
            String myBD = "jdbc:mysql://localhost:3306/amanecer?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", "osca160203");
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public Venta() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEliminar(int eliminar) {
        this.eliminar = eliminar;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getEliminar() {
        return eliminar;
    }

    public double getPrecio() {
        return precio;
    }

    public double getImporte() {
        return importe;
    }
    
   public boolean ActualizarStock(int cant, int id){
        String sql = "UPDATE producto SET stock = ? WHERE idProducto= ?";
        try {
            getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cant);
            ps.setInt(2, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
}


