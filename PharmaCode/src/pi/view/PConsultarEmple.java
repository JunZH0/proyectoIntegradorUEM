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
import pi.model.Empleado;

public class PConsultarEmple extends JPanel {

	public static final String BTN_ELIMINAR_PROV = "Eliminar";

	private JLabel lblListadoEmple;
	private JTable tblEmpleados;
	private JScrollPane scrpTablaEmple;
	private DefaultTableModel dtmTablaConsulta;
	private JButton btnEliminar;
	
	
	public PConsultarEmple() {
		inicializarComponentes();
		
	}
	
	private void inicializarComponentes() {
		setSize(VInicio.ANCHO, VInicio.ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consulta de Empleados");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);
		
		lblListadoEmple = new JLabel("Listado de Empleados:");
		lblListadoEmple.setBounds(97, 160, 147, 14);
		add(lblListadoEmple);
		
		scrpTablaEmple = new JScrollPane();
		scrpTablaEmple.setBounds(62, 213, 681, 286);
		add(scrpTablaEmple);
		
		tblEmpleados = new JTable();
		scrpTablaEmple.setViewportView(tblEmpleados);
		
		btnEliminar = new JButton(BTN_ELIMINAR_PROV);
		btnEliminar.setBounds(628, 156, 115, 23);
		add(btnEliminar);
		
		configurarTabla();
	}
	
	public void hacerTabVisi(boolean b) {
		scrpTablaEmple.setVisible(b);
		lblListadoEmple.setVisible(b);
	}
	
	private void configurarTabla() {
		dtmTablaConsulta = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
				
		dtmTablaConsulta.addColumn(DBContract.COL_ID_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_NOM_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_CIF_PROV);
		dtmTablaConsulta.addColumn(DBContract.COL_TELF_PROV);
		
		tblEmpleados.setModel(dtmTablaConsulta);
		
		tblEmpleados.getColumn(DBContract.COL_ID_PROV).setPreferredWidth(40);
		tblEmpleados.getColumn(DBContract.COL_NOM_PROV).setPreferredWidth(40);
		tblEmpleados.getColumn(DBContract.COL_CIF_PROV).setPreferredWidth(20);
		tblEmpleados.getColumn(DBContract.COL_TELF_PROV).setPreferredWidth(40);
		
	}
	
	public void setControlador(GestorControl controlador) {
		btnEliminar.addActionListener(controlador);	
	}
	
	public void rellenarTabla(ArrayList<Empleado> listaEmple) {
		dtmTablaConsulta.getDataVector().clear();
		Object[] fila = new Object[5];
		
		for (Empleado empleado : listaEmple) {
			fila[0] = empleado.getIdEmpleado();
			
			fila[1] = empleado.getNombreEmple();
			
			fila[2] = empleado.getApellidoEmple();
			
			fila[3] = empleado.getTurnoEmple();
			
			fila[4] = empleado.getAdminEmple();
			
			dtmTablaConsulta.addRow(fila);
			
		}
	}
	
	public String empleadoEliminar() {
		String nombreEmple = null;
		
		if (tblEmpleados.getSelectedRow() >= 0) {
			int columna = 1;
			int fila = tblEmpleados.getSelectedRow();
			nombreEmple = tblEmpleados.getModel().getValueAt(fila, columna).toString();
		}
		
		return nombreEmple;
		
	}
	
}





















