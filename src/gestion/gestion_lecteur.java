package gestion;

public class gestion_lecteur {
	public gestion_lecteur() {
		// TODO Auto-generated constructor stub
		String tablename="abonne";
		String[]columns= new String[]{"CodeAb", "NomAb", "PrenomAb", "DateNaissAb", "EmailAb", "DateAb", "DateRenouvAb", "CodeTA"};
		String[]types= new String[]{"i","s","s","D","s","D","D","i"};
		gestion_entite p1=new gestion_entite(tablename, columns, types);
		p1.table_api.hide_ui();
		p1.setTitle("Lecteurs");
		p1.table_api.title.setText("lecteurs");;
		p1.table_api.delete.setVisible(false); 
		p1.table_api.addButton.setVisible(false);
		p1.table_api.restore.setVisible(false);
		
		p1.table_api.valider.setText("recherche"); // 5edma mta3 chadi
		p1.table_api.updateButton.setText("retour");
	}
	public static void main(String[] args) {
		new gestion_lecteur();
	}
}