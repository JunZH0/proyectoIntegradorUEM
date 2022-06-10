package pi.view;

import javax.swing.JPanel;
import pi.control.GestorControl;
import pi.db.persistencia.DBPersistencia;
import pi.model.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import java.awt.Font;

public class PRegistrarProd extends JPanel {
	private JButton btnGuardar;
	private JTextField txtNombre;
	private JTextArea txtDesc;
	private JComboBox cmbTipo;
	private JSpinner spnPrecio;
	private Producto producto;
	public final static String BTN_GUARDAR = "Guardar"; 
	
	
	public PRegistrarProd() {
		init();
	}
	private void init() {
		setLayout(null);
		
		
		JLabel lblRegistrarProductos = new JLabel("Registrar Productos");
		lblRegistrarProductos.setBounds(99, 6, 210, 28);
		lblRegistrarProductos.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblRegistrarProductos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 65, 51, 17);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(99, 63, 210, 21);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Tipo");
		lblCategoria.setBounds(6, 119, 28, 17);
		add(lblCategoria);
		
		cmbTipo = new JComboBox();
		cmbTipo.setBounds(99, 114, 210, 26);
		add(cmbTipo);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(6, 171, 39, 17);
		add(lblPrecio);
		
		spnPrecio = new JSpinner();
		spnPrecio.setBounds(99, 169, 28, 22);
		add(spnPrecio);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(6, 221, 74, 17);
		add(lblDescripcion);
		
		txtDesc = new JTextArea();
		txtDesc.setBounds(99, 221, 331, 78);
		add(txtDesc);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(99, 377, 86, 27);
		add(btnGuardar);
	}
	
	public void setControlador(GestorControl c) {
		btnGuardar.addActionListener(c);
	}
	
	public Producto obtenerDatosProd() {
		
		String nombre = txtNombre.getSelectedText();
		String tipo = (String) cmbTipo.getSelectedItem();
		String descripcion = txtDesc.getSelectedText();
		double precio = (double) spnPrecio.getValue();
		int stock = 0;
		
		boolean esValido = true;
		
		if (nombre.isEmpty()) {
			mostrarMensaje("El campo no puede estar vacio", "Error de datos", 0);
			esValido = false;
		}
		
		if(esValido) {
			mostrarMensaje("Se ha registrado el medicamento correctamente", "Resultado de la operacion", 1);
			producto = new Producto(0, nombre, descripcion, tipo, precio, stock);
			
		}
		
		return producto;
		
	}
	
	public void mostrarMensaje(String mensaje, String titulo, int i) {
		JOptionPane.showMessageDialog(this, mensaje ,titulo, i);
		
	}
	
	
	public void asignarTipo(GestorControl c) {
		
		DBPersistencia dPersis = new DBPersistencia();
		DefaultComboBoxModel<String> modelo = (DefaultComboBoxModel<String>) cmbTipo.getModel();
		for(String tipo : dPersis.getTiposProd()) {
			modelo.addElement(tipo);
		}
		
		
	}
	
	public void limpiarDatos() {
		txtNombre.setText(null);
		txtDesc.setText(null);
		cmbTipo.setSelectedIndex(0);
	}
	
	
}
