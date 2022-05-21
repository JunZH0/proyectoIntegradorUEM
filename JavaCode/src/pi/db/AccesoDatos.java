package pi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {

	public class AccesoDB {
		private String driver;
		private String url;
		
		
		public AccesoDB() {
			/*driver = "org.sqlite.JDBC"; 
			url = "jdbc:sqlite:db/DDBBPharmaCode.db";*/
			
			Properties prop = new Properties();
			InputStream is = null;
		}
		
		try {
			is = new FileInputStream("ConfiguracionDB.properties"); //provoca excepción por si no encuentra el fichero
			prop.load(is); //esto también provocará excepción si hay problema a la hora de leer el fichero
			
			//tal como lo escribamos en el fichero .properties, se lo pasamos como String a ambos atributos:
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
}
