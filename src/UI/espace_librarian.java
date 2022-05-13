package UI;

 import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gestion.gestion_abonne;
import gestion.gestion_bibliothecaire;
import gestion.gestion_classe;
import gestion.gestion_emprunts;
import gestion.gestion_enseignant;
import gestion.gestion_etudiant;
import gestion.gestion_exemplaire;
import gestion.gestion_matiere;
import gestion.gestion_ouvrage;
import gestion.gestion_typeabonnement;
import gestion.gestion_utilisateur;
import main.login_form;

 public class espace_librarian extends JFrame implements ActionListener{
 JComboBox<String> entity_selection;//,action_selection;
 JButton validate,deconnection,somebutton;
     public espace_librarian(){ 
    	 setSize(350, 160);
     	 setLocationRelativeTo(null);
     	 setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         setFont(new Font("SansSerif", Font.PLAIN, 14));
         setLayout(null);
         
         JPanel contentpane= new JPanel();
         contentpane.setBounds(10, 10,100,200);
         contentpane.setLayout(new GridLayout(3,2,30,10));
         contentpane.setSize(300,100);
         add(contentpane);
         
         validate = new JButton("Suivant");
         somebutton = new JButton("Badel essmi");
         deconnection = new JButton("deconnection");
         validate.addActionListener(this);
         somebutton.addActionListener(this);
         deconnection.addActionListener(this);
         contentpane.add(new JLabel("Selectionner entite"));
         entity_selection=get_entities();
         contentpane.add(entity_selection);
//         contentpane.add(new JLabel("Selection action"));
//         action_selection=get_actions();
//         contentpane.add(action_selection);
         contentpane.add(validate);
         contentpane.add(deconnection);
         contentpane.add(somebutton);
         setVisible(true);
     }
     public JComboBox<String> get_entities() { ///make this external
 		// TODO Auto-generated method stub
 		///TODO
 		//auto import classes here
 		String[] data= {"Abonnes","Typeabonnement","Emprunts","Exemplaires","Ouvrages"};
 		JComboBox<String> c=new JComboBox<String>(data);
 		return c;
 	}
 	
 	@Override
 	public void actionPerformed(ActionEvent e) {
 		Object source=e.getSource();	
 		if (source==validate) {
 			String choix=entity_selection.getSelectedItem().toString();
 			
 			switch (choix) {
 				case "Abonnes": {
// 					System.out.println("etudiant");
 					new gestion_abonne();
 					break;
 				}
 				case "Typeabonnement": {
// 					System.out.println("etudiant");
 					new gestion_typeabonnement();
 					break;
 				}
 				case "Emprunts": {
//// 					System.out.println("etudiant");
 					new gestion_emprunts();
// 					break;
 				}
 				case "Exemplaires": {
 					new gestion_exemplaire();
// 					System.out.println("enseignant");
 					break;
 				}
 				case "Ouvrages": {
 					new gestion_ouvrage();
//// 					System.out.println("enseignant");
 					break;
 				}
 			}
 		}
		else if (source==deconnection) {
			dispose();
			new login_form();
		}
		else if (source==somebutton) {
			dispose();
			new interface_interrogation();
		}
	}
 	public static void main(String[] args) {
		new espace_librarian();
	}
 }