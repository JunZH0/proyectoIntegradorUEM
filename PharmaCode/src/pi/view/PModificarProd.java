package pi.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

import pi.control.GestorControl;
import pi.db.persistencia.DBPersistencia;
import pi.model.Producto;
import pi.model.Proveedor;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class PModificarProd extends JPanel {
	private JTextField txtNombre;
	private JButton btnGuardar;
	private JButton btnBuscarProducto;
	private JTextArea txtArea;
	private JComboBox cmbTipo;
	private JSpinner spnStock;
	private JButton btnLimpiar;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	
	public final static String BTN_GUARDAR = "Guardar";
	public final static String BTN_LIMPIAR = "Limpiar";
	public final static String BTN_BUSCAR = "Buscar Producto";
	
	public PModificarProd() {
		init();
	}

	private void init() {
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificar Producto");
		lblTitulo.setBounds(265, 26, 202, 28);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(88, 143, 63, 17);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(195, 141, 171, 21);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(88, 208, 63, 17);
		add(lblTipo);
		
		cmbTipo = new JComboBox();
		cmbTipo.setEnabled(false);
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"ANTICONCEPTIVO", "ANALGESICO", "ANTIHISTAMINICO", "ANTIBIOTICO",
				"ANTITUSIVO", "RELAJANTE MUSCULAR", "ANTIDEPRESIVO", "CITOTOXICO", "ANTIINFLAMATORIO", "ANTIDIABETICO"}));
		cmbTipo.setBounds(195, 203, 146, 26);
		add(cmbTipo);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(445, 143, 63, 17);
		add(lblStock);
		
		spnStock = new JSpinner();
		spnStock.setEnabled(false);
		spnStock.setBounds(536, 141, 42, 22);
		add(spnStock);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(88, 291, 90, 17);
		add(lblDescripcion);
		
		txtArea = new JTextArea();
		txtArea.setEnabled(false);
		txtArea.setBounds(195, 279, 273, 119);
		add(txtArea);
		
		btnGuardar = new JButton(BTN_GUARDAR);
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(88, 445, 108, 27);
		add(btnGuardar);
		
		btnBuscarProducto = new JButton("Buscar Producto");
		btnBuscarProducto.setBounds(276, 445, 171, 27);
		add(btnBuscarProducto);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(541, 445, 108, 27);
		add(btnLimpiar);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(445, 208, 63, 17);
		add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setEnabled(false);
		txtPrecio.setBounds(535, 206, 114, 21);
		add(txtPrecio);
		txtPrecio.setColumns(10);
	}
	
	DBPersistencia dPersis = new DBPersistencia();
	
	public void setControlador(GestorControl c) {
		btnBuscarProducto.addActionListener(c);
		btnGuardar.addActionListener(c);
		btnLimpiar.addActionListener(c);
	}
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void limpiarCampos() {
		txtArea.setText("");
		txtNombre.setText("");
		cmbTipo.setSelectedIndex(-1);
		txtPrecio.setText("");
		spnStock.setValue(0);
	}
	
	public void hacerVisibleMod(boolean bandera) {
		
		btnGuardar.setEnabled(bandera);
		btnLimpiar.setEnabled(bandera);
		txtArea.setEnabled(bandera);
		txtPrecio.setEnabled(bandera);
		cmbTipo.setEnabled(bandera);
		spnStock.setEnabled(bandera);
	}
	
	public void rellenarDatos(Producto producto) {
		txtNombre.setText(producto.getNombreProd());
		txtArea.setText(producto.getDescrProd());
		txtPrecio.setText(String.valueOf(producto.getPrecioProd()));
		spnStock.setValue(producto.getStockProd());
		cmbTipo.setSelectedItem(producto.getTipo());
		
		
	}
	
	public Producto comprobarDatosModProd() {
		Producto modProd = null;
		
		String nombre = txtNombre.getText();
		if (nombre.isBlank()) {
			mostrarError("El nombre no puede estar vacío");
		} else {
			String descripcion = txtArea.getText();
			String tipo = (String) cmbTipo.getSelectedItem();
			double precio;
			if(txtPrecio.getText().equals("")) {
				mostrarMensaje("El campo precio no puede estar vacio", "Error de datos", 0);
			} else {
				precio = Double.parseDouble(txtPrecio.getText());
				int stock = (int) spnStock.getValue();
					modProd = new Producto(0, nombre, descripcion, tipo, precio, stock);
				}
			}
		return modProd;
		}
	
	
	
	
	
	
	
	
	public void comprobrarProducto() {
		String nomProd = txtNombre.getText();
		if (nomProd.isBlank()) {
			mostrarError("El nombre no puedes estar en blanco");
		} else {
			String prod = dPersis.seleccionarProductoUnico(nomProd);
			
				Producto producto = dPersis.seleccionarUnProducto(prod);
				rellenarDatos(producto);
			
		}
	}
	
	

	public void mostrarMensaje(String mensaje, String titulo, int i) {
		JOptionPane.showMessageDialog(this, mensaje ,titulo, i);
		
	}
	
	
}
