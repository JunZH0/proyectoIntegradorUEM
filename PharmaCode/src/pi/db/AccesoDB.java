package pi.db;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AccesoDB {
	private String driver;
	private String url;
	
	
	public AccesoDB() {
		Properties prop = new Properties();
		InputStream is = null;
		
		try {
			is = new FileInputStream("ConfiguracionDB.properties");
			prop.load(is); 
			driver = prop.getProperty("DRIVER");
			url = prop.getProperty("URL");
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public Connection hacerConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection conexion = DriverManager.getConnection(url);
		
		return conexion;
	}
	
	
}