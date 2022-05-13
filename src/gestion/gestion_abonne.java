package gestion;

public class gestion_abonne {
	public gestion_abonne() {
		// TODO Auto-generated constructor stub
		String tablename="abonne";
		String[]columns= new String[]{"CodeAb", "NomAb", "PrenomAb", "DateNaissAb", "EmailAb", "DateAb", "DateRenouvAb", "CodeTA"};
		String[]types= new String[]{"i","s","s","D","s","D","D","i"};
		gestion_entite p1=new gestion_entite(tablename, columns, types);
	}
	public static void main(String[] args) {
		new gestion_abonne();
	}
}