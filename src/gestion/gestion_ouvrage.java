package gestion;

public class gestion_ouvrage {
	public gestion_ouvrage() {
		// TODO Auto-generated constructor stub
		String tablename="ouvrage";
		String[]columns= new String[]{"idO","TitreO","DateO", "NomAut"};
		String[]types= new String[]{"i","s","D","s"};
		gestion_entite p1=new gestion_entite(tablename, columns, types);
		
	}
	public static void main(String[] args) {
		new gestion_ouvrage();
	}
}