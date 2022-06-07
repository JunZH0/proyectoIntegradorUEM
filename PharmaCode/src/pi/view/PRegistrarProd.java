package pi.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PRegistrarProd extends JPanel {
	private JTextField txtNom;
	public PRegistrarProd() {
		init();
	}
	private void init() {
		setLayout(new MigLayout("", "[][][][][grow][][][]", "[][][][][][][][][]"));
		
		JLabel lblRegistrarProductos = new JLabel("Registrar Productos");
		add(lblRegistrarProductos, "cell 4 1");
		
		JLabel lblNombre = new JLabel("Nombre");
		add(lblNombre, "cell 1 3");
		
		txtNom = new JTextField();
		add(txtNom, "cell 4 3,growx");
		txtNom.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		add(lblCategoria, "cell 1 5");
		
		JButton btnGuardar = new JButton("Guardar");
		add(btnGuardar, "cell 4 8");
	}

}
