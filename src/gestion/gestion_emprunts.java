package gestion;

public class gestion_emprunts {
	public gestion_emprunts() {
		// TODO Auto-generated constructor stub
		String tablename="emprunter";
		String[]columns= new String[]{"CodeAb", "NumEx", "DateEmp", "DateRetour"};
		String[]types= new String[]{"i","i","D","D"};
		gestion_entite p1=new gestion_entite(tablename, columns, types);
	}
	public static void main(String[] args) {
		new gestion_emprunts();
	}
}