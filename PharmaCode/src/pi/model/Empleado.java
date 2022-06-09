package pi.model;

public class Empleado {
	private int idEmpleado;
	private String nombreEmple;
	private String apellidoEmple;
	private String turnoEmple;
	private String adminEmple;
	private String password;
	
	
	public Empleado(int idEmpleado, String nombreEmple, String apellidoEmple, String turnoEmple, String adminEmple, String password) {
		this.idEmpleado = idEmpleado;
		this.nombreEmple = nombreEmple;
		this.apellidoEmple = apellidoEmple;
		this.turnoEmple = turnoEmple;
		this.adminEmple = adminEmple;
		this.password =  password;
	}
	
	public Empleado(String apellidoEmple, String password) {
		this.apellidoEmple = apellidoEmple;
		this.password =  password;
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


	public String getAdminEmple() {
		return adminEmple;
	}
	
	public String getPassword() {
		return password;
	}


	public static String validarPassword(String usuario, String pwd) {
		// Comprobar que no esté vacía, que no coincida con el nombre de usuario, que tenga mínimo 8 caracteres
		// y máximo 20, que contenga al menos 
		// una mayúscula, un número y no contenga caracteres distintos de letras, números y &,+,_,*, 
		String error = "";
		
		if (pwd.isEmpty()) {
			error = "Debe introducir la password";
			
		} else if (pwd.length() < 8 || pwd.length() > 20) {
			error = "La password debe contener entre 8 y 20 caracteres";
			
		} else if (pwd.equals(usuario)) {
			error = "La password no puede coincidir con el nombre de usuario";
			
		} else if (!contieneMayus(pwd) || !contieneNum(pwd) || contieneCarEsp(pwd)) {
			error = "La password debe contener al menos un caracter en mayúsculas, " 
					+ "un número y no puede contener caracteres especiales salvo &, +, _ y *";
			
		} 
		
		return error;
	}
	
	private static boolean contieneMayus(String pwd) {
		boolean contieneMayus = false;
		String pwdMayus = pwd.toUpperCase();
		for (int i = 0; i < pwd.length() && !contieneMayus; i++) {
			if (pwd.charAt(i) == pwdMayus.charAt(i) && !Character.isDigit(pwd.charAt(i))) {
				contieneMayus = true;
			}
		}
		return contieneMayus;
	}
	
	private static boolean contieneNum(String pwd) {
		boolean contieneNum = false;
		for (int i = 0; i < pwd.length() && !contieneNum; i++) {
			if (Character.isDigit(pwd.charAt(i))) {
				contieneNum = true;
			}
		}
		return contieneNum;
	}
	
	private static boolean contieneCarEsp(String pwd) {
		boolean contieneCE = false;
		for (int i = 0; i < pwd.length() && !contieneCE; i++) {
			if (!Character.isAlphabetic(pwd.charAt(i)) && !Character.isDigit(pwd.charAt(i)) && pwd.charAt(i) != '&'
					&& pwd.charAt(i) != '+' && pwd.charAt(i) != '_' && pwd.charAt(i) != '*') {
				contieneCE = true;
			}
		}
		return contieneCE;
	}
	
}
