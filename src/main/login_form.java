package main;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

//import java.awt.GridLayout;
//import javax.swing.BorderFactory;
import javax.swing.*;

import UI.espace_admin;
import UI.espace_enseignant1;
import UI.espace_etudiant;
import UI.espace_superadmin;
import model.Classe;
import user.Admin;
import user.Librarian;
import user.Etudiant;
import user.Utilisateur;

public class login_form extends JFrame implements ActionListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	private int count=0;
//	private JFrame frame;
	private JLabel userlabel;
	private JButton button;
	private JTextField login;
	private JLabel passwordlabel;
	private JPasswordField Passwordtext;
	private JPanel panel;
	private JLabel message;

	public static final int librarianTypeCode = 1;
	public static final int adminTypeCode = 2;

	Vector <Utilisateur> users=new Vector<>();
	Vector <Classe> classes=new Vector<>();

//	private void initUserList() {
//		users.add(new Etudiant("user1", "Ahmed", "Ben Ahmed", "ahmed", "1234"));
//		users.add(new Enseignant("Imene", "12345"));
//		users.add(new Admin("admin", "*****"));
//		users.add(new Super_Admin("root", "toor"));
//	}

    public login_form(){
//    	initUserList();
    	panel = new JPanel();
//        frame = new JFrame();

        setSize(400, 180);

        setLocationRelativeTo(null); //centered
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(panel);
        panel.setLayout(null);

        userlabel= new JLabel("User");
        userlabel.setBounds(10,20,80,25);
        panel.add(userlabel);

        login=new JTextField(20);
        login.setBounds(100,20,165,25);
        panel.add(login);

        passwordlabel= new JLabel("Password");
        passwordlabel.setBounds(10,50,80,25);
        panel.add(passwordlabel);

        Passwordtext=new JPasswordField(20);
        Passwordtext.setBounds(100,50,165,25);
        panel.add(Passwordtext);

        button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(this);
        panel.add(button);

        message=new JLabel("");
        message.setBounds(10,110,100,25);
        panel.add(message);
        setVisible(true);
    }
    public static void main(String[] args) {
        new login_form();
    }

//    private Utilisateur connect(String l,String p){ deprecated after integration of getUserFromDB (user class)
//    	for(Utilisateur u:users) {
//			if (u.seConnecter(l, p)){
//				return u;
//			}
//		}
//    	return null ;
//    }
	@Override
	public void actionPerformed(ActionEvent e) {
		String l= this.login.getText();
		String p= this.Passwordtext.getText();
		Utilisateur user = new Utilisateur(l, p);
		user = user.getUserFromDB(); //null if not found
//		Utilisateur user=connect(l,p); deprecated
		if(user!=null){
//			System.out.println(user.getClass());
			dispose();

			switch (user.getType()) {

				case librarianTypeCode: { /// complete these
					Librarian librarian= new Librarian().fetch_Librarian(user.idref); ///create class
					new espace_librarian(librarian);
					break;
				}
				case adminTypeCode:{
					Admin admin= new Admin().fetch_Admin(user.idref);
					new espace_admin();
					break;
				}
			}
		}
		else {
			message.setText("user not found !!"); ///temp, make this alert instead of message
		}

	}
}