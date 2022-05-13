package UI;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import gestion.gestion_abonne;
import gestion.gestion_admin;
import gestion.gestion_bibliothecaire;
import gestion.gestion_emprunts;
import gestion.gestion_exemplaire;
import gestion.gestion_librarian;
import gestion.gestion_ouvrage;
import gestion.gestion_typeabonnement;
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
		String[] data= {"Bibliothecaires","Abonnes","Types abonnements","Emprunts","Exemplaires","Ouvrages"};
		JComboBox<String> c=new JComboBox<String>(data);
		return c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();	
		if (source==validate) {
			String choix=entity_selection.getSelectedItem().toString();
			
			switch (choix) {
				case "Bibliothecaires": {
//					System.out.println("etudiant");
					new gestion_bibliothecaire();
					break;
				}
				case "Abonnes": {
//					System.out.println("etudiant");
					new gestion_abonne();
					break;
				}
				case "Types abonnements": {
//					System.out.println("etudiant");
					new gestion_typeabonnement();
					break;
				}
				case "Emprunts": {
////					System.out.println("etudiant");
					new gestion_emprunts();
//					break;
				}
				case "Exemplaires": {
					new gestion_exemplaire();
//					System.out.println("enseignant");
					break;
				}
				case "Ouvrages": {
					new gestion_ouvrage();
////					System.out.println("enseignant");
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