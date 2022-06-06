package pi.model;

public class Empleado {
	private int idEmpleado;
	private String nombreEmple;
	private String apellidoEmple;
	private String turnoEmple;
	private int adminEmple; //hará falta traducir empleado?
	
	
	public Empleado(int idEmpleado, String nombreEmple, String apellidoEmple, String turnoEmple, int adminEmple) {
		this.idEmpleado = idEmpleado;
		this.nombreEmple = nombreEmple;
		this.apellidoEmple = apellidoEmple;
		this.turnoEmple = turnoEmple;
		this.adminEmple = adminEmple;
	}


	public int getIdEmpleado() {
		return idEmpleado;
	}


	public String getNombreEmple() {
		return nombreEmple;
	}


	public String getApellidoEmple() {
		return apellidoEmple;
	}


	public String getTurnoEmple() {
		return turnoEmple;
	}


	public int getAdminEmple() {
		return adminEmple;
	}
	
	
	
}
