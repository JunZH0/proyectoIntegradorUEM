package pi.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import pi.db.persistencia.DBPersistencia;
import pi.model.Empleado;
import pi.model.Proveedor;
import pi.view.PConsultarEmple;
import pi.view.PConsultarProv;
import pi.view.PConsultarStock;
import pi.view.PModificarEmple;
import pi.view.PModificarProd;
import pi.view.PModificarProv;
import pi.view.PRegistrarEmple;
import pi.view.PRegistrarProd;
import pi.view.PRegistrarProv;
import pi.view.PRegistrarVenta;
import pi.view.VInicio;
import pi.view.VMenu;


public class GestorControl implements ActionListener{
	
	private final int TOTAL_INTENTOS = 3;
	private int contIntentos;
	VInicio vInicio;
	VMenu vMenu;
	PConsultarStock pConStock;
	PModificarProd pModProd;
	PRegistrarProd pRegProd;
	PRegistrarVenta pRegVenta;
	PConsultarEmple pConEmple;
	PModificarEmple pModEmple;
	PRegistrarEmple pRegEmple;
	PConsultarProv pConProv;
	PModificarProv pModProv;
	PRegistrarProv pRegProv;
	DBPersistencia dbPers;
	
	
	
	
	public GestorControl(VInicio vInicio, VMenu vMenu, PConsultarStock pConStock, PModificarProd pModProd,
			PRegistrarProd pRegProd, PRegistrarVenta pRegVenta, PConsultarEmple pConEmple, PModificarEmple pModEmple,
			PRegistrarEmple pRegEmple, PConsultarProv pConProv, PModificarProv pModProv, PRegistrarProv pRegProv) {
		this.vInicio = vInicio;
		this.vMenu = vMenu;
		this.pConStock = pConStock;
		this.pModProd = pModProd;
		this.pRegProd = pRegProd;
		this.pRegVenta = pRegVenta;
		this.pConEmple = pConEmple;
		this.pModEmple = pModEmple;
		this.pRegEmple = pRegEmple;
		this.pConProv = pConProv;
		this.pModProv = pModProv;
		this.pRegProv = pRegProv;
		this.dbPers = new DBPersistencia();
	}



	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() instanceof JMenuItem) {
			if (ev.getActionCommand().equals(VMenu.MNIM_SALIR)) {
				salirApp();
			} else if (ev.getActionCommand().equals(VMenu.MNIM_CONS_STOCK)) {
				vMenu.cargarPanel(pConStock);
			} else if (ev.getActionCommand().equals(VMenu.MNIM_REG_PROD)) {
				vMenu.cargarPanel(pRegProd);
			} else if (ev.getActionCommand().equals(VMenu.MNIM_MOD_PROD)) {
				vMenu.cargarPanel(pModProd);
			} else if (ev.getActionCommand().equals(VMenu.MNIM_REG_VENTA)) {
				vMenu.cargarPanel(pRegVenta);
			} else if (ev.getActionCommand().equals(VMenu.MNIM_CONS_EMPLE)) {
				vMenu.cargarPanel(pConEmple);
			} else if (ev.getActionCommand().equals(VMenu.MNIM_MOD_EMPLE)) {
				vMenu.cargarPanel(pModEmple);
			} else if (ev.getActionCommand().equals(VMenu.MNIM_CONS_PROV)) {
				listarResultadosProv();
			} else if (ev.getActionCommand().equals(VMenu.MNIM_REG_PROV)) {
				vMenu.cargarPanel(pRegProv);
			}  else if (ev.getActionCommand().equals(VMenu.MNIM_MOD_PROV)) {
				vMenu.cargarPanel(pModProv);
				pModProv.hacerVisibleMod(false);
			}
			
		} else if (ev.getSource() instanceof JButton) {
			if (ev.getActionCommand().equals(PRegistrarProv.BTN_REG_PROV)) {
                registrarProv();
	        } else if (ev.getActionCommand().equals(PConsultarProv.BTN_ELIMINAR_PROV)) {
                eliminarProv();
            } else if (ev.getActionCommand().equals(PModificarProv.BTN_BUSC_PROV)) {
				buscarProvMod();
			} else if (ev.getActionCommand().equals(PModificarProv.BTN_MOD_PROV)) {
				modificarProv();
			} else if (ev.getActionCommand().equals(PModificarProv.BTN_CANCEL_PROV)) {
				pModProv.limpiarComponentes();
				pModProv.hacerVisibleMod(false);
			} else if (ev.getActionCommand().equals(VInicio.BTN_ACCEDER)) {
				intentoAcceder();
			} else if (ev.getActionCommand().equals(PRegistrarProd.BTN_GUARDAR)) {
				pRegProd.guardarDatos(dbPers);
			} else if (ev.getActionCommand().equals(PConsultarStock.BTN_CONSULTAR)) {
				pConStock.obtenerDatos(dbPers);
			}
		}
			
	}
	
	
	
	
	
	
	

	private void intentoAcceder() {
		boolean noAccede = true;
		Empleado empleado = vInicio.obtenerUsuario();
		
		if (empleado != null) {
			contIntentos++;
			String pwd = dbPers.consultarPwdPorUsuario(empleado.getApellidoEmple());
			String error = "";
			if (pwd == null) {
				error = "El usuario introducido no existe";
				if (contIntentos<TOTAL_INTENTOS) {
					error += " Te equedan " + (TOTAL_INTENTOS - contIntentos);
				}
				vInicio.mostrarError(error);
			} else if (!pwd.equals(empleado.getPassword())) {
				vInicio.mostrarError("La contraseña introducida no es correcta");
			} else {
				noAccede = false; 
				vInicio.dispose();
				vInicio.limpiarDatos();
				vMenu.hacerVisible();
			}
			
			if (noAccede && contIntentos == 3) {
				vInicio.mostrarError("Se han agotado los 3 intentos");
				System.exit(0);
			}
		}
	}



	private void buscarProvMod() {
		String nomProv = pModProv.obtenerNombre();
		if (nomProv.isBlank()) {
			JOptionPane.showMessageDialog(pModProv, "Debe introducir un nombre", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			int idProv = dbPers.selecIdProv(nomProv);
			if (idProv <= 0) {
				pModProv.mostrarError("No se ha encontrado ningún restaurante con el nombre introducido");
			} else {
				Proveedor proveedor = dbPers.selecionarUnProveedor(nomProv);
				pModProv.rellenarDatos(proveedor);
				pModProv.hacerVisibleMod(true);
			}
		}
	}



	private void modificarProv() {
		Proveedor modProv = pModProv.comprobarDatosModProv();
		if (modProv  == null) {
			pModProv.mostrarError("No han podido guardarse los cambios");
		} else {
			int resp = dbPers.modProveedor(modProv);
			
			if (resp == 1) {
				JOptionPane.showMessageDialog(pModProv, "Se ha modificado el restaurante con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
				pModProv.limpiarComponentes();
			} else {
				pModProv.mostrarError("No han podido guardarse los cambios");
			}
		}
	}




	private void registrarProv() {
		Proveedor nuevoProv = pRegProv.obtenerDatosProv();
		if (nuevoProv != null) {
			int idProv = dbPers.selecIdProv(nuevoProv.getNombreProv());
			if (idProv != 0) {
				pRegProv.mostrarError("Ese proveedor ya existe");
			} else {
				int resp = dbPers.registrarProv(nuevoProv);
				
				if (resp == 1) {
					JOptionPane.showMessageDialog(pRegProv, "Se ha registrado el proveedor", "Información", JOptionPane.INFORMATION_MESSAGE);
					pRegProv.limpiarComponentes();
				} else {
					pRegProv.mostrarError("No se ha podido añadir el proveedor");
				}
			}
		}
	}

	
	
	
	
	
	private void eliminarProv() {
		String nombreProv = pConProv.poveedorEliminar();
		if (nombreProv == null) {
			JOptionPane.showMessageDialog(pConProv, "No se ha seleccionado ning�n restaurante", "Error selección", JOptionPane.ERROR_MESSAGE);
		} else {
			int resp = JOptionPane.showConfirmDialog(pConProv, "Se va a eliminar el restaurante, ¿desea continuar?",
					"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (resp == JOptionPane.YES_OPTION) {
				int res = dbPers.borrarProv(nombreProv);
				listarResultadosProv();
				if (res==1) {
					JOptionPane.showMessageDialog(pConProv, "Se ha eliminado el restaurante", "Información", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(pConProv, "No se ha podido eliminar el restaurante", "Error eliminación", JOptionPane.ERROR_MESSAGE);
				}						
			}
		}
	}



	private void listarResultadosProv() {
		ArrayList<Proveedor> listaProv = new ArrayList<>();
		listaProv = dbPers.seleccionarProveedores();
		pConProv.rellenarTabla(listaProv);
		vMenu.cargarPanel(pConProv);
	}



	public void salirApp() {
		int resp = JOptionPane.showConfirmDialog(vInicio, "Se va a cerrar la aplicación, ¿desea continuar?",
				"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	
}