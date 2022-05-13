package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import lib.MYSQL_Connection;
import user.Utilisateur;

public class Exemplaire {

	private Integer NumEx;
	private String DateAchatEx;
	private Integer EtatEx;
	private Double PrixEx;
	private String DateEdit; ///parse
	private Integer CodeO;

	public Exemplaire() {
		// TODO Auto-generated constructor stub
	}
	public Exemplaire(Integer NumEx, String DateAchatEx,Integer EtatEx,Double PrixEx,String DateEdit,Integer CodeO) {
		// TODO Auto-generated method stub
		this.NumEx=NumEx;
		this.DateAchatEx=DateAchatEx;
		this.EtatEx=EtatEx; ///parse
		this.PrixEx=PrixEx;
		this.DateEdit=DateEdit;
		this.CodeO=CodeO;
	}

public void delete_Exemplaire(int id) {
	try {
	    	String query="delete from exemplaire where NumEx=?;";
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

public Exemplaire fetch_Exemplaire(int id) {
    try{ 
        String query = "select * from exemplaire where NumEx=?;";
        java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        
        preparedStmt.setInt(1, id);
        
        ResultSet resultSet = preparedStmt.executeQuery();
        Exemplaire instance = null;
        while (resultSet.next()) {
        		instance = new Exemplaire(); ///fix attributs
//        		emprunt.setCodeab(resultSet.getInt(1));
//        		emprunt.setNumEx(resultSet.getInt(2));
//        		emprunt.setDateEmp(resultSet.getString(3));
//        		emprunt.setDateretour(resultSet.getString(4));
        }
        connection.close();
        return instance;
    }
    catch (SQLException e) {e.printStackTrace();}
    return null;
}


public ArrayList<Exemplaire> getlistExemplaires() {///code
	return null;
}


}