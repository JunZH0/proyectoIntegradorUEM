package pi.view;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import pi.control.GestorControl;
import pi.model.Empleado;

public class VInicio extends JFrame{
	public static final String BTN_ACCEDER = "Acceder";
	public static final int ANCHO = 800;
	public static final int ALTO = 600;
	
	private JTextField txtEmpleado;
	private JTextField txtPwd;
	private JButton btnAcceso;
	
	
	public VInicio() {
		init();
		
	}

	private void init() {
		setTitle("PharmaCode PGS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		centrarVentana();
		setSize(ANCHO, ALTO);
		

		JLabel lblTitulo = new JLabel("* * * PHARMACODE * * *");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTitulo);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setToolTipText("Empleado");
		txtEmpleado.setBounds(338, 171, 114, 21);

		JLabel lblPharmacode = new JLabel("PharmaCode");
		lblPharmacode.setBounds(340, 34, 120, 17);
		getContentPane().add(lblPharmacode);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setToolTipText("Empleado");
		txtEmpleado.setBounds(318, 86, 114, 21);

		getContentPane().add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		txtPwd = new JTextField();

		txtPwd.setToolTipText("Contraseña");
		txtPwd.setBounds(338, 275, 114, 21);
		getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		
		btnAcceso = new JButton(BTN_ACCEDER);
		btnAcceso.setBounds(349, 356, 86, 27);
		txtPwd.setToolTipText("ContraseÃ±a");
		txtPwd.setBounds(318, 142, 114, 21);
		getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		
		JButton btnAcceso = new JButton(BTN_ACCEDER);
		btnAcceso.setBounds(329, 211, 86, 27);

		getContentPane().add(btnAcceso);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(369, 144, 46, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblPwd = new JLabel("Contraseña");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setBounds(328, 118, 86, 14);
		getContentPane().add(lblPwd);
		
		
		
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));     
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();                  
		Dimension ventana = this.getPreferredSize();                      
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}
	
	
	public void setControlador(GestorControl controlador) {
		btnAcceso.addActionListener(controlador);
	}
	
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}

	public Empleado obtenerUsuario() {
		Empleado empleado =  null;
		
		String apellido = txtEmpleado.getText().trim(); 
		
		if (apellido.isEmpty()) {
			mostrarError("Debe introducir el nombre de usuario");
		} else {
			String pwd = txtPwd.getText().trim();
			String error = Empleado.validarPassword(apellido, pwd);
			if (!error.isEmpty()) {
				mostrarError(error);
			} else {
				empleado = new Empleado(apellido, pwd);
			}
		}
		
		return empleado;
	}

	public void limpiarDatos() {
		txtEmpleado.setText("");
		txtPwd.setText("");
	}
	
	
}