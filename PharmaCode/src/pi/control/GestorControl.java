package pi.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import pi.db.persistencia.DBPersistencia;
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
			}
			
		} else if (ev.getSource() instanceof JButton) {
			if (ev.getActionCommand().equals(PRegistrarProv.BTN_REG_PROV)) {
                registrarProv();
	        } else if (ev.getActionCommand().equals(PConsultarProv.BTN_ELIMINAR_PROV)) {
                eliminarProv();
            }
		}
			
	}
	
	
	
	
	
	
	
	private void registrarProv() {
		Proveedor nuevoProv = pRegProv.obtenerDatosProv();
		if (nuevoProv != null) {
			int idProv = dbPers.selecIdProv(nuevoProv.getNombreProv());
			if (idProv <= 0) {
				pRegProv.mostrarError("Ese proveedor ya exista");
			} else {
				int resp = dbPers.registrarProv(nuevoProv);
				
				if (resp == 1) {
					JOptionPane.showMessageDialog(pRegProv, "Se ha registrado el restaurante", "Información", JOptionPane.INFORMATION_MESSAGE);
					pRegProv.limpiarComponentes();
				} else {
					pRegProv.mostrarError("No se ha podido añadir el restaurante");
				}
			}
		}
	}

	
	
	
	
	
	private void eliminarProv() {
		String nombreProv = pConProv.poveedorEliminar();
		if (nombreProv == null) {
			JOptionPane.showMessageDialog(pConProv, "No se ha seleccionado ningï¿½n restaurante", "Error selecciï¿½n", JOptionPane.ERROR_MESSAGE);
		} else {
			int resp = JOptionPane.showConfirmDialog(pConProv, "Se va a eliminar el restaurante, ï¿½desea continuar?",
					"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (resp == JOptionPane.YES_OPTION) {
				int res = dbPers.borrarProv(nombreProv);
				listarResultadosProv();
				if (res==1) {
					JOptionPane.showMessageDialog(pConProv, "Se ha eliminado el restaurante", "Informaciï¿½n", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(pConProv, "No se ha podido eliminar el restaurante", "Error eliminaciï¿½n", JOptionPane.ERROR_MESSAGE);
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
		int resp = JOptionPane.showConfirmDialog(vInicio, "Se va a cerrar la aplicaciï¿½n, ï¿½desea continuar?",
				"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	
}