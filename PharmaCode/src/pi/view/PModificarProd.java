package pi.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import pi.control.GestorControl;
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
	
	public final static String BTN_MODIFICAR = "Guardar";
	
	public PModificarProd() {
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
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"Anticonceptivo", "Analgesico", "Antihistaminico", "Antibiotico", "Antitusivo", "Antiinflamatorio", "Antidiabetico", "Relajante muscular", "Antidepresivo", "Citotoxico"}));
		cmbTipo.setBounds(195, 203, 146, 26);
		add(cmbTipo);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(445, 143, 63, 17);
		add(lblStock);
		
		spnStock = new JSpinner();
		spnStock.setBounds(536, 141, 42, 22);
		add(spnStock);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(88, 291, 90, 17);
		add(lblDescripcion);
		
		txtArea = new JTextArea();
		txtArea.setBounds(195, 279, 273, 119);
		add(txtArea);
		
		btnGuardar = new JButton(BTN_MODIFICAR);
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
		txtPrecio.setBounds(535, 206, 114, 21);
		add(txtPrecio);
		txtPrecio.setColumns(10);
	}
	
	public void setControlador(GestorControl c) {
		btnBuscarProducto.addActionListener(cmbTipo);
		btnGuardar.addActionListener(c);
		btnLimpiar.addActionListener(c);
	}
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void limpiarCampos() {
		txtArea.setText("");
		txtNombre.setText("");
	}
	
	public void hacerVisibleMod(boolean bandera) {
		btnBuscarProducto.setVisible(!bandera);
		btnGuardar.setVisible(bandera);
		btnLimpiar.setVisible(bandera);
		txtArea.setEnabled(bandera);
		txtNombre.setEnabled(bandera);
		txtPrecio.setEnabled(!bandera);
	}
	
	
	public Producto comprobrarProducto() {
		Producto modProd = null;
		
		String nombre = txtNombre.getText();
		if (nombre.isBlank()) {
			mostrarError("El nombre no puede estar vacio");
		}
		double precio = Double.parseDouble(txtPrecio.getText());
		String campoPrecio = txtPrecio.getText();
		if(campoPrecio.isBlank()) {
			mostrarError("El campo de precio no puede estar vacio");
		}
		
		String tipo = (String) cmbTipo.getSelectedItem();
		String descripcion = txtArea.getText();
		int stock = (int) spnStock.getValue();
		
		modProd = new Producto(0, nombre, descripcion, tipo, precio, stock);
		
		
		
		
		return modProd;
	}
	
}
