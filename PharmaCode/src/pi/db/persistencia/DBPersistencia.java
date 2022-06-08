package pi.db.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			System.out.println("Error en la base de datos: error conexiï¿½n, sentencia incorrecta");
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


	public int selecIdProv(String nombreProv) {
		int idProv = 0;
		
		
		String query = "SELECT " + DBContract.COL_ID_PROV + " FROM " + DBContract.NOM_TAB_PROV + " WHERE " + DBContract.COL_NOM_PROV + " LIKE ?";
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nombreProv + "%");
			
			rst = pstmt.executeQuery();
			
			if (rst.next()) { 
				idProv = rst.getInt(DBContract.COL_ID_PROV);
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
				if (pstmt != null) pstmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		return idProv;
	}


	public int registrarProv(Proveedor nuevoProv) {
		int res = 0;
		
		String query = "INSERT INTO " + DBContract.NOM_TAB_PROV + " (" + DBContract.COL_NOM_PROV + ", " + DBContract.COL_CIF_PROV + ", " + DBContract.COL_TELF_PROV + ") VALUES (?, ?, ?)";
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			
			pstm.setString(1, nuevoProv.getNombreProv());
			pstm.setString(2, nuevoProv.getCifProv());
			pstm.setString(3, nuevoProv.getTelefProv());
			
			res = pstm.executeUpdate();
						
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return res;
	}


	public int borrarProv(String nombreProv) {

		int resultado = 0;
		
		String query = "DELETE FROM " + DBContract.NOM_TAB_PROV + " WHERE " + DBContract.COL_NOM_PROV + "=?"; 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.hacerConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombreProv);

			resultado = pstmt.executeUpdate(); 
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) { 
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally { 
			try {
				if (pstmt != null) pstmt.close(); 
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return resultado;
	
	}


	public Proveedor selecionarUnProveedor(String nomProv) {
		Proveedor proveedor = null;
		
		String query = "SELECT * FROM " + DBContract.NOM_TAB_PROV + " WHERE " + DBContract.COL_NOM_PROV + " LIKE ?";
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nomProv + "%");
			
			rst = pstmt.executeQuery();
			
			String nombre;
			String cif;
			String telefono;

			
			if (rst.next()) {
				nombre = rst.getString(DBContract.COL_NOM_PROV);
				cif = rst.getString(DBContract.COL_CIF_PROV);
				telefono = rst.getString(DBContract.COL_TELF_PROV);

				proveedor = new Proveedor(0, nombre, cif, telefono);
				
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
				if (pstmt != null) pstmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		
		return proveedor;
	}


	public int modProveedor(Proveedor modProv) {
		int res = 0;
		
		String query = "UPDATE " + DBContract.NOM_TAB_PROV  + " SET " + DBContract.COL_CIF_PROV + "= ?, " + DBContract.COL_TELF_PROV + "= ? WHERE " + DBContract.COL_NOM_PROV + "= ?";
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			
			pstm.setString(1, modProv.getCifProv());
			pstm.setString(2, modProv.getTelefProv());
			pstm.setString(3, modProv.getNombreProv());
	
			res = pstm.executeUpdate();
						
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexión, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		

		return res;
	}


	public String consultarPwdPorUsuario(String apellidoEmple) {
		String pwd = null;
		
		String query = "SELECT " + DBContract.COL_PWD_EMP + " FROM " + DBContract.NOM_TAB_EMP + " WHERE " + DBContract.COL_APE_EMP + "= ?";
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, apellidoEmple);
			
			rst = pstmt.executeQuery();
			
			if (rst.next()) {
				pwd = rst.getString(DBContract.COL_PWD_EMP);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rst != null) rst.close();
				if (pstmt != null) pstmt.close();
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return pwd;
	}
	
	
	
}
