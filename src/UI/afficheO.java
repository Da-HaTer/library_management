package UI;

import gestion.gestion_entite;
import gestion.gestion_ouvrage;

public class afficheO {
	public afficheO() {
		// TODO Auto-generated constructor stub
		String tablename="ouvrage";
		String[]columns= new String[]{"idO","TitreO","DateO", "NomAut"};
		String[]types= new String[]{"i","s","D","s"};
		gestion_entite p1=new gestion_entite(tablename, columns, types);
		p1.table_api.hide_ui();
		p1.table_api.addButton.setVisible(false);
		p1.table_api.updateButton.setVisible(false);
		p1.table_api.delete.setVisible(false);
		p1.table_api.valider.setVisible(false);
		p1.table_api.restore.setVisible(false);
	}
	public static void main(String[] args) {
		new afficheO();
	}
}