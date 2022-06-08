package pi.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import pi.db.persistencia.DBPersistencia;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class PConsultarStock extends JPanel {
	private JComboBox cmbTipo;
	private DefaultTableModel dtmProd;
	
	
	public PConsultarStock() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta de Stock");
		lblNewLabel.setBounds(36, 7, 180, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtros");
		lblNewLabel_1.setBounds(19, 43, 29, 14);
		add(lblNewLabel_1);
		
		cmbTipo = new JComboBox();
		cmbTipo.setBounds(75, 72, 107, 22);
		add(cmbTipo);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo");
		lblNewLabel_2.setBounds(19, 76, 46, 14);
		add(lblNewLabel_2);
	}
	
	public void asignarTipo() {
		DBPersistencia dPersis = new DBPersistencia();
		DefaultComboBoxModel<String> modelo = (DefaultComboBoxModel<String>) cmbTipo.getModel();
		for(String tipo : dPersis.getTiposProd()) {
			modelo.addElement(tipo);
		}
	}
	
	public void configurarTabla() {
		
		
		
	}
}
