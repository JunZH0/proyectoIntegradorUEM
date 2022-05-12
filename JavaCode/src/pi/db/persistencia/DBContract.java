package src.pi.db.persistencia;

public class DBContract {
	
	//Tabla Empleado:
	public static final String NOM_TAB_EMP = "EMPLEADO";
	public static final String COL_ID_EMP = "ID_EMPLEADO";
	public static final String COL_NOM_EMP = "NOMBRE";
	public static final String COL_APE_EMP = "APELLIDO";
	public static final String COL_TURNO_EMP = "TURNO";
	public static final String COL_ES_ADM_EMP = "ES_ADMIN";
	
	
	//Tabla Producto:
	public static final String NOM_TAB_PROD = "PRODUCTO";
	public static final String COL_ID_PROD = "ID_PRODUCTO";
	public static final String COL_NOM_PROD = "NOM_PROD";
	public static final String COL_DESCR_PROD = "DESCRIPCION";
	public static final String COL_TIPO_PROD = "TIPO";
	public static final String COL_PRECIO_PROD = "PRECIO";
	public static final String COL_STOCK_PROD = "STOCK";
	
	
	//Tabla Proveedor:
	public static final String NOM_TAB_PROV = "PROVEEDOR";
	public static final String COL_ID_PROV = "ID_PROVEEDOR";
	public static final String COL_NOM_PROV = "NOMBRE";
	public static final String COL_CIF_PROV = "CIF";
	public static final String COL_TELF_PROV = "TELEFONO";
	
	
	//Tabla Producto_Proveedor:
	public static final String NOM_TAB_PROD_PROV = "PRODUCTO_PROVEEDOR";
	//cols ya existen
	
	
	//Tabla Ventas:
	public static final String NOM_TAB_VENTA = "VENTAS";
	public static final String COL_ID_VENTA = "ID_VENTA";
	//ID_EMPLEADO
	public static final String COL_HORA_VENTA = "HORA_VENTA";
	public static final String COL_FECHA_VENTA = "FECHA_VENTA";
	
	
	//Tabla Solicitud_Stock:
	public static final String NOM_TAB_SOL_STOCK = "SOLICITUD_STOCK";
	//ID_EMPLEADO
	//ID_PRODUCTO
	public static final String COL_FECHA_PEDIDO = "FECHA_PEDIDO";
	public static final String COL_HORA_PEDIDO = "HORA_PEDIDO";
	public static final String COL_CANT_PEDIDO = "CANTIDAD_PEDIDO";
	
	
	//Tabla Stock_Producto:
	public static final String NOM_TAB_STOCK_PROD_= "STOCK_PRODUCTO";
	//ID_PRODUCTO
	//STOCK
}
