package pi.view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import pi.control.GestorControl;
import pi.db.persistencia.DBContract;
import pi.model.Proveedor;

public class PConsultarProv extends JPanel {

	public static final String BTN_ELIMINAR_PROV = "Eliminar";

	private JLabel lblListadoProv;
	private JTable tblProveedores;
	private JScrollPane scrpTablaProv;
	private DefaultTableModel dtmTablaConsulta;
	private JButton btnEliminar;
	
	
	public PConsultarProv() {
		inicializarComponentes();
		
	}



	private void inicializarComponentes() {
		setSize(VInicio.ANCHO, VInicio.ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consulta de Proveedores");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);
		
		lblListadoProv = new JLabel("Listado de proveedores:");
		lblListadoProv.setBounds(97, 160, 147, 14);
		add(lblListadoProv);
		
		
		scrpTablaProv = new JScrollPane();
		scrpTablaProv.setBounds(62, 213, 681, 286);
		add(scrpTablaProv);
		
		tblProveedores = new JTable();
		scrpTablaProv.setViewportView(tblProveedores);
		
		btnEliminar = new JButton(BTN_ELIMINAR_PROV);
		btnEliminar.setBounds(628, 156, 115, 23);
		add(btnEliminar);
		
		configurarTabla();
	}


	
	
	
	private void configurarTabla() {
		dtmTablaConsulta = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblProveedores.setModel(dtmTablaConsulta);
		
		dtmTablaConsulta.addColumn(DBContract.COL_ID_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_NOM_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_CIF_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_TELF_PROV);
		
		
		tblProveedores.getColumn(DBContract.COL_ID_PROV).setPreferredWidth(40);
		tblProveedores.getColumn(DBContract.COL_NOM_PROV).setPreferredWidth(40);
		tblProveedores.getColumn(DBContract.COL_CIF_PROV).setPreferredWidth(20);
		tblProveedores.getColumn(DBContract.COL_TELF_PROV).setPreferredWidth(40);

	}




	public void setControlador(GestorControl controlador) {
		btnEliminar.addActionListener(controlador);	
	}



	public void rellenarTabla(ArrayList<Proveedor> listaProv) {
		dtmTablaConsulta.getDataVector().clear();
		Object[] fila = new Object[4];
		
		for (Proveedor proveedor : listaProv) {
			fila[0] = proveedor.getIdProv();
			
			fila[1] = proveedor.getNombreProv();
			
			fila[2] = proveedor.getCifProv();
			
			fila[3] = proveedor.getTelefProv();
			
			dtmTablaConsulta.addRow(fila);
		}
	}
	
	

	public String poveedorEliminar() {
		String nombreProv = null;
		
		if (tblProveedores.getSelectedRow() >= 0) {
			int columna = 1;
			int fila = tblProveedores.getSelectedRow();
			nombreProv = tblProveedores.getModel().getValueAt(fila, columna).toString();
		}
		return nombreProv;
	}
	


}
