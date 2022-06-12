package pi.view;

import java.awt.Font;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pi.control.GestorControl;
import pi.model.Empleado;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PRegistrarEmple extends JPanel {
	
	public static final String BTN_REG_EMPLE = "Registrar empleado";
	
	private JTextField txtNombreEmpleReg;
	private JTextField txtIdEmpleReg;
	private JTextField txtApellidoEmpleReg;
	
	private JButton btnRegistrar;
	private JTextField txtPWDEmpleReg;
	
	private JComboBox cmbAdminEmpleReg;
	private JComboBox cmbTurnoEmpleReg;
	
	
	public PRegistrarEmple() {
		
		inicializarComponentes();
		
	}
	
	private void inicializarComponentes() {
		setSize(VInicio.ANCHO, VInicio.ALTO);
		setLayout(null);
		
		JLabel lblTituloEmpleReg = new JLabel("Registrar Empleado");
		lblTituloEmpleReg.setBounds(0, 0, 800, 25);
		lblTituloEmpleReg.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTituloEmpleReg.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTituloEmpleReg);
		
		JLabel lblNombreEmpleReg = new JLabel("Nombre:");
		lblNombreEmpleReg.setBounds(65, 201, 83, 14);
		add(lblNombreEmpleReg);
		
		txtNombreEmpleReg = new JTextField();
		txtNombreEmpleReg.setBounds(172, 198, 129, 20);
		add(txtNombreEmpleReg);
		txtNombreEmpleReg.setColumns(10);
		
		JLabel lblDatos = new JLabel("Rellenar datos del empleado");
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setBounds(230, 121, 339, 14);
		add(lblDatos);
		
		JLabel lblIdEmpleReg = new JLabel("ID:");
		lblIdEmpleReg.setBounds(65, 162, 46, 14);
		add(lblIdEmpleReg);
		
		txtIdEmpleReg = new JTextField();
		txtIdEmpleReg.setBounds(172, 159, 86, 20);
		add(txtIdEmpleReg);
		txtIdEmpleReg.setColumns(10);
		
		JLabel lblApellidoEmpleReg = new JLabel("Apellido:");
		lblApellidoEmpleReg.setBounds(65, 236, 83, 14);
		add(lblApellidoEmpleReg);
		
		txtApellidoEmpleReg = new JTextField();
		txtApellidoEmpleReg.setBounds(172, 233, 129, 20);
		add(txtApellidoEmpleReg);
		txtApellidoEmpleReg.setColumns(10);
		
		btnRegistrar = new JButton(BTN_REG_EMPLE);
		btnRegistrar.setBounds(340, 496, 144, 23);
		add(btnRegistrar);
		
		JLabel lblTurnoEmpleReg = new JLabel("Turno:");
		lblTurnoEmpleReg.setBounds(450, 162, 49, 14);
		add(lblTurnoEmpleReg);
		
		cmbTurnoEmpleReg = new JComboBox();
		cmbTurnoEmpleReg.setBounds(560, 158, 30, 22);
		add(cmbTurnoEmpleReg);
		
		JLabel lblAdminEmpleReg = new JLabel("Administrador");
		lblAdminEmpleReg.setBounds(450, 201, 77, 14);
		add(lblAdminEmpleReg);
		
		cmbAdminEmpleReg = new JComboBox();
		cmbAdminEmpleReg.setBounds(560, 197, 30, 22);
		add(cmbAdminEmpleReg);
		
		JLabel lblPWDEmpleReg = new JLabel("PassWord");
		lblPWDEmpleReg.setBounds(450, 236, 62, 14);
		add(lblPWDEmpleReg);
		
		txtPWDEmpleReg = new JTextField();
		txtPWDEmpleReg.setBounds(560, 233, 96, 20);
		add(txtPWDEmpleReg);
		txtPWDEmpleReg.setColumns(10);
		
	}
	
	public void setControlador(GestorControl controlador) {
		
		btnRegistrar.addActionListener(controlador);
		
	}
	
	public void mostrarError(String error) {
		
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void limpiarComponentes() {
		
		txtIdEmpleReg.setText("");
		txtNombreEmpleReg.setText("");
		txtApellidoEmpleReg.setText("");
		txtPWDEmpleReg.setText("");
		cmbTurnoEmpleReg.setSelectedIndex(0);
		cmbAdminEmpleReg.setSelectedIndex(0);
		
	}
	
	public Empleado obtenerDatosEmple() {
		Empleado nuevoEmple = null;
		
		String nombre = txtNombreEmpleReg.getText();
		if (nombre.isBlank()) {
			mostrarError("El nombre no puede estar vacío");
		} else {
			int id = Integer.valueOf(txtIdEmpleReg.getText());
			if (id <= 0) {
				mostrarError("El ID no puede ser menor que cero");
			} else {
				String apellido = txtApellidoEmpleReg.getText();
				if (apellido.isBlank()) {
					mostrarError("El apellido no puede estar vacío");
				} else {
					nuevoEmple = new Empleado(id, nombre, apellido);
				}
			}
		}
		
		return nuevoEmple;
		
	}
	
}













