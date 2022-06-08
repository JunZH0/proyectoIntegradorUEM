package pi.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pi.control.GestorControl;
import pi.db.persistencia.DBPersistencia;
import pi.model.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class PRegistrarProd extends JPanel {
	private JButton btnGuardar;
	private JTextField txtNombre;
	private JTextArea txtDesc;
	private JComboBox cmbTipo;
	private JSpinner spnPrecio;
	private Producto producto;
	
	
	
	public PRegistrarProd() {
		init();
	}
	private void init() {
		setLayout(new MigLayout("", "[][63.00][grow][185.00,left]", "[][][][][][][][][grow][][]"));
		
		
		JLabel lblRegistrarProductos = new JLabel("Registrar Productos");
		add(lblRegistrarProductos, "cell 2 0");
		
		JLabel lblNombre = new JLabel("Nombre");
		add(lblNombre, "cell 0 2");
		
		txtNombre = new JTextField();
		add(txtNombre, "cell 2 2,growx");
		txtNombre.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Tipo");
		add(lblCategoria, "cell 0 4");
		
		cmbTipo = new JComboBox();
		add(cmbTipo, "cell 2 4,growx");
		
		JLabel lblPrecio = new JLabel("Precio");
		add(lblPrecio, "cell 0 6");
		
		spnPrecio = new JSpinner();
		add(spnPrecio, "cell 2 6");
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		add(lblDescripcion, "cell 0 8");
		
		txtDesc = new JTextArea();
		add(txtDesc, "cell 2 8 2 1,grow");
		
		btnGuardar = new JButton("Guardar");
		add(btnGuardar, "cell 3 10");
	}
	
	public void setControlador(GestorControl c) {
		btnGuardar.addActionListener(c);
	}
	
	public void guardarDatos(DBPersistencia dPersis) {
		
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
			producto = new Producto(ALLBITS, nombre, descripcion, tipo, precio, stock);
			
			dPersis.registrarProd(producto);
		}
		
		
		
	}
	
	private void mostrarMensaje(String mensaje, String titulo, int i) {
		JOptionPane.showMessageDialog(this, mensaje ,titulo, i);
		
	}
	
	
	public void obtenerTipo() {
		
		
		
	}
		

}
