package pi.model;

public class SolicitudStock {
	private Empleado empleado;
	private Producto producto;
	private String fechaPedido;
	private String horaPedido;
	private int cantidadPedido;
	
	
	public SolicitudStock(Empleado empleado, Producto producto, String fechaPedido, String horaPedido,
			int cantidadPedido) {
		this.empleado = empleado;
		this.producto = producto;
		this.fechaPedido = fechaPedido;
		this.horaPedido = horaPedido;
		this.cantidadPedido = cantidadPedido;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public Producto getProducto() {
		return producto;
	}


	public String getFechaPedido() {
		return fechaPedido;
	}


	public String getHoraPedido() {
		return horaPedido;
	}


	public int getCantidadPedido() {
		return cantidadPedido;
	}
	
	
	
	
}
