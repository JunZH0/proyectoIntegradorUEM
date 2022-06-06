package pi.model;

public class Proveedor {
	private int idProv;
	private String nombreProv;
	private String cifProv;
	private String telefProv;
	
	
	
	public Proveedor(int idProv, String nombreProv, String cifProv, String telefProv) {
		this.idProv = idProv;
		this.nombreProv = nombreProv;
		this.cifProv = cifProv;
		this.telefProv = telefProv;
	}
	
	
	
	public int getIdProv() {
		return idProv;
	}
	public String getNombreProv() {
		return nombreProv;
	}
	public String getCifProv() {
		return cifProv;
	}
	public String getTelefProv() {
		return telefProv;
	}
	
	
}
