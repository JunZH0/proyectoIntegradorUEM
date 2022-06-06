package pi.model;

public class Ventas {
	private int idVenta;
	private Empleado empleado;
	private String horaVenta;
	private String fechaVenta;
	
	
	public Ventas(int idVenta, Empleado empleado, String horaVenta, String fechaVenta) {
		this.idVenta = idVenta;
		this.empleado = empleado;
		this.horaVenta = horaVenta;
		this.fechaVenta = fechaVenta;
	}


	public int getIdVenta() {
		return idVenta;
	}


	public Empleado getEmpleado() {
		return empleado;
	}


	public String getHoraVenta() {
		return horaVenta;
	}


	public String getFechaVenta() {
		return fechaVenta;
	}
	
	
	
}
