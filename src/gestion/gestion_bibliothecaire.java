package gestion;

public class gestion_bibliothecaire {
	public gestion_bibliothecaire() {
		// TODO Auto-generated constructor stub
		String tablename="bibliothecaire";
		String[]columns= new String[]{"idB", "Nom", "Prenom", "Login", "Password", "typeB"};
		String[]types= new String[]{"i","s","s","s","s","i"};
		new gestion_entite(tablename, columns, types);
	}
	public static void main(String[] args) {
		new gestion_bibliothecaire();
	}
}