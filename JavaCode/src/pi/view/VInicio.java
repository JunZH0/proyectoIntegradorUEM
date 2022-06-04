package pi.view;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VInicio extends JFrame{
	public static final String BTN_ACCEDER = "Acceder";
	public static final int ANCHO = 800;
	public static final int ALTO = 600;
	
	private JTextField txtEmpleado;
	private JTextField txtPwd;
	
	
	public VInicio() {
		init();
		
	}

	private void init() {
		setTitle("PharmaCode PGS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		centrarVentana();
		setSize(ANCHO, ALTO);
		
		JLabel lblPharmacode = new JLabel("PharmaCode");
		lblPharmacode.setBounds(186, 34, 120, 17);
		getContentPane().add(lblPharmacode);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setToolTipText("Empleado");
		txtEmpleado.setBounds(170, 77, 114, 21);
		getContentPane().add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		txtPwd = new JTextField();
		txtPwd.setToolTipText("Contraseña");
		txtPwd.setBounds(170, 132, 114, 21);
		getContentPane().add(txtPwd);
		txtPwd.setColumns(10);
		
		JButton btnAcceso = new JButton(BTN_ACCEDER);
		btnAcceso.setBounds(186, 180, 86, 27);
		getContentPane().add(btnAcceso);
		
		
		
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	
	
	public void centrarVentana() {
		setPreferredSize(new Dimension());  
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(100, 100, (int) pantalla.getWidth(), (int) pantalla.getHeight());
		setLocationRelativeTo(null);
		Dimension ventana = this.getPreferredSize();               
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);		
		
	}
}
