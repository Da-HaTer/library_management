package gestion;

public class gestion_typeabonnement {
	public gestion_typeabonnement() {
		// TODO Auto-generated constructor stub
		String tablename="typeabonnement";
		String[]columns= new String[]{"CodeTA", "IntituleTA", "delaiTA", "NbLivresTA", "PrixTA"};
		String[]types= new String[]{"i","s","i","i","f"};
		gestion_entite p1=new gestion_entite(tablename, columns, types);
	}
	public static void main(String[] args) {
		new gestion_typeabonnement();
	}
}