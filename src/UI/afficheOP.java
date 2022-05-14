package UI;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gestion.generic_table;
import gestion.sql;

public class afficheOP extends JFrame{
	private JScrollPane jScrollPane1;
	private JTable jTable1;
	public afficheOP(int prete)
	{
		Vector<String> cols=new Vector<String>();
		cols.add("code ouvrage");
		cols.add("Titte");
		String[][] data=sql.getODispo(prete);
		generic_table p1=new generic_table("liste des ouvrages les plus demandes", cols, data);
		p1.hide_ui();
		add(p1);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	public static void main(String[] args) {
		new afficheOP(1);
	}
}
