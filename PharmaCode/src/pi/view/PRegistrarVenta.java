package pi.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pi.control.GestorControl;
import pi.model.Ventas;

import javax.swing.JTextField;
import javax.swing.JButton;



public class PRegistrarVenta extends JPanel {
	
	public static final String BTN_REG_VENTA = "Registrar Venta";
	public static final String BTN_LIMPIAR_VENTA = "Limpiar campos";
	
	private JTextField txtIdEmple;
	private JTextField txtHora;
	private JTextField txtFecha;
	private JButton btnRegVenta;
	private JButton btnLimpiarVenta;
	
	
	
	public PRegistrarVenta() {
		inicializarComponentes();
		
	}
	
	

	private void inicializarComponentes() {
		setSize(800,600);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Venta");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);
		
		JLabel lblidEmple = new JLabel("ID empleado:");
		lblidEmple.setBounds(94, 117, 99, 14);
		add(lblidEmple);
		
		txtIdEmple = new JTextField();
		txtIdEmple.setBounds(261, 114, 86, 20);
		add(txtIdEmple);
		txtIdEmple.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora  venta:");
		lblHora.setBounds(94, 223, 99, 14);
		add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setBounds(261, 220, 86, 20);
		add(txtHora);
		txtHora.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha venta:");
		lblFecha.setBounds(94, 337, 99, 14);
		add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(261, 334, 86, 20);
		add(txtFecha);
		txtFecha.setColumns(10);
		
		btnRegVenta = new JButton(BTN_REG_VENTA);
		btnRegVenta.setBounds(119, 458, 135, 23);
		add(btnRegVenta);
		
		btnLimpiarVenta = new JButton(BTN_LIMPIAR_VENTA);
		btnLimpiarVenta.setBounds(514, 458, 135, 23);
		add(btnLimpiarVenta);

	}
	
	
	
	public void setControlaador(GestorControl controlador) {
		btnLimpiarVenta.addActionListener(controlador);
		btnRegVenta.addActionListener(controlador);
		
	}



	public void limpiarCamposVenta() {
		txtFecha.setText("");
		txtHora.setText("");
		txtIdEmple.setText("");
	}
	
	
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}



	public Ventas obtenerDatosVenta() {
		Ventas venta =  null;
		
		try {
			int idEmple =  Integer.valueOf( txtIdEmple.getText());
			if (idEmple <= 0) {
				mostrarError("El ID del empleado ser mayor que 0");
			} else {
				String hora = txtHora.getText();
				if (hora.isBlank()) {
					mostrarError("La hora no puede estar vacía");
				} else {
					String fecha = txtFecha.getText();
					if (fecha.isBlank()) {
						mostrarError("La fecha no puede estar vacía");
					} else {
						venta = new Ventas(0, idEmple, hora, fecha);
					}
				}
				
			}
		} catch (NumberFormatException e) {
			mostrarError("El ID del empleado ser un valor numérico");
		}
		
		return venta;
	}
}