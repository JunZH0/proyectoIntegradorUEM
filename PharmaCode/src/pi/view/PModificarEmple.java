package pi.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import pi.control.GestorControl;
import pi.model.Empleado;
import javax.swing.JComboBox;

public class PModificarEmple extends JPanel {
	
	public static final String BTN_MOD_PROV = "Modificar proveedor";
	public static final String BTN_BUSC_PROV = "Buscar proveedor";
	public static final String BTN_CANCEL_PROV = "Cancelar modificación";
	
	private JTextField txtNombreEmple;
	private JTextField txtIdEmple;
	private JTextField txtTelefono;
	JButton btnModificar;
	private JButton btnBuscar;
	private JButton btnCancelar;
	private JTextField txtApellidoEmple;
	
	private JComboBox cmbTurnoEmple;
	private JComboBox cmbAdminEmple;
	
	public PModificarEmple() {
		
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
		
		JLabel lblNombreEmple = new JLabel("Nombre:");
		lblNombreEmple.setBounds(106, 186, 83, 14);
		add(lblNombreEmple);
		
		txtNombreEmple = new JTextField();
		txtNombreEmple.setBounds(213, 183, 129, 20);
		add(txtNombreEmple);
		txtNombreEmple.setColumns(10);
		
		JLabel lblDatos = new JLabel("Rellenar datos del proveedor");
		lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setBounds(213, 99, 356, 36);
		add(lblDatos);
		
		JLabel lblIdEmple = new JLabel("ID:");
		lblIdEmple.setBounds(106, 157, 46, 14);
		add(lblIdEmple);
		
		txtIdEmple = new JTextField();
		txtIdEmple.setEnabled(false);
		txtIdEmple.setBounds(213, 152, 86, 20);
		add(txtIdEmple);
		txtIdEmple.setColumns(10);
		
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
		btnModificar.setBounds(39, 498, 178, 23);
		add(btnModificar);
		
		btnBuscar = new JButton(BTN_BUSC_PROV);
		btnBuscar.setBounds(307, 498, 178, 23);
		add(btnBuscar);
		
		btnCancelar = new JButton(BTN_CANCEL_PROV);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(569, 498, 178, 23);
		add(btnCancelar);
		
		JLabel lblApellidoEmple = new JLabel("Apellido:");
		lblApellidoEmple.setBounds(106, 214, 83, 14);
		add(lblApellidoEmple);
		
		txtApellidoEmple = new JTextField();
		txtApellidoEmple.setColumns(10);
		txtApellidoEmple.setBounds(213, 211, 129, 20);
		add(txtApellidoEmple);
		
		JLabel lblTurnoEmple = new JLabel("Turno:");
		lblTurnoEmple.setBounds(106, 246, 83, 14);
		add(lblTurnoEmple);
		
		cmbTurnoEmple = new JComboBox();
		cmbTurnoEmple.setBounds(213, 242, 30, 22);
		add(cmbTurnoEmple);
		
		JLabel lblAdminEmple = new JLabel("Administrador:");
		lblAdminEmple.setBounds(106, 279, 83, 14);
		add(lblAdminEmple);
		
		cmbAdminEmple = new JComboBox();
		cmbAdminEmple.setBounds(213, 275, 30, 22);
		add(cmbAdminEmple);
		
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
		txtIdEmple.setText("");
		txtNombreEmple.setText("");
		txtApellidoEmple.setText("");
		cmbTurnoEmple.setSelectedIndex(0);
		cmbAdminEmple.setSelectedIndex(0);
		//txtTelefono.setText("");
	}
	
	public Empleado comprobarDatosModProv() {
		Empleado modEmple = null;
		
		String nombre = txtNombreEmple.getText();
		if (nombre.isBlank()) {
			mostrarError("El nombre no puede estar vacío");
		} else {
			String cif = txtIdEmple.getText();
			if (cif.isBlank()) {
				mostrarError("El CIF no puede estar vacío");
			} else {
				String telefono = txtTelefono.getText();
				if (telefono.isBlank()) {
					mostrarError("El teléfono no puede estar vacío");
				} else {
					modEmple = new Empleado(0, nombre, cif, telefono);
				}
			}
		}
		return modEmple;
	}



	public void hacerVisibleMod(boolean bandera) {
		btnBuscar.setVisible(!bandera);
		btnModificar.setVisible(bandera);
		btnCancelar.setVisible(bandera);
		txtIdEmple.setEnabled(bandera);
		txtTelefono.setEnabled(bandera);
		txtNombreEmple.setEnabled(!bandera);
	}



	public String obtenerNombre() {
		String nombreProv = txtNombreEmple.getText();
		return nombreProv;
	}



	public void rellenarDatos(Empleado proveedor) {
		txtNombreEmple.setText(proveedor.getNombreProv());
		txtIdEmple.setText(proveedor.getCifProv());
		txtTelefono.setText(proveedor.getTelefProv());
	}
}


