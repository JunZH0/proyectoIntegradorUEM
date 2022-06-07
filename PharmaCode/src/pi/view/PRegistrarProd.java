package pi.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class PRegistrarProd extends JPanel {
	public PRegistrarProd() {
		setLayout(new MigLayout("", "[][][][][][][][]", "[][]"));
		
		JLabel lblRegistrarProductos = new JLabel("Registrar Productos");
		add(lblRegistrarProductos, "cell 4 1");
	}

}
