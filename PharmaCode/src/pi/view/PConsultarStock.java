package pi.view;

import javax.swing.JPanel;
import pi.control.GestorControl;
import pi.db.persistencia.DBContract;
import pi.db.persistencia.DBPersistencia;
import pi.model.Producto;
import pi.model.Proveedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.ScrollPane;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PConsultarStock extends JPanel {
	private JComboBox cmbTipo;
	private DefaultTableModel dtmProd;
	private JTable tblProd;
	private ArrayList<Producto> listaProd;
	private JScrollPane scrollPane;
	private JButton btnConsultar;
	public final static String BTN_CONSULTAR = "Consultar";
	public final static String BTN_ELIMINAR = "Eliminar Datos";
	private JButton btnEliminar;
	
	
	public PConsultarStock() {
		init();
	}

	private void init() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta de Stock");
		lblNewLabel.setBounds(231, 12, 237, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtros");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(19, 43, 89, 14);
		add(lblNewLabel_1);
		
		cmbTipo = new JComboBox();
		cmbTipo.addItem("Todas");
		cmbTipo.setBounds(75, 72, 150, 22);
		add(cmbTipo);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo");
		lblNewLabel_2.setBounds(19, 76, 46, 14);
		add(lblNewLabel_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 129, 605, 216);
		add(scrollPane);
		
		tblProd = new JTable();
		scrollPane.setViewportView(tblProd);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(75, 395, 110, 23);
		add(btnConsultar);
		
		btnEliminar = new JButton("Eliminar Datos");
		btnEliminar.setBounds(403, 395, 89, 23);
		add(btnEliminar);
	}
	
	Producto product;
	
	
	public void setControlador(GestorControl c) {
		btnConsultar.addActionListener(c);
		btnEliminar.addActionListener(c);
		asignarTipo();
	}
	
	public void asignarTipo() {
		DBPersistencia dPersis = new DBPersistencia();
		DefaultComboBoxModel<String> modelo = (DefaultComboBoxModel<String>) cmbTipo.getModel();
		for(String tipo : dPersis.getTiposProd()) {
			modelo.addElement(tipo);
		}
	}
	
	public void configurarTabla() {
		
		dtmProd = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				
				return false;
			}
		};
		
		tblProd.setModel(dtmProd);
		
		dtmProd.addColumn(DBContract.COL_NOM_PROD);
		dtmProd.addColumn(DBContract.COL_TIPO_PROD);
		dtmProd.addColumn(DBContract.COL_STOCK_PROD);
		dtmProd.addColumn(DBContract.COL_PRECIO_PROD);
	
		
	}
	
	public void obtenerDatos(DBPersistencia dbP) {
		configurarTabla();
		
		String tipo = (String) cmbTipo.getSelectedItem();
		
		listaProd = new ArrayList<>();
		
		if(tipo.equals("Todas")) {
			for (Producto prod : dbP.seleccionarProducto() ) {
				listaProd.add(prod);
			}
			
		} else {
			scrollPane.setVisible(true);
			for (Producto prod : dbP.seleccionarProducto() ) {
				if(prod.getTipo().equals(tipo)) {
					listaProd.add(prod);
				}
				
			}
		}
		if(listaProd.isEmpty()) {
			scrollPane.setVisible(false);
			mostrarAviso("No se han encontrado datos para el filtro introducido", "Resultado de consulta", 1);
		} else {
			scrollPane.setVisible(true);
			for(Producto prod : listaProd) {
				dtmProd.addRow(prod.getRowData());
			}
		}
	
		
		
	}
	
	public String productoEliminar() {
		String nombreProd = null;
		
		if (tblProd.getSelectedRow() >= 0) {
			int columna = 0;
			int fila = tblProd.getSelectedRow();
			nombreProd = tblProd.getModel().getValueAt(fila, columna).toString();
		}
		return nombreProd;
	}
	
	public void rellenarTabla(ArrayList<Producto> listaProd) {
		dtmProd.getDataVector().clear();
		Object[] data = new Object[5];
		
		for (Producto producto : listaProd) {
			data[0] = producto.getNombreProd();
			data[1] = producto.getDescrProd();
			data[2] = producto.getTipo();
			data[3] = producto.getStockProd();
			data[4] = producto.getPrecioProd();
			
			dtmProd.addRow(data);
		}
	}
	
	
	
	public void mostrarAviso(String mensaje, String titulo, int i) {
		JOptionPane.showMessageDialog(this,mensaje, titulo, i);
	}
}
