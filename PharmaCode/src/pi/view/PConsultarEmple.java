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

	public static final String BTN_ELIMINAR_EMPLE = "Eliminar empleado";

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
		lblListadoEmple.setBounds(97, 160, 177, 14);
		add(lblListadoEmple);
		
		scrpTablaEmple = new JScrollPane();
		scrpTablaEmple.setBounds(62, 213, 681, 286);
		add(scrpTablaEmple);
		
		tblEmpleados = new JTable();
		scrpTablaEmple.setViewportView(tblEmpleados);
		
		btnEliminar = new JButton(BTN_ELIMINAR_EMPLE);
		btnEliminar.setBounds(572, 156, 171, 23);
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
				
		dtmTablaConsulta.addColumn(DBContract.COL_ID_EMP);
		dtmTablaConsulta.addColumn(DBContract.COL_NOM_EMP);
		dtmTablaConsulta.addColumn(DBContract.COL_APE_EMP);
		dtmTablaConsulta.addColumn(DBContract.COL_TURNO_EMP);
		dtmTablaConsulta.addColumn(DBContract.COL_ES_ADM_EMP);
		
		tblEmpleados.setModel(dtmTablaConsulta);
		
		tblEmpleados.getColumn(DBContract.COL_ID_EMP).setPreferredWidth(40);
		tblEmpleados.getColumn(DBContract.COL_NOM_EMP).setPreferredWidth(40);
		tblEmpleados.getColumn(DBContract.COL_APE_EMP).setPreferredWidth(20);
		tblEmpleados.getColumn(DBContract.COL_TURNO_EMP).setPreferredWidth(40);
		tblEmpleados.getColumn(DBContract.COL_ES_ADM_EMP).setPreferredWidth(20);
		
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





















