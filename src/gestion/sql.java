package gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import lib.MYSQL_lib;




public class sql {

	
	
	public static String[][] getOExistant() {
		// TODO Auto-generated method stub
		ArrayList<String[]> u=new ArrayList<>();
		String t[]=new String[2];
		try{  
			java.sql.Connection conn=MYSQL_lib.getconnection();
	        String s="Select CodeO, Titre From Ouvrage ;";
	        PreparedStatement stmt = conn.prepareStatement(s);  
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	        	t[0]=rs.getString(1);
		        t[1]=rs.getString(2);
	        	u.add(t);
	        }
	        conn.close();  
	    }catch(Exception ex){
	        System.out.println(ex);
	    }  
		String[][] vals=new String[u.size()][3];
		vals=u.toArray(vals);
		return vals;	}
	
	
	
	public static String[][] getODispo(int dispo) {
		// TODO Auto-generated method stub
		ArrayList<String[]> u=new ArrayList<>();
		String t[]=new String[2];
		try{  
			java.sql.Connection conn=MYSQL_lib.getconnection();
	        String s=String.format("Select idO, TitreO From ouvrage, exemplaire Where EtatEx = %d And ouvrage.idO = exemplaire.CodeO;",dispo);
	        PreparedStatement stmt = conn.prepareStatement(s);  
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	        	t[0]=rs.getString(1);
		        t[1]=rs.getString(2);
	        	u.add(t);
	        }
	        conn.close();  
	    }catch(Exception ex){
	        System.out.println(ex);
	    }  
		String[][] vals=new String[u.size()][3];
		vals=u.toArray(vals);
		return vals;
	}
	
	
	
	
	public static String[][] getOIndispo() {
		// TODO Auto-generated method stub
		ArrayList<String[]> u=new ArrayList<>();
		String t[]=new String[2];
		try{  
			java.sql.Connection conn=MYSQL_lib.getconnection();  
	        String s=" Select CodeO, Titre From Ouvrage, Exemplaire Where EtatEx In = ‘0’ And Ouvrage.CodeO = Exemplaire.CodeO ;";
	        PreparedStatement stmt = conn.prepareStatement(s);  
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	        	t[0]=rs.getString(1);
		        t[1]=rs.getString(2);
	        	u.add(t);
	        }
	        conn.close();  
	    }catch(Exception ex){
	        System.out.println(ex);
	    }  
		String[][] vals=new String[u.size()][3];
		vals=u.toArray(vals);
		return vals;
	}

	
	
	public static String[][] getOAbonne(String abonne) {
		// TODO Auto-generated method stub
		ArrayList<String[]> u=new ArrayList<>();
		String t[]=new String[6];
		try{  
			java.sql.Connection conn=MYSQL_lib.getconnection();
	        String s=" Select  NomAb, PrenomAb, CodeO, NumEx, TitreO, DateEmp        From   Ouvrage O, Exemplaire E, Emprunter Emp, Abonne A Where CodeAb =? And EtatEx = ‘0’ And A.CodeAb = Emp.CodeAb And  O.CodeO = E.CodeOAnd    Emp.NumEx =  E.NumEx;";
	        PreparedStatement stmt = conn.prepareStatement(s);
	        stmt.setString(1,abonne);
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	        	t[0]=rs.getString(1);
		        t[1]=rs.getString(2);
		        t[2]=rs.getString(3);
		        t[3]=rs.getString(4);
		        t[4]=rs.getString(5);
		        t[5]=rs.getString(6);
	        	u.add(t);
	        }
	        conn.close();  
	    }catch(Exception ex){
	        System.out.println(ex);
	    }  
		String[][] vals=new String[u.size()][6];
		vals=u.toArray(vals);
		return vals;
	}
	
	
	
	public static String[][] getOpdAbonne(String abonne) {
		// TODO Auto-generated method stub
		ArrayList<String[]> u=new ArrayList<>();
		String t[]=new String[3];
		try{  
			java.sql.Connection conn=MYSQL_lib.getconnection();
	        String s="  Select  CodeO, TitreO, max(count(*)) From    Ouvrage O, Emprunter Emp, Exemplaire E Where  CodeAb = ? And      E.NumEx = Emp.NumEx And      O.CodeO = E.CodeO Group by CodeO;";
	        PreparedStatement stmt = conn.prepareStatement(s);
	        stmt.setString(1,abonne);
	        ResultSet rs = stmt.executeQuery();
	        while(rs.next()) {
	        	t[0]=rs.getString(1);
		        t[1]=rs.getString(2);
		        t[2]=rs.getString(3);
	        	u.add(t);
	        }
	        conn.close();  
	    }catch(Exception ex){
	        System.out.println(ex);
	    }  
		
		String[][] vals=new String[u.size()][3];
		vals=u.toArray(vals);
		return vals;
	}
	
	
	
	static public void main(String[] args) {
		
	    
	}
	
}
