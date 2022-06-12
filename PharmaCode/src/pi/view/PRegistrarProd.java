package pi.view;

import javax.swing.JPanel;
import pi.control.GestorControl;
import pi.db.persistencia.DBPersistencia;
import pi.model.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.function.DoublePredicate;

import javax.swing.JSpinner;

public class PRegistrarProd extends JPanel {
	private JButton btnGuardar;
	private JTextField txtNombre;
	private JTextArea txtDesc;
	private JComboBox cmbTipo;
	private Producto producto;
	public final static String BTN_GUARDAR = "Guardar"; 
	public final static String BTN_LIMPIAR = "Limpiar"; 
	private JButton btnLimpiar;
	private JTextField txtPrecio;
	private JLabel lblStock;
	private JSpinner spnStock;

	
	
	public PRegistrarProd() {
		init();
	}
	private void init() {
		setLayout(null);
		
		
		JLabel lblRegistrarProductos = new JLabel("Registrar Productos");
		lblRegistrarProductos.setBounds(220, 11, 210, 28);
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
		
		cmbTipo = new JComboBox<String>();
		cmbTipo.setBounds(99, 114, 210, 26);
		add(cmbTipo);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(6, 171, 39, 17);
		add(lblPrecio);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(6, 221, 74, 17);
		add(lblDescripcion);
		
		txtDesc = new JTextArea();
		txtDesc.setBounds(99, 221, 331, 78);
		add(txtDesc);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(99, 377, 86, 27);
		add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(302, 379, 89, 23);
		add(btnLimpiar);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(99, 169, 66, 20);
		add(txtPrecio);
		txtPrecio.setColumns(10);
		
		spnStock = new JSpinner();
		spnStock.setBounds(99, 330, 46, 20);
		add(spnStock);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(11, 333, 46, 14);
		add(lblStock);
	}
	
	
	DBPersistencia gPersis = new DBPersistencia();
	
	public void setControlador(GestorControl c) {
		btnGuardar.addActionListener(c);
		btnLimpiar.addActionListener(c);
		asignarTipo();
	}
	
	public void obtenerDatosProd() {
		boolean esValido = true;
		
		String nombre = txtNombre.getText();
		if (nombre.isEmpty()) {
			mostrarMensaje("El campo nombre no puede estar vacio", "Error de datos", 0);
			esValido = false;
			
			} else {
				String descripcion = txtDesc.getText();
				String tipo = (String) cmbTipo.getSelectedItem();
				double precio;
					if(txtPrecio.getText().equals("")) {
						mostrarMensaje("El campo precio no puede estar vacio", "Error de datos", 0);
					} else {
						precio = Double.parseDouble(txtPrecio.getText());
						int stock = (Integer) spnStock.getValue();
					
						precio = Double.parseDouble(txtPrecio.getText());
						mostrarMensaje("Se ha registrado el medicamento correctamente", "Resultado de la operacion", 1);
						producto = new Producto(0, nombre, descripcion, tipo, precio, stock);
						gPersis.registrarProd(producto);
					}
				
				
			}
		
		
		//return producto;
		
	}
	
	public void mostrarMensaje(String mensaje, String titulo, int i) {
		JOptionPane.showMessageDialog(this, mensaje ,titulo, i);
		
	}
	
	// Muestra en el comboBox a traves de una query los tipos de medicamento
	public void asignarTipo() {
		
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
		txtPrecio.setText(null);
	}
}
