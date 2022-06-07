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

	private JLabel lblListadoRest;
	private JTable tblRestaurantes;
	private JScrollPane scrpTablaRest;
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
		
		lblListadoRest = new JLabel("Listado de proveedores:");
		lblListadoRest.setBounds(97, 160, 147, 14);
		add(lblListadoRest);
		
		
		scrpTablaRest = new JScrollPane();
		scrpTablaRest.setBounds(62, 213, 681, 286);
		add(scrpTablaRest);
		
		tblRestaurantes = new JTable();
		scrpTablaRest.setViewportView(tblRestaurantes);
		
		btnEliminar = new JButton(BTN_ELIMINAR_PROV);
		btnEliminar.setBounds(628, 156, 115, 23);
		add(btnEliminar);
		
		configurarTabla();
	}

	
	public void hacerTabVisi(boolean b) {
		scrpTablaRest.setVisible(b);
		lblListadoRest.setVisible(b);
	}
	
	
	
	private void configurarTabla() {
		dtmTablaConsulta = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblRestaurantes.setModel(dtmTablaConsulta);
		
		dtmTablaConsulta.addColumn(DBContract.COL_ID_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_NOM_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_CIF_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_TELF_PROV);
		
		
		tblRestaurantes.getColumn(DBContract.COL_ID_PROV).setPreferredWidth(40);
		tblRestaurantes.getColumn(DBContract.COL_NOM_PROV).setPreferredWidth(40);
		tblRestaurantes.getColumn(DBContract.COL_CIF_PROV).setPreferredWidth(20);
		tblRestaurantes.getColumn(DBContract.COL_TELF_PROV).setPreferredWidth(40);

		
	}




	public void setControlaador(GestorControl controlador) {
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
	
	

	public String restauranteEliminar() {
		String nombreRes = null;
		
		if (tblRestaurantes.getSelectedRow() >= 0) {
			int columna = 0;
			int fila = tblRestaurantes.getSelectedRow();
			nombreRes = tblRestaurantes.getModel().getValueAt(fila, columna).toString();
		}
		return nombreRes;
	}
	


}
