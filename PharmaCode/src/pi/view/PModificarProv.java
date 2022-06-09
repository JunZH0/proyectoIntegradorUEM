package pi.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pi.control.GestorControl;
import pi.model.Proveedor;

public class PModificarProv extends JPanel {

	
	public static final String BTN_MOD_PROV = "Modificar proveedor";
	public static final String BTN_BUSC_PROV = "Buscar proveedor";
	public static final String BTN_CANCEL_PROV = "Cancelar modificación";
	
	private JTextField txtNombre;
	private JTextField txtCif;
	private JTextField txtTelefono;
	private JButton btnModificar;
	private JButton btnBuscar;
	private JButton btnCancelar;
	
	public PModificarProv() {
		inicializarComponentes();
	}
	


	private void inicializarComponentes() {
		setSize(VInicio.ANCHO, VInicio.ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar Proveedor");
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
		txtCif.setEnabled(false);
		txtCif.setBounds(493, 271, 86, 20);
		add(txtCif);
		txtCif.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(106, 341, 83, 14);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setBounds(213, 338, 114, 20);
		add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnModificar = new JButton(BTN_MOD_PROV);
		btnModificar.setEnabled(false);
		btnModificar.setBounds(67, 437, 178, 23);
		add(btnModificar);
		
		btnBuscar = new JButton(BTN_BUSC_PROV);
		btnBuscar.setBounds(311, 437, 178, 23);
		add(btnBuscar);
		
		btnCancelar = new JButton(BTN_CANCEL_PROV);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(572, 437, 178, 23);
		add(btnCancelar);
		
	}
	
	

	public void setControlador(GestorControl controlador) {
		btnModificar.addActionListener(controlador);	
		btnBuscar.addActionListener(controlador);
		btnCancelar.addActionListener(controlador);
	}



	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}



	public void limpiarComponentes() {
		txtNombre.setText("");
		txtCif.setText("");
		txtTelefono.setText("");
	}



	public Proveedor comprobarDatosModProv() {
		Proveedor modProv = null;
		
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
					modProv = new Proveedor(0, nombre, cif, telefono);
				}
			}
		}
		return modProv;
	}



	public void hacerVisibleMod(boolean bandera) {
		btnBuscar.setEnabled(!bandera);
		btnModificar.setEnabled(bandera);
		btnCancelar.setEnabled(bandera);
		txtCif.setEnabled(bandera);
		txtTelefono.setEnabled(bandera);
		txtNombre.setEnabled(!bandera);
	}



	public String obtenerNombre() {
		String nombreProv = txtNombre.getText();
		return nombreProv;
	}



	public void rellenarDatos(Proveedor proveedor) {
		txtNombre.setText(proveedor.getNombreProv());
		txtCif.setText(proveedor.getCifProv());
		txtTelefono.setText(proveedor.getTelefProv());
	}
}

