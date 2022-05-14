package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;

import gestion.generic_table;
import gestion.gestion_entite;
import gestion.gestion_ouvrage;
import lib.MYSQL_lib;

public class affichePL extends JFrame{
	public affichePL()  {
		String tablename="abonne";
		String[]column= new String[]{"CodeAb", "NomAb", "PrenomAb", "DateNaissAb", "EmailAb", "DateAb", "DateRenouvAb", "CodeTA"};
		Vector<String> columns = new Vector<String>(Arrays.asList(column));
		String[][] data=MYSQL_lib.get_data(tablename, column.length);
		generic_table p1=new generic_table(tablename, columns, data);
		add(p1);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);	
		p1.hide_ui();
		p1.delete.setVisible(false); 
		p1.addButton.setVisible(false);
		p1.restore.setVisible(false);
		p1.valider.setText("recherche"); 
		p1.updateButton.setText("retour");
		p1.valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (p1.table.getSelectedRow()!=-1){
					dispose();
					String id=(String) p1.table.getModel().getValueAt(p1.table.getSelectedRow(),0);
					new ouvragePL(id);
				}
				else {
					System.out.println("none selected");
				}
			}
			
		});
	
	}
	
	public static void main(String[] args) {
	new affichePL();
		
		
	}
	
	
}
