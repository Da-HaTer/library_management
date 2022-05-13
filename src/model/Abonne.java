package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;
import user.Utilisateur;

public class Abonne {

	private Integer codeab;
	private String Datenaissance;
	private String Nom; ///parse
	private String Prenom;
	private String Email;
	private String dateab;
	private String daterenouvab;
	private Integer codeTA;

	public Abonne() {
		// TODO Auto-generated constructor stub
	}
	public Abonne(Integer codeab,String Datenaissance, String Nom ,String Prenom, String Email ,String dateab, String daterenouvab,Integer codeTA) {
		// TODO Auto-generated method stub
		this.codeab=codeab;
		this.Datenaissance=Datenaissance;
		this.Nom=Nom; ///parse
		this.Prenom=Prenom;
		this.Email=Email;
		this.dateab=dateab;
		this.daterenouvab=daterenouvab;
		this.codeTA=codeTA;
	}

public Integer getCodeab() {
		return codeab;
	}
	public void setCodeab(Integer codeab) {
		this.codeab = codeab;
	}
	public String getDatenaissance() {
		return Datenaissance;
	}
	public void setDatenaissance(String datenaissance) {
		Datenaissance = datenaissance;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDateab() {
		return dateab;
	}
	public void setDateab(String dateab) {
		this.dateab = dateab;
	}
	public String getDaterenouvab() {
		return daterenouvab;
	}
	public void setDaterenouvab(String daterenouvab) {
		this.daterenouvab = daterenouvab;
	}
	public Integer getCodeTA() {
		return codeTA;
	}
	public void setCodeTA(Integer codeTA) {
		this.codeTA = codeTA;
	}
public void delete_Abonne(int id) {
	try {
	    	String query="delete from abonne where Codeab=?;";
	    	java.sql.Connection connection=MYSQL_Connection.getconnection();
	        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	        preparedStmt.setInt(1, id);
	        int rowsaffected = preparedStmt.executeUpdate();
	        System.out.println(rowsaffected);
	        connection.close();
		}
	catch (SQLException e) {e.printStackTrace();}
}


public void save_Abonne(boolean previlege) { /// fix attributes
	try{
    	String query=String.format("update abonne set idUser=?,login=?, Pwd=?, idRef=?, userType=? \r\n"
    			+ "where Codeab=%d;",this.codeab);
    	if (fetch_Abonne(codeab)==null) query = "insert into user values (?,?,?,?,?);";
    	
        java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        
        if (idUser<=0) preparedStmt.setNull(1,idUser);
        else preparedStmt.setInt(1, idUser);
        
        preparedStmt.setString(2, login);
        preparedStmt.setString(3, motDePasse);
        
        if (idref<=0) preparedStmt.setNull(4,idref);
        else preparedStmt.setInt(4, idref);
        
        if (type<=0) preparedStmt.setNull(5,type);
        else preparedStmt.setInt(5, type);

        int rowsaffected = preparedStmt.executeUpdate();
        System.out.println(rowsaffected);

        connection.close();
    }
    
    catch (SQLException e) {e.printStackTrace();}
}

public Abonne fetch_Abonne(int id) { ///change type to admin
    try{ 
        String query = "select * from abonne where Codeab=?;";
        java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        
        preparedStmt.setInt(1, id);
        
        ResultSet resultSet = preparedStmt.executeQuery();
        Abonne abonne = null;
        while (resultSet.next()) {
        		abonne = new Abonne(); ///fix attributs
//        		emprunt.setCodeab(resultSet.getInt(1));
//        		emprunt.setNumEx(resultSet.getInt(2));
//        		emprunt.setDateEmp(resultSet.getString(3));
//        		emprunt.setDateretour(resultSet.getString(4));
        }
        connection.close();
        return abonne;
    }
    catch (SQLException e) {e.printStackTrace();}
    return null;
}






}