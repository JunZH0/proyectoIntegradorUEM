package pi.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import pi.view.VInicio;
import pi.view.VMenu;

public class GestorControl implements ActionListener{
	
	VInicio vInicio;
	VMenu vMenu;
	
	public GestorControl(VInicio vInicio, VMenu vMenu) {
		this.vInicio = vInicio;
		this.vMenu = vMenu;
	}
	
	
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() instanceof JMenuItem) {
			if (ev.getActionCommand().equals(VMenu.MNIM_SALIR)) {
				salirApp();
			} else if (ev.getActionCommand().equals(VMenu.MNIM_CONS_STOCK)) {
				
			} else if (ev.getActionCommand().equals(VMenu.MNIM_REG_PROD)) {
				
			} else if (ev.getActionCommand().equals(VMenu.MNIM_MOD_PROD)) {
				
			} else if (ev.getActionCommand().equals(VMenu.MNIM_REG_VENTA)) {
				
			} else if (ev.getActionCommand().equals(VMenu.MNIM_CONS_EMPLE)) {
				
			} else if (ev.getActionCommand().equals(VMenu.MNIM_MOD_EMPLE)) {
				
			}
			
		} else if (ev.getSource() instanceof JButton) {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void salirApp() {
		int resp = JOptionPane.showConfirmDialog(vInicio, "Se va a cerrar la aplicación, ¿desea continuar?",
				"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (resp == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	
}
