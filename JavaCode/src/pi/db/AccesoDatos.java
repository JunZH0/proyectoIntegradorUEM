package pi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {

	public class AccesoDB {
		private String driver;
		private String url;
		
		
		public AccesoDB() {
			driver = "org.sqlite.JDBC"; 
			url = "jdbc:sqlite:db/DDBBPharmaCode.db";
		}
		
		public Connection hacerConexion() throws ClassNotFoundException, SQLException {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(url);
			
			
			return conexion;
		}
	}
}
