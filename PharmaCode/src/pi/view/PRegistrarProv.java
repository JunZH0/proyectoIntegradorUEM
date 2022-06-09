package pi.view;

import java.awt.Font;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pi.control.GestorControl;
import pi.model.Proveedor;

import javax.swing.JTextField;
import javax.swing.JButton;

public class PRegistrarProv extends JPanel {
	
	public static final String BTN_REG_PROV = "Registrar  proveedor";
	
	private JTextField txtNombre;
	private JTextField txtCif;
	private JTextField txtTelefono;
	JButton btnRegistrar;
	
	public PRegistrarProv() {
		inicializarComponentes();
	}
	


	private void inicializarComponentes() {
		setSize(VInicio.ANCHO, VInicio.ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registrar Proveedor");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(106, 186, 83, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(213, 183, 129, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDatos = new JLabel("Rellenar datos del proveedor");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setBounds(230, 121, 339, 14);
		add(lblDatos);
		
		JLabel lblCif = new JLabel("CIF:");
		lblCif.setBounds(406, 274, 46, 14);
		add(lblCif);
		
		txtCif = new JTextField();
		txtCif.setBounds(493, 271, 86, 20);
		add(txtCif);
		txtCif.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(106, 341, 83, 14);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(213, 338, 114, 20);
		add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnRegistrar = new JButton(BTN_REG_PROV);
		btnRegistrar.setBounds(303, 442, 194, 23);
		add(btnRegistrar);
		
	}
	
	

	public void setControlador(GestorControl controlador) {
		btnRegistrar.addActionListener(controlador);	
	}



	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}



	public void limpiarComponentes() {
		txtNombre.setText("");
		txtCif.setText("");
		txtTelefono.setText("");
	}



	public Proveedor obtenerDatosProv() {
		Proveedor nuevoProv = null;
		
		String nombre = txtNombre.getText();
		if (nombre.isBlank()) {
			mostrarError("El nombre no puede estar vacío");
		} else {
			String cif = txtCif.getText();
			if (cif.isBlank()) {
				mostrarError("El CIF no puede estar vacío");
			} else {
				String telefono = txtTelefono.getText();
				if (telefono.isBlank()) {
					mostrarError("El teléfono no puede estar vacío");
				} else {
					nuevoProv = new Proveedor(0, nombre, cif, telefono);
				}
			}
		}
		return nuevoProv;
	}
	
	
}