package pi.main;

import java.awt.EventQueue;

import pi.control.GestorControl;
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

public class GestorMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VInicio vInicio = new VInicio();
				VMenu vMenu = new VMenu();
				PConsultarStock pConStock = new PConsultarStock();
				PModificarProd pModProd = new PModificarProd();
				PRegistrarProd pRegProd = new PRegistrarProd();
				PRegistrarVenta pRegVenta = new PRegistrarVenta();
				PConsultarEmple pConEmple = new PConsultarEmple();
				PModificarEmple pModEmple = new PModificarEmple();
				PRegistrarEmple pRegEmple = new PRegistrarEmple();
				PConsultarProv pConProv =  new PConsultarProv();
				PModificarProv pModProv = new PModificarProv();
				PRegistrarProv pRegProv = new PRegistrarProv();
				
				GestorControl controlador = new GestorControl(vInicio, vMenu, pConStock, pModProd, pRegProd, pRegVenta, pConEmple, pModEmple, pRegEmple, pConProv, pModProv, pRegProv);
				
				vInicio.setControlador(controlador);
				vMenu.setControlador(controlador);
				pConProv.setControlador(controlador);
				pModProv.setControlador(controlador);
				pRegProv.setControlador(controlador);
				
				pModEmple.setControlador(controlador);
				pConEmple.setControlador(controlador);
				pRegEmple.setControlador(controlador);
				
				vInicio.hacerVisible();
			}
		});

	}

}
