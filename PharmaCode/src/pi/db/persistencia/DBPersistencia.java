package pi.db.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pi.db.AccesoDB;
import pi.model.Proveedor;

public class DBPersistencia {
	
	private AccesoDB acceso;
	
	
	public DBPersistencia() {
		acceso  = new AccesoDB();
	}


	public ArrayList<Proveedor> seleccionarProveedores() {
		ArrayList<Proveedor> listaProv = new ArrayList<Proveedor>();
		
		String query = "SELECT * FROM " + DBContract.NOM_TAB_PROV + " ORDER BY " + DBContract.COL_NOM_PROV;
		
		Connection conexion = null;
		Statement stmt = null;
		ResultSet rst = null;
		

		try {
			conexion = acceso.hacerConexion();
			stmt = conexion.createStatement();
			rst = stmt.executeQuery(query);
			
			Proveedor proveedor;
			
			int id;
			String nombre;
			String cif;
			String telefono;
			
			while (rst.next()) {
				id = rst.getInt(DBContract.COL_ID_PROV);
				nombre = rst.getString(DBContract.COL_NOM_PROV);
				cif = rst.getString(DBContract.COL_CIF_PROV);
				telefono =  rst.getString(DBContract.COL_TELF_PROV);
				
				proveedor = new Proveedor(id, nombre, cif, telefono);
				listaProv.add(proveedor);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (stmt != null) stmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaProv;
	}
	
	
	
}
