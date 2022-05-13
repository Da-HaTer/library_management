package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;
import user.Utilisateur;

public class Ouvrage {

	private Integer ido;
	private String titreo;
	private String DateO; ///parse
	private String Nom_aut;

	public Ouvrage() {
		// TODO Auto-generated constructor stub
	}
	public Ouvrage(Integer ido,String titreo, String DateO ,String Nom_aut) {
		// TODO Auto-generated method stub
		this.ido=ido;
		this.titreo=titreo;
		this.DateO=DateO; ///parse
		this.Nom_aut=Nom_aut;
	}

public void delete_Ouvrage(int id) {
	try {
	    	String query="delete from ouvrage where Codeab=?;";
	    	java.sql.Connection connection=MYSQL_Connection.getconnection();
	        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	        preparedStmt.setInt(1, id);
	        int rowsaffected = preparedStmt.executeUpdate();
	        System.out.println(rowsaffected);
	        connection.close();
		}
	catch (SQLException e) {e.printStackTrace();}
}


public void save_Ouvrage(boolean previlege) { /// fix attributes
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

public Ouvrage fetch_Ouvrage(int id) { ///change type to admin
    try{ 
        String query = "select * from ouvrage where idO=?;";
        java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        
        preparedStmt.setInt(1, id);
        
        ResultSet resultSet = preparedStmt.executeQuery();
        Ouvrage abonne = null;
        while (resultSet.next()) {
        		abonne = new Ouvrage(); ///fix attributs
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


public ArrayList<Ouvrage> getlistOuvrages() {///code
	return null;
}


}