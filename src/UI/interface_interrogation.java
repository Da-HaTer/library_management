package UI;


	import java.util.*;
  

	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.naming.event.NamingExceptionEvent;
	import javax.swing.JButton;
	import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
	import javax.swing.JTextField;
	import javax.swing.JPasswordField;
	import javax.swing.UIManager;



	import java.awt.Component;
	import java.awt.FlowLayout;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ItemEvent;
	import java.awt.event.ItemListener;

	public class interface_interrogation extends JFrame implements ActionListener{
		
	
		JPanel contentPane =(JPanel) this.getContentPane() ;
		
		JButton b1 =new JButton ("Les ouvrages existants");
		JButton b2 =new JButton ("Les ouvrages  prêtés");
		JButton b3 =new JButton ("Les ouvrages disponibles");
		JButton b4 =new JButton ("L’ouvrage le plus demandé par un lecteur donné.");
		JButton b5 =new JButton ("Les ouvrages qui sont en possession d’un lecteur donné");
		JButton retour =new JButton ("Retour");
		JLabel classlab = new JLabel ("Selectionner la Classe");
		JComboBox<Object> class_selection;
		JPanel classe = new JPanel ();
		
		String classe_selected="mi";
		
		public interface_interrogation(){
            
			super("Informations");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(550, 480);
			this.setLocationRelativeTo(contentPane);
			
			try {
			
            }catch (Exception e) {}
			contentPane.setLayout(null);

			contentPane.add(b1);
			b1.setBounds(50, 20, 450, 50);
			b1.addActionListener(this);
			
			contentPane.add(b2);
			b2.setBounds(50, 90, 450,50);
			b2.addActionListener(this);
			
			contentPane.add(b3);
			b3.setBounds(50, 165, 450,50);
			b3.addActionListener(this);
			
			contentPane.add(b4);
			b4.setBounds(50, 240,450,50);
			b4.addActionListener(this);
			
			contentPane.add(b5);
			b5.setBounds(50, 310,450,50);
			b5.addActionListener(this);
			
			
			contentPane.add(retour);
			retour.setBounds(200, 380, 120, 30);
			retour.addActionListener(this);
		
			this.setVisible(true);
			
		}


		
		public void actionPerformed(ActionEvent e)  {
			
			Object source = e.getSource();
			
            if(source==b1)
            {
            	new afficheO();
				// liste des ouvrage existants
            }
            if(source==b2){
            	new afficheOP(1);
            	// liste des ouvrage disponibles
            }
            if(source==b3){
            	new afficheOP(0);
			}
            if(source==b4){
            	new affichePD();
            }
            if(source==b5){
				new affichePL();	
            }
		}
		
				
		
	public static void main(String[] args){
		
	new interface_interrogation();

	}
	}