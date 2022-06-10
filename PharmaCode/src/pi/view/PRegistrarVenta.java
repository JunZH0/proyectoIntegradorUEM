package pi.view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import pi.control.GestorControl;
import pi.db.persistencia.DBContract;
import pi.model.Ventas;

import javax.swing.JTextField;
import javax.swing.JButton;



public class PRegistrarVenta extends JPanel {
	
	public static final String BTN_REG_VENTA = "Registrar Venta";
	public static final String BTN_LIMPIAR_VENTA = "Limpiar campos";
	public static final String BTN_ACT_VENTAS = "Actualizar";
	
	private JTextField txtIdEmple;
	private JTextField txtHora;
	private JTextField txtFecha;
	private JButton btnRegVenta;
	private JButton btnLimpiarVenta;
	private JButton btnRefresTabla;
	private JTable tblVentas;
	private JScrollPane scrpTabla;
	private DefaultTableModel dtmTablaVentas;
	
	
	
	public PRegistrarVenta() {
		inicializarComponentes();
		
	}
	
	

	private void inicializarComponentes() {
		setSize(800,600);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Venta");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);
		
		JLabel lblidEmple = new JLabel("ID empleado:");
		lblidEmple.setBounds(94, 117, 99, 14);
		add(lblidEmple);
		
		txtIdEmple = new JTextField();
		txtIdEmple.setBounds(261, 114, 86, 20);
		add(txtIdEmple);
		txtIdEmple.setColumns(10);
		
		JLabel lblHora = new JLabel("Hora  venta:");
		lblHora.setBounds(94, 223, 99, 14);
		add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setBounds(261, 220, 86, 20);
		add(txtHora);
		txtHora.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha venta:");
		lblFecha.setBounds(94, 337, 99, 14);
		add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(261, 334, 86, 20);
		add(txtFecha);
		txtFecha.setColumns(10);
		
		btnRegVenta = new JButton(BTN_REG_VENTA);
		btnRegVenta.setBounds(119, 458, 135, 23);
		add(btnRegVenta);
		
		btnLimpiarVenta = new JButton(BTN_LIMPIAR_VENTA);
		btnLimpiarVenta.setBounds(514, 458, 135, 23);
		add(btnLimpiarVenta);
		
		btnRefresTabla = new JButton(BTN_ACT_VENTAS);
		btnRefresTabla.setBounds(609, 77, 138, 23);
		add(btnRefresTabla);
		
		scrpTabla = new JScrollPane();
		scrpTabla.setBounds(419, 111, 328, 323);
		add(scrpTabla);
		
		tblVentas = new JTable();
		scrpTabla.setViewportView(tblVentas);
		
		JLabel lblVentas = new JLabel("Ventas:");
		lblVentas.setBounds(419, 84, 86, 14);
		add(lblVentas);
		
		configurarTabla();

	}
	
	public void rellenarTabla(ArrayList<Ventas> listaVentas) {
		dtmTablaVentas.getDataVector().clear();
		Object[] fila = new Object[4];
		
		for (Ventas venta : listaVentas) {
			fila[0] = venta.getIdVenta();
			
			fila[1] = venta.getEmpleado();
			
			fila[2] = venta.getHoraVenta();
			
			fila[3] = venta.getFechaVenta();
			
			dtmTablaVentas.addRow(fila);
		}
	}
	
	
	private void configurarTabla() {
		dtmTablaVentas = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblVentas.setModel(dtmTablaVentas);
		
		dtmTablaVentas.addColumn(DBContract.COL_ID_VENTA);
		dtmTablaVentas.addColumn(DBContract.COL_ID_EMP);
		dtmTablaVentas.addColumn(DBContract.COL_HORA_VENTA);
		dtmTablaVentas.addColumn(DBContract.COL_FECHA_VENTA);
		
		tblVentas.getColumn(DBContract.COL_ID_VENTA).setPreferredWidth(20);
		tblVentas.getColumn(DBContract.COL_ID_EMP).setPreferredWidth(20);
		tblVentas.getColumn(DBContract.COL_HORA_VENTA).setPreferredWidth(30);
		tblVentas.getColumn(DBContract.COL_FECHA_VENTA).setPreferredWidth(40);
	}



	public void setControlador(GestorControl controlador) {
		btnLimpiarVenta.addActionListener(controlador);
		btnRegVenta.addActionListener(controlador);
		btnRefresTabla.addActionListener(controlador);
		
	}



	public void limpiarCamposVenta() {
		txtFecha.setText("");
		txtHora.setText("");
		txtIdEmple.setText("");
	}
	
	
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}



	public Ventas obtenerDatosVenta() {
		Ventas venta =  null;
		
		try {
			int idEmple =  Integer.valueOf( txtIdEmple.getText());
			if (idEmple <= 0) {
				mostrarError("El ID del empleado ser mayor que 0");
			} else {
				String hora = txtHora.getText();
				if (hora.isBlank()) {
					mostrarError("La hora no puede estar vacía");
				} else {
					String fecha = txtFecha.getText();
					if (fecha.isBlank()) {
						mostrarError("La fecha no puede estar vacía");
					} else {
						venta = new Ventas(0, idEmple, hora, fecha);
					}
				}
				
			}
		} catch (NumberFormatException e) {
			mostrarError("El ID del empleado ser un valor numérico");
		}
		
		return venta;
	}
}