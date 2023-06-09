
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

        static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        private final String user;
	private final String password;
	private final String url;
	private final String driverClassName;
	private Connection conn = null;
	private Statement stmt;
    
        //MÃ©todo para definir las variables de conexiÃ³n a la base de datos
        public Conexion() {
                //usuario de mysql
		this.user = "root";
                //password de mysql
		this.password = "osca160203";
                //ruta de la base de datos
		this.url = "jdbc:mysql://localhost/amanecer";
                //nombre del controlador de mysql segÃºn la versiÃ³n
		this.driverClassName =  "com.mysql.cj.jdbc.Driver"; // driver
	}
    //MÃ©todo para establecer la conexiÃ³n a la base de datos
        public void conectar() throws SQLException {
		try {
			Class.forName(this.driverClassName).newInstance();
			this.conn = DriverManager.getConnection(this.url, this.user,this.password);

		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException err) {
			System.out.println("Error " + err.getMessage());
		}
	}
        //MÃ©todo para realizar consultas a la base de datos
        public ResultSet obtenerDatos(String sql) throws SQLException {

		this.stmt = conn.createStatement();
		return this.stmt.executeQuery(sql);
	}
        //MÃ©todo para realizar cualquier operaciÃ³n de actualizaciÃ³n, inserciÃ³n y eliminaciÃ³n de datos en la BD
        public void actualizar(String sql) throws SQLException {
		this.stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	}
        //MÃ©todo para cerrar la Base de Datos
        public void Off() throws SQLException {
		this.conn.close();
}
}