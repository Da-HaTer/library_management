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

public class MYSQL_lib {
	public static  java.sql.Connection getconnection() throws SQLException {
		java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gestion_lib2?characterEncoding=utf8","root","toor");
		return connection;
	}
	
	
	public static void delete_entite(String tablename,String[] cols, String id) {
    	try {
    	String query= String.format("delete from %s where %s=%s;",tablename,cols[0],id);
    	java.sql.Connection connection=MYSQL_lib.getconnection();
        PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
        int rowsaffected = preparedStmt.executeUpdate();
//        System.out.println(rowsaffected);
        connection.close();

    	}
    	
    	catch (SQLException e) {e.printStackTrace();}
	}
	
	
	public static ArrayList<String> fetch_entite(String tablename,String[] cols,String id) { ///data as list
	    try{ 
	        String query = String.format("select * from %s where %s=%s;",tablename,cols[0],id);
	        java.sql.Connection connection=MYSQL_lib.getconnection();
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
	
	public static String[][] get_data(String tablename,int colcount) { ///data as list
	    try{ 
	        String query = String.format("select * from %s ;",tablename);
	        java.sql.Connection connection=MYSQL_lib.getconnection();
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
	        connection.close();
	        return (String[][]) vals.toArray(new String[vals.size()][colcount]);
	    }
	    catch (Exception e) {
	    	System.out.println(e.getStackTrace());
			// TODO: handle exception
		}
		return null;
	}
	
    public static void save_entite(String tablename,String[] cols, String[] types,String[] data) { //to fix
    	
        try{
        	String metadata=update_metadata(cols,types,data); ///todo
        	String query=String.format("update %s set %s where %s=%s;",tablename,metadata,cols[0],data[0]);
	    	if (fetch_entite(tablename,cols,data[0])==null || fetch_entite(tablename,cols,data[0]).size()==0) {
	    		System.out.println("insert");
	    		metadata=insert_metadata(cols,types,data); ///todo
	    		query = String.format("insert into %s values (%s);",tablename,metadata); // WHERE Login=? and Pwd=?";
	    	}
//	    	System.out.println(query);
            java.sql.Connection connection=MYSQL_lib.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            int rowsaffected = preparedStmt.executeUpdate();
            System.out.println(rowsaffected);

            connection.close();
        }
        
        catch (SQLException e) {e.printStackTrace();}
	}

    private static String[] format_cols(String[] data, String[] types) {
    	String[] s=new String[types.length];
		for (int i = 0; i < types.length; i++) {
			if (data[i].length()==0 || data[i]=="null") s[i]="null";
			else if (types[i]=="s" || types[i]=="D") s[i]="'"+data[i]+"'"; //string or date
			else s[i]=data[i];
		}
		return s;
    }
    
	private static String insert_metadata(String[] cols, String[] types, String[] data) {
		String[] s=format_cols(data, types);
		return String.join(",",s);
	}

	private static String update_metadata(String[] cols, String[] types, String[] data) {
		String[] s=format_cols(data, types);
		for (int i = 0; i < s.length; i++) {
			s[i]=cols[i]+"="+s[i];
		}
		return String.join(",", s);
	}
	public static void main(String[] args) {
//		MYSQL_lib db=  MYSQL_lib();
		String[] colnames={"CodeAb", "NomAb", "PrenomAb", "DateNaissAb", "EmailAb", "DateAb", "DateRenouvAb", "CodeTA"};
		String[] types={"i", "s", "D", "D", "s", "D", "D", "i"}; //s:string, D:data i:integer
		String[] data={"2","ayadi","montasar","2002-4-2","test@yandex.com","2022-4-4","",""}; //s:string, D:data i:integer
//		System.out.println(db.fetch_entite("abonne",colnames, 1));
		//System.out.println(Arrays.deepToString(db.get_data("abonne", 8)));
		MYSQL_lib.save_entite("abonne", colnames, types, data);
//		db.delete_entite("abonne", colnames,"2");
	}
}
