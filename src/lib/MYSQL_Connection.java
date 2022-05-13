package lib;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import model.Abonne;
import user.Utilisateur;

public class MYSQL_Connection {
	public static  java.sql.Connection getconnection() throws SQLException {
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test?characterEncoding=utf8","root","toor");
		return connection;
	}
	
	
	public void delete_entite(String tablename,String[] cols, int id) {
    	try {
    	String query= String.format("delete from %s where %s=%d;",tablename,cols[0],id);
    	java.sql.Connection connection=MYSQL_Connection.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        int rowsaffected = preparedStmt.executeUpdate();
//        System.out.println(rowsaffected);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
	public ArrayList<String> fetch_entite(String tablename,String[] cols,int id) { ///data as list
	    try{ 
	        String query = String.format("select * from %s where %s=%d;",tablename,cols[0],id);
	        java.sql.Connection connection=MYSQL_Connection.getconnection();
	        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	        ResultSet resultSet = preparedStmt.executeQuery();
	        ArrayList<String> vals= new ArrayList<String>();
	        while (resultSet.next()) {
	        	for (int i = 1; i <= cols.length; i++) {
	        		vals.add(resultSet.getString(i));
				}
	        	break;
	        }
	        connection.close();
	        return vals;
	    }
	    catch (Exception e) {
	    	System.out.println(e.getStackTrace());
			// TODO: handle exception
		}
		return null;
	}
	
	public String[][] get_data(String tablename,int colcount) { ///data as list
	    try{ 
	        String query = String.format("select * from %s ;",tablename);
	        java.sql.Connection connection=MYSQL_Connection.getconnection();
	        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
	        ResultSet resultSet = preparedStmt.executeQuery();
	        
	        ArrayList<String[]> vals= new ArrayList<String[]>();
	        while (resultSet.next()) {
	        	String[] s=new String[colcount];
	        	for (int i = 1; i <= colcount; i++) {
	        		s[i-1]=resultSet.getString(i);
				}
	        	vals.add(s);
	        }
	        
	        String[][]data=new String[vals.size()][colcount];
	        for (int i = 0; i < vals.size(); i++) {
				data[i]=vals.get(i);
			}
	        connection.close();
	        return data;
	    }
	    catch (Exception e) {
	    	System.out.println(e.getStackTrace());
			// TODO: handle exception
		}
		return null;
	}
	
    public void save_entite(String tablename,int id,String[] cols, String[] types,String[] data) { //to fix
    	
        try{
        	String metadata=update_metadata(cols,types,data); ///todo
        	String query=String.format("update %s set %s where %s=%d;",tablename,metadata,cols[0],id);
	    	if (fetch_entite(tablename,id,cols[0],cols.length)==null || fetch_entite(tablename,id,cols[0],cols.length).size()==0) {
	    		metadata=insert_metadata(cols,types,data); ///todo
	    		query = String.format("insert into %s values (%s);",tablename,metadata); // WHERE Login=? and Pwd=?";
	    	}
            java.sql.Connection connection=MYSQL_Connection.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            int rowsaffected = preparedStmt.executeUpdate();
//            System.out.println(rowsaffected);

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
	}

    private String[] format_cols(String[] data, String[] types) {
    	String[] s=new String[types.length];
		for (int i = 0; i < types.length; i++) {
			if (data[i].length()==0) s[i]="null";
			else if (types[i]=="s") s[i]="'"+data[i]+"'";
			else s[i]=data[i];
		}
		return s;
    }
    
	private String insert_metadata(String[] cols, String[] types, String[] data) {
		String[] s=format_cols(data, types);
		return String.join(",",s);
	}

	private String update_metadata(String[] cols, String[] types, String[] data) {
		String[] s=format_cols(data, types);
		for (int i = 0; i < s.length; i++) {
			s[i]=cols[i]+"="+s[i];
		}
		return String.join(",", s);
	}
}
