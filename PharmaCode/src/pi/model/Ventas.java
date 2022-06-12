package pi.model;

public class Ventas {
	private int idVenta;
	private int idEmple;
	private String horaVenta;
	private String fechaVenta;
	
	
	public Ventas(int idVenta, int idEmple, String horaVenta, String fechaVenta) {
		this.idVenta = idVenta;
		this.idEmple = idEmple;
		this.horaVenta = horaVenta;
		this.fechaVenta = fechaVenta;
	}


	public int getIdVenta() {
		return idVenta;
	}


	public int getEmpleado() {
		return idEmple;
	}


	public String getHoraVenta() {
		return horaVenta;
	}


	public String getFechaVenta() {
		return fechaVenta;
	}
	
	
	
}
