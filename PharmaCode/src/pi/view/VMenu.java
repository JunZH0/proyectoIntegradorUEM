package pi.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import pi.control.GestorControl;

public class VMenu extends JFrame {
	public static final int ALTO = 600;
	public static final int ANCHO = 800;

	public static final String MNIM_SALIR = "Salir";
	public static final String MNIM_CONS_STOCK = "Stock";
	public static final String MNIM_REG_PROD = "Registrar Producto";
	public static final String MNIM_MOD_PROD = "Modificar Producto";
	public static final String MNIM_REG_VENTA = "Registrar Venta";
	public static final String MNIM_CONS_EMPLE = "Consultar Empleado";
	public static final String MNIM_REG_EMPLE = "Registar Empleado";
	public static final String MNIM_MOD_EMPLE = "Modificar  Empleado";
	public static final String MNIM_CONS_PROV = "Consultar Proveedor";
	public static final String MNIM_REG_PROV = "Registrar Proveedor";
	public static final String MNIM_MOD_PROV = "Modificar  Proovedor";
	
	private JMenu mnProducto;
	private JMenuItem mntmRegistroProd;
	private JMenuItem mntmConsultaStock;
	private JMenuItem mntmModProd;
	private JMenuItem mntmSalir;
	private JScrollPane scrpContenedor;
	private JMenuItem mntmVenta;
	private JSeparator separator_3;
	private JMenu mnEmpleado;
	private JSeparator separator_4;
	private JMenuItem mntmConsultaEmple;
	private JSeparator separator_5;
	private JMenuItem mntmRegistroEmple;
	private JSeparator separator_6;
	private JMenuItem mntmModEmple;
	private JMenu mnProveedor;
	private JSeparator separator_7;
	private JMenuItem mntmConsultaProv;
	private JSeparator separator_8;
	private JMenuItem mntmRegistroProv;
	private JSeparator separator_9;
	private JMenuItem mntmModProv;
	//private JLabel lblLogo;
	
	
	public VMenu() {
		inicializarComponentes();
	}
	
	
	private void inicializarComponentes() {
		setTitle("* * P H A R M A C O D E * *");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(ANCHO, ALTO);
		centrarVentana();
		
		crearMenu();
		
		scrpContenedor = new JScrollPane();
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
		
		JLabel lblInicio = new JLabel("MENU");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setFont(new Font("Tahoma", Font.BOLD, 20));
		scrpContenedor.setColumnHeaderView(lblInicio);
		
		/*lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(VMenu.class.getResource("/img/LogoPharma.png")));
		scrpContenedor.setViewportView(lblLogo);*/
	}


	private void crearMenu() {
		JMenuBar mbMenu = new JMenuBar();
		setJMenuBar(mbMenu);
		
		mnProducto = new JMenu("Producto");
		mnProducto.setHorizontalAlignment(SwingConstants.CENTER);
		mbMenu.add(mnProducto);
		
		mntmConsultaStock = new JMenuItem(MNIM_CONS_STOCK);
		mnProducto.add(mntmConsultaStock);
		
		JSeparator separator = new JSeparator();
		mnProducto.add(separator);
		
		mntmRegistroProd = new JMenuItem(MNIM_REG_PROD);
		mnProducto.add(mntmRegistroProd);
		
		JSeparator separator_1 = new JSeparator();
		mnProducto.add(separator_1);
		
		mntmModProd = new JMenuItem(MNIM_MOD_PROD);
		mnProducto.add(mntmModProd);
		
		JSeparator separator_2 = new JSeparator();
		mbMenu.add(separator_2);
		
		mntmVenta = new JMenuItem(MNIM_REG_VENTA);
		mbMenu.add(mntmVenta);
		
		separator_3 = new JSeparator();
		mbMenu.add(separator_3);
		
		mnEmpleado = new JMenu("Empleados");
		mbMenu.add(mnEmpleado);
		
		mntmConsultaEmple = new JMenuItem(MNIM_CONS_EMPLE);
		mnEmpleado.add(mntmConsultaEmple);
		
		separator_5 = new JSeparator();
		mnEmpleado.add(separator_5);
		
		mntmRegistroEmple = new JMenuItem(MNIM_REG_EMPLE);
		mnEmpleado.add(mntmRegistroEmple);
		
		separator_6 = new JSeparator();
		mnEmpleado.add(separator_6);
		
		mntmModEmple = new JMenuItem(MNIM_MOD_EMPLE);
		mnEmpleado.add(mntmModEmple);
		
		separator_7 = new JSeparator();
		mbMenu.add(separator_7);
		
		mnProveedor = new JMenu("Proveedor");
		mbMenu.add(mnProveedor);
		
		mntmConsultaProv = new JMenuItem(MNIM_CONS_PROV);
		mnProveedor.add(mntmConsultaProv);
		
		separator_8 = new JSeparator();
		mnProveedor.add(separator_8);
		
		mntmRegistroProv = new JMenuItem(MNIM_REG_PROV);
		mnProveedor.add(mntmRegistroProv);
		
		separator_9 = new JSeparator();
		mnProveedor.add(separator_9);
		
		mntmModProv = new JMenuItem(MNIM_MOD_PROV);
		mnProveedor.add(mntmModProv);
		
		separator_4 = new JSeparator();
		mbMenu.add(separator_4);
		
		mntmSalir = new JMenuItem(MNIM_SALIR);
		mntmSalir.setHorizontalAlignment(SwingConstants.CENTER);
		mbMenu.add(mntmSalir);
		

	}


	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));     
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();                  
		Dimension ventana = this.getPreferredSize();                      
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}

	
	
	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}
	
	
	public void setControlador(GestorControl controlador) {
		mntmConsultaStock.addActionListener(controlador);
		mntmModProd.addActionListener(controlador);
		mntmRegistroProd.addActionListener(controlador);
		mntmConsultaEmple.addActionListener(controlador);
		mntmModEmple.addActionListener(controlador);
		mntmRegistroEmple.addActionListener(controlador);
		mntmSalir.addActionListener(controlador);
	}
	
	
	public void hacerVisible() {
		setVisible(true);
	}
	
}
