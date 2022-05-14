package UI;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gestion.generic_table;
import gestion.sql;

public class ouvragePL extends JFrame {

	private JScrollPane jScrollPane1;
	private JTable jTable1;
	public ouvragePL(String id) {
		Vector<String> cols= new Vector<String>();
		cols.add("code ouvrage");
		cols.add("Titre");
		String[][] data=sql.getOpdAbonne(id); //data
		generic_table p1=new generic_table("ouvrage plus demande", cols, data);
		p1.hide_ui();
		add(p1);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ouvragePL("1");
	}

}
