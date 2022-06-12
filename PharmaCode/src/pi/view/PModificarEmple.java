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

public class PModificarEmple extends JPanel {
	
	public static final String BTN_MOD_EMPLE = "Modificar empleado";
	public static final String BTN_BUSC_EMPLE = "Buscar empleado";
	public static final String BTN_CANCEL_EMPLE = "Cancelar modificación";
	
	private JTextField txtNombreEmple;
	private JTextField txtIdEmple;
	private JTextField txtApellidoEmple;
	
	private JButton btnModificar;
	private JButton btnBuscar;
	private JButton btnCancelar;
	
	public PModificarEmple() {
		
		inicializarComponentes();
		
	}
	
	private void inicializarComponentes() {
		setSize(VInicio.ANCHO, VInicio.ALTO);
		setLayout(null);
		
		JLabel lblTituloEmple = new JLabel("Modificar Empleado");
		lblTituloEmple.setBounds(0, 0, 800, 25);
		lblTituloEmple.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTituloEmple.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTituloEmple);
		
		JLabel lblNombreEmple = new JLabel("Nombre:");
		lblNombreEmple.setBounds(106, 186, 83, 14);
		add(lblNombreEmple);
		
		txtNombreEmple = new JTextField();
		txtNombreEmple.setBounds(213, 183, 129, 20);
		add(txtNombreEmple);
		txtNombreEmple.setColumns(10);
		
		JLabel lblDatosEmple = new JLabel("Rellenar datos del empleado");
		lblDatosEmple.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosEmple.setBounds(230, 121, 339, 14);
		add(lblDatosEmple);
		
		JLabel lblIdEmple = new JLabel("ID:");
		lblIdEmple.setBounds(106, 269, 46, 14);
		add(lblIdEmple);
		
		txtIdEmple = new JTextField();
		txtIdEmple.setEnabled(false);
		txtIdEmple.setBounds(213, 266, 129, 20);
		add(txtIdEmple);
		txtIdEmple.setColumns(10);
		
		JLabel lblApellidoEmple = new JLabel("Apellido:");
		lblApellidoEmple.setBounds(106, 229, 83, 14);
		add(lblApellidoEmple);
		
		txtApellidoEmple = new JTextField();
		txtApellidoEmple.setEnabled(false);
		txtApellidoEmple.setBounds(213, 226, 129, 20);
		add(txtApellidoEmple);
		txtApellidoEmple.setColumns(10);
		
		btnModificar = new JButton(BTN_MOD_EMPLE);
		btnModificar.setEnabled(false);
		btnModificar.setBounds(39, 366, 178, 23);
		add(btnModificar);
		
		btnBuscar = new JButton(BTN_BUSC_EMPLE);
		btnBuscar.setBounds(305, 366, 178, 23);
		add(btnBuscar);
		
		btnCancelar = new JButton(BTN_CANCEL_EMPLE);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(566, 366, 178, 23);
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
		
		txtNombreEmple.setText("");
		txtIdEmple.setText("");
		txtApellidoEmple.setText("");
		
	}
	
	public Empleado comprobarDatosModEmple() {
		Empleado modEmple = null;
		
		String nombre = txtNombreEmple.getText();
		if (nombre.isBlank()) {
			mostrarError("El nombre no puede estar vacío");
		} else {
			try {
				int id = Integer.valueOf(txtIdEmple.getText());
				if (id <= 0) {
					mostrarError("El ID no puede ser menor que cero");
				} else {
					String apellido = txtApellidoEmple.getText();
					if (apellido.isBlank()) {
						mostrarError("El apellido no puede estar vacío");
					} else {
						modEmple = new Empleado(id, nombre, apellido);
					}
				}
			} catch (NumberFormatException e) {
				mostrarError("El ID no puede tener letras");
			}
			
		}
		
		return modEmple;
		
	}
		
	public void hacerVisibleMod(boolean bandera) {
		
		btnBuscar.setEnabled(!bandera);
		btnModificar.setEnabled(bandera);
		btnCancelar.setEnabled(bandera);
		txtIdEmple.setEnabled(bandera);
		txtApellidoEmple.setEnabled(bandera);
		txtNombreEmple.setEnabled(!bandera);
		
	}
	
	public String obtenerNombre() {
		
		String nombreEmple = txtNombreEmple.getText();
		return nombreEmple;
		
	}
	
	public void rellenarDatos(Empleado empleado) {
		
		txtNombreEmple.setText(empleado.getNombreEmple());
		txtIdEmple.setText(String.valueOf(empleado.getIdEmpleado()));
		txtApellidoEmple.setText(empleado.getApellidoEmple());
		
	}
	
}









