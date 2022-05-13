package gestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lib.MYSQL_lib;
import user.Librarian;

public class gestion_entite {
	public generic_table table_api;
	
	public gestion_entite(String tablename,String[]columns,String[]types) {
		// TODO Auto-generated constructor stub
		
		Vector<String> cols=new Vector<>(Arrays.asList(columns));
        JFrame f = new JFrame("Gestion "+tablename);
        f.setLayout(new FlowLayout());
//        String[][] data= data_fromarraylist(new Librarian().getListlibrarians());
        String[][] data=MYSQL_lib.get_data(tablename, columns.length);
        table_api=new generic_table(tablename,cols,data);
        JButton restore=table_api.restore;
        //p1.hide_ui();
//        p1.addButton.setVisible(false);
        restore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new gestion_entite(tablename,columns,types);
			}
			
		});
        JButton validate=table_api.valider;
        validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[][] new_data=table_api.get_data();
//				System.out.println(Arrays.deepToString(data));
//				System.out.println(Arrays.deepToString(new_data));
				//check for something new locally
				for (int i = 0; i < new_data.length; i++) {
					boolean isnew=true;
					for (int j = 0; j < data.length && isnew; j++) {
						if (data[j][0].equals(new_data[i][0])) {
							isnew=false;
							if (!arrayequals(new_data[i],(data[j]))) // System.out.println("updating");} ///not updating if last column is empty
								MYSQL_lib.save_entite(tablename, columns, types, new_data[i]); //update
//								System.out.println(Arrays.deepToString(new_data[i]));} //update()
						}
					}
					
					if (isnew)
						MYSQL_lib.save_entite(tablename, columns, types, new_data[i]); //update
//						System.out.println(Arrays.deepToString(new_data[i])); //add new
//					}

				}
				//check if something is deleted locally
				for (int i = 0; i < data.length; i++) {
					boolean iskept=false;
					for (int j = 0; (j < new_data.length) && (!iskept); j++) {
						
						if(data[i][0].equals(new_data[j][0])) {

							iskept=true;}
					}
					if (!iskept) {
//						System.out.println("matiere of id "+data[i][0]+" is deleted");
						MYSQL_lib.delete_entite(tablename, columns, data[i][0]);
//						new Librarian().delete_librarian((Integer.parseInt(data[i][0])));
					}
				}
				//check if deleted
				
			f.dispose();
			new gestion_entite(tablename,columns,types);
			}
		});
        
        
        f.getContentPane().add(table_api);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//      f.setSize(340,250);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
	public boolean arrayequals(String[] a1,String[]a2) {///duplicate (make imported
		if (a1.length!=a2.length) return false;
		boolean equals=true;	
		for (int i = 0; i < a2.length && equals; i++) {
			if (!(a1[i].equals(a2[i]))) equals=false;
		}
		return equals;
	}
    public static void main(String[] args) {
    	String tablename="ouvrage";
		String[]columns= new String[]{"idO","TitreO","DateO", "NomAut"};
		String[]types= new String[]{"i","s","D","s"};
        new gestion_entite(tablename,columns,types);
    }
}
