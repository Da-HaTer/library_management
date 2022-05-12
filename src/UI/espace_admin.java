package UI;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import gestion.gestion_admin;
import gestion.gestion_librarian;
import gestion.gestion_utilisateur;
import main.login_form;
import user.Admin;
import user.Utilisateur;

public class espace_admin extends espace_librarian{
	public Utilisateur admin;
	public espace_admin(Utilisateur user) {
		super();
		setTitle("espace admin");
		admin=user;
		// TODO Auto-generated constructor stub
	}
	
	@Override
 	public JComboBox<String> get_entities() { ///make this external
		// TODO Auto-generated method stub
		///TODO
		//auto import classes here
		String[] data= {"Bibliothecaire","Admin","Utilisateur","Abonnements","Emprunts","Abonnes","Ouvrages"};
		JComboBox<String> c=new JComboBox<String>(data);
		return c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();	
		if (source==validate) {
			String choix=entity_selection.getSelectedItem().toString();
			
			switch (choix) {
				case "Bibliothecaire": {
//					System.out.println("etudiant");
					new gestion_librarian();
					break;
				}
				case "admin": {
//					System.out.println("etudiant");
					new gestion_admin();
					break;
				}
				case "Emprunts": {
//					System.out.println("etudiant");
					new gestion_admin();
					break;
				}
				case "Abonnements": {
////					System.out.println("etudiant");
//					new gestion_etudiant();
//					break;
				}
				case "Utilisateur": {
					new gestion_utilisateur(false);
//					System.out.println("enseignant");
					break;
				}
				case "Abonnes": {
//					new gestion_enseignant();
////					System.out.println("enseignant");
					break;
				}
				case "Ouvrages": {
//					new gestion_matiere();
//					System.out.println("matiere");
					break;
				}
			}
		}
		else if (source==deconnection) {
			dispose();
			new login_form();
		}
	}
	
	public static void main(String[] args) {
		new espace_admin(null);
	}
}