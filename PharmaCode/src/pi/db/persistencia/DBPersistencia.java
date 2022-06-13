package pi.db.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pi.db.AccesoDB;
import pi.model.Empleado;
import pi.model.Producto;
import pi.model.Proveedor;
import pi.model.Ventas;

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
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
	
	public ArrayList<Empleado> seleccionarEmpleados() {
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
		
		String query = "SELECT * FROM " + DBContract.NOM_TAB_EMP + " ORDER BY " + DBContract.COL_NOM_EMP;
		
		Connection conexion = null;
		Statement stmt = null;
		ResultSet rst = null;

		try {
			conexion = acceso.hacerConexion();
			stmt = conexion.createStatement();
			rst = stmt.executeQuery(query);
			
			Empleado empleado;
			
			int id;
			String nombre;
			String apellido;
			String turno;
			String admin;
			
			while (rst.next()) {
				id = rst.getInt(DBContract.COL_ID_EMP);
				nombre = rst.getString(DBContract.COL_NOM_EMP);
				apellido =  rst.getString(DBContract.COL_APE_EMP);
				turno = rst.getString(DBContract.COL_TURNO_EMP);
				admin = rst.getString(DBContract.COL_ES_ADM_EMP);
				
				empleado = new Empleado(id, nombre, apellido, turno, admin, null);
				listaEmpleados.add(empleado);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
		
		return listaEmpleados;
		
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
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
	
	public int selecIdEmple(String nombreEmple) {
		int idEmple = 0;
		
		String query = "SELECT " + DBContract.COL_ID_EMP 
						+ " FROM " + DBContract.NOM_TAB_EMP 
						+ " WHERE " + DBContract.COL_NOM_EMP + " LIKE ?";
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nombreEmple + "%");
			
			rst = pstmt.executeQuery();
			
			if (rst.next()) { 
				idEmple = rst.getInt(DBContract.COL_ID_EMP);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
		
		return idEmple;
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
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
		
		public int registrarEmpleado(Empleado nuevoEmple) {
			int res = 0;
			
			String query = "INSERT INTO " + DBContract.NOM_TAB_EMP 
							+ " (" + DBContract.COL_NOM_EMP + ", " 
							+ DBContract.COL_ID_EMP + ", " 
							+ DBContract.COL_APE_EMP + ", "
							+ DBContract.COL_TURNO_EMP + ", "
							+ DBContract.COL_ES_ADM_EMP + ", "
							+ DBContract.COL_PWD_EMP + ") VALUES (?, ?, ?, ?, ?, ?)";
			
			Connection conexion = null;
			PreparedStatement pstm = null;
			
			try {
				conexion = acceso.hacerConexion();
				pstm = conexion.prepareStatement(query);
				
				pstm.setString(1, nuevoEmple.getNombreEmple());
				pstm.setInt(2, nuevoEmple.getIdEmpleado());
				pstm.setString(3, nuevoEmple.getApellidoEmple());
				pstm.setString(4, nuevoEmple.getTurnoEmple());
				pstm.setString(5, nuevoEmple.getAdminEmple());
				pstm.setString(6, nuevoEmple.getPassword());
				
				res = pstm.executeUpdate();
			
			} catch (ClassNotFoundException e) {
				System.out.println("El driver indicado no es correcto");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
	
	public int borrarEmple(String nombreEmple) {

		int resultado = 0;
		
		String query = "DELETE FROM " + DBContract.NOM_TAB_EMP
						+ " WHERE " + DBContract.COL_NOM_EMP + "=?"; 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.hacerConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombreEmple);
			
			resultado = pstmt.executeUpdate(); 
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) { 
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
			System.out.println("Error en la base de datos: error conexion, sentencia incorrecta");

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
	
	public Empleado selecionarUnEmpleado(String nomEmple) {
		Empleado empleado = null;
		
		String query = "SELECT * FROM " + DBContract.NOM_TAB_EMP
					+ " WHERE " + DBContract.COL_NOM_EMP + " LIKE ?";
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nomEmple + "%");
			
			rst = pstmt.executeQuery();
			
			String nombre;
			String apellido;
			int id;

			
			if (rst.next()) {
				nombre = rst.getString(DBContract.COL_NOM_EMP);
				id = rst.getInt(DBContract.COL_ID_EMP);
				apellido = rst.getString(DBContract.COL_APE_EMP);

				empleado = new Empleado(id, nombre, apellido);
				
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");

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

		
		
		return empleado;
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
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
	
	public int modEmpleado(Empleado modEmple) {
		int res = 0;
		
		String query = "UPDATE " + DBContract.NOM_TAB_EMP  
						+ " SET " + DBContract.COL_ID_EMP 
						+ "= ?, " + DBContract.COL_APE_EMP 
						+ "= ? WHERE " + DBContract.COL_NOM_EMP + "= ?";
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			
			pstm.setInt(1, modEmple.getIdEmpleado());
			pstm.setString(2, modEmple.getApellidoEmple());
			pstm.setString(3, modEmple.getNombreEmple());
			
			res = pstm.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
	
	public int registrarProd(Producto nuevoProd) {
		

		String query = "INSERT INTO " + DBContract.NOM_TAB_PROD + " (" +  DBContract.COL_NOM_PROD + ", " + DBContract.COL_DESCR_PROD + ", " + DBContract.COL_TIPO_PROD 
				+ ", " + DBContract.COL_PRECIO_PROD + ", " + DBContract.COL_STOCK_PROD + ") VALUES (?, ?, ?, ?, ?)";
		



		
		Connection conexion = null;
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			
			pstm.setString(1, nuevoProd.getNombreProd());
			pstm.setString(2, nuevoProd.getDescrProd());
			pstm.setString(3, nuevoProd.getTipo());
			pstm.setDouble(4, nuevoProd.getPrecioProd());
			pstm.setInt(5, nuevoProd.getStockProd());
			
			res = pstm.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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

	
	public ArrayList<String> getTiposProd() {
		ArrayList<String> listTipo = new ArrayList<String>();
		
		String query = "SELECT DISTINCT " + DBContract.COL_TIPO_PROD + " FROM " + DBContract.NOM_TAB_PROD;
		String tipo;
		
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rslt = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			rslt = pstm.executeQuery();
			
			while(rslt.next()) {
				tipo = rslt.getString(DBContract.COL_TIPO_PROD);
				
				listTipo.add(tipo);
			}
						
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if(rslt != null) rslt.close();
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		

		return listTipo;
	}


	public ArrayList<Producto> seleccionarProducto() {
		ArrayList<Producto> listProd = new ArrayList<>();
		
		Producto producto;
		int id = 0;
		String nombre;
		String descripcion = null;
		String tipo;
		int stock;
		double precio;
		
		String query = "SELECT " + DBContract.COL_NOM_PROD + ", " + DBContract.COL_TIPO_PROD + ", " + DBContract.COL_STOCK_PROD + ", " + DBContract.COL_PRECIO_PROD + " FROM " + DBContract.NOM_TAB_PROD;
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rslt = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			rslt = pstm.executeQuery();
			
			while(rslt.next()) {
				nombre = rslt.getString(DBContract.COL_NOM_PROD);
				tipo = rslt.getString(DBContract.COL_TIPO_PROD);
				stock = rslt.getInt(DBContract.COL_STOCK_PROD);
				precio = rslt.getDouble(DBContract.COL_PRECIO_PROD);
				
				producto = new Producto(id,nombre, descripcion , tipo, precio, stock);
				
				listProd.add(producto);
				
			}
						
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return listProd;

	}
	
	public Producto seleccionarUnProducto(String prod) {
		
		Producto producto = null;
		
		
		
		String nombre;
		String descripcion = null;
		String tipo;
		int stock;
		double precio;
		
		String query = "SELECT * FROM " + DBContract.NOM_TAB_PROD + " WHERE " + DBContract.COL_NOM_PROD + " LIKE ?";
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rslt = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			pstm.setString(1, prod + "%");
			rslt = pstm.executeQuery();
			
			if(rslt.next()) {
				nombre = rslt.getString(DBContract.COL_NOM_PROD);
				descripcion = rslt.getString(DBContract.COL_DESCR_PROD);
				tipo = rslt.getString(DBContract.COL_TIPO_PROD);
				stock = rslt.getInt(DBContract.COL_STOCK_PROD);
				precio = rslt.getDouble(DBContract.COL_PRECIO_PROD);
				
				producto = new Producto(0,nombre, descripcion , tipo, precio, stock);
				
				
				
			}
						
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close(); 
				if (conexion != null) conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		
		return producto;

	}
	
	public String comprobarExistProd(String nomProd) {
	
		
		String nombre = "";
		
		String query = "SELECT * FROM " + DBContract.NOM_TAB_PROD + " WHERE " + DBContract.COL_NOM_PROD + " LIKE ?";
		
		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setString(1, nomProd + "%");
			
			rst = pstmt.executeQuery();
			
			if (rst.next()) {
				nombre = rst.getString(DBContract.COL_NOM_PROD);
				
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
		
		return nombre;

	}
	
	
	
	public int selectIdProducto(int idProd) {
		int idProducto = 0;
		
		String query = "SELECT " + DBContract.COL_ID_PROD + " FROM " + DBContract.NOM_TAB_PROD + "  WHERE " + DBContract.COL_ID_PROD + " = ?";
		

		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, idProd);
			
			rst = pstmt.executeQuery();
			
			if (rst.next()) { 
				idProducto = rst.getInt(DBContract.COL_ID_PROD);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
		
		return idProducto;
	}


	public int selectIdVenta(int idVenta) {
		int IdEmple = 0;
		
		String query = "SELECT " + DBContract.COL_ID_VENTA + " FROM " + DBContract.NOM_TAB_VENTA + "  WHERE " + DBContract.COL_ID_EMP + " = ?";
		

		Connection conexion = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstmt = conexion.prepareStatement(query);
			pstmt.setInt(1, idVenta);
			
			rst = pstmt.executeQuery();
			
			if (rst.next()) { 
				IdEmple = rst.getInt(DBContract.COL_ID_VENTA);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
		
		return IdEmple;
	}


	public int registrarVenta(Ventas venta) {
		int res = 0;
		
		String query = "INSERT INTO " + DBContract.NOM_TAB_VENTA + " (" + DBContract.COL_ID_EMP + ", " + DBContract.COL_HORA_VENTA + ", " + DBContract.COL_FECHA_VENTA + ") VALUES (?, ?, ?)";
		
		
		Connection conexion = null;
		PreparedStatement pstm = null;
		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			
			pstm.setInt(1, venta.getEmpleado());
			pstm.setString(2, venta.getHoraVenta());
			pstm.setString(3, venta.getFechaVenta());
			
			res = pstm.executeUpdate();
						
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
	
	public int borrarProd(String nombreProd) {

		int resultado = 0;
		
		String query = "DELETE FROM " + DBContract.NOM_TAB_PROD + " WHERE " + DBContract.COL_NOM_PROD + "=?"; 
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.hacerConexion();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nombreProd);

			resultado = pstmt.executeUpdate(); 
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) { 
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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


	public ArrayList<Ventas> seleccionarVentas() {
		ArrayList<Ventas> listaVentas = new ArrayList<Ventas>();
		
		String query = "SELECT * FROM " + DBContract.NOM_TAB_VENTA + " ORDER  BY " + DBContract.COL_FECHA_VENTA + " DESC";
		
		Connection conexion = null;
		Statement stmt = null;
		ResultSet rst = null;
		

		try {
			conexion = acceso.hacerConexion();
			stmt = conexion.createStatement();
			rst = stmt.executeQuery(query);
			
			Ventas venta;
			
			int idVenta;
			int idEmple;
			String hora;
			String fecha;
			
			while (rst.next()) {
				idVenta = rst.getInt(DBContract.COL_ID_VENTA);
				idEmple = rst.getInt(DBContract.COL_ID_EMP);
				hora = rst.getString(DBContract.COL_HORA_VENTA);
				fecha =  rst.getString(DBContract.COL_FECHA_VENTA);
				
				venta = new Ventas(idVenta, idEmple, hora, fecha);
				listaVentas.add(venta);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
		
		
		return listaVentas;
	}
	
	public int modProducto(Producto modProd) {
		
		int res = 0;
		
		String query = "UPDATE " + DBContract.NOM_TAB_PROD  + " SET " + DBContract.COL_TIPO_PROD + "= ?, " 
		+ DBContract.COL_DESCR_PROD + "= ? ," + DBContract.COL_PRECIO_PROD + "= ? ,"  + DBContract.COL_STOCK_PROD + "= ? " + 
				" WHERE " + DBContract.COL_NOM_PROD + "= ?";
					
		Connection conexion = null;
		PreparedStatement pstm = null;

		
		try {
			conexion = acceso.hacerConexion();
			pstm = conexion.prepareStatement(query);
			

			pstm.setString(1, modProd.getTipo());
			pstm.setString(2, modProd.getDescrProd());
			pstm.setDouble(3, modProd.getPrecioProd());
			pstm.setInt(4, modProd.getStockProd());
			pstm.setString(5,  modProd.getNombreProd());
	
			res = pstm.executeUpdate();
						

						

		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: error conexi�n, sentencia incorrecta");
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
	
	
	
}
