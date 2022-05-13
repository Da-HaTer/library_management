package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;
import user.Utilisateur;

public class Emprunt {

	private Integer codeab;
	private Integer NumEx;
	private String dateEmp; ///parse
	private String dateretour;

	public Emprunt() {
		// TODO Auto-generated constructor stub
	}
	public Emprunt(Integer codeab,Integer NumEx,String dateEmp, String dateretour) {
		// TODO Auto-generated method stub
		this.codeab=codeab;
		this.NumEx=NumEx;
		this.dateEmp=dateEmp;
		this.dateretour=dateretour;
	}


public Integer getCodeab() {
	return codeab;
}
public void setCodeab(Integer codeab) {
	this.codeab = codeab;
}
public Integer getNumEx() {
	return NumEx;
}
public void setNumEx(Integer numEx) {
	NumEx = numEx;
}
public String getDateEmp() {
	return dateEmp;
}
public void setDateEmp(String dateEmp) {
	this.dateEmp = dateEmp;
}
public String getDateretour() {
	return dateretour;
}
public void setDateretour(String dateretour) {
	this.dateretour = dateretour;
}

public void delete_Emprunt(int id) { ///shouldn't delete (like notes)
	try {
	    	String query="delete from user where idUser=?;";
	    	java.sql.Connection connection=MYSQL_Connection.getconnection();
	        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	        preparedStmt.setInt(1, id);
	        int rowsaffected = preparedStmt.executeUpdate();
	        System.out.println(rowsaffected);
	        connection.close();
		}
	catch (SQLException e) {e.printStackTrace();}
}


public void save_Emprunt(boolean previlege) { /// fix attributes
	try{
    	String query=String.format("update emprunter set idUser=?,login=?, Pwd=?, idRef=?, userType=? \r\n"
    			+ "where idUser=%d;",this.idUser);
    	if (fetch_Utilisateur(idUser)==null) query = "insert into user values (?,?,?,?,?);";
    	
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

public Emprunt fetch_Emprunt(int id) { ///change type to admin
    try{ 
        String query = "select * from emprunter where idUser=?;";
        java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        
        preparedStmt.setInt(1, id);
        
        ResultSet resultSet = preparedStmt.executeQuery();
        Emprunt emprunt = null;
        while (resultSet.next()) {
        		emprunt = new Emprunt();
        		emprunt.setCodeab(resultSet.getInt(1));
        		emprunt.setNumEx(resultSet.getInt(2));
        		emprunt.setDateEmp(resultSet.getString(3));
        		emprunt.setDateretour(resultSet.getString(4));
        }
        connection.close();
        return emprunt;
    }
    catch (SQLException e) {e.printStackTrace();}
    return null;
}






}