package gestion;

public class gestion_exemplaire {
	public gestion_exemplaire() {
		// TODO Auto-generated constructor stub
		String tablename="exemplaire";
		String[]columns= new String[]{"NumEx", "DateAchatEx", "EtatEx", "PrixEx", "DateEdit", "CodeO"};
		String[]types= new String[]{"i","D","i","f","D","i"};
		gestion_entite p1=new gestion_entite(tablename, columns, types);
	}
	public static void main(String[] args) {
		new gestion_exemplaire();
	}
}