package user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.*;
import lib.MYSQL_lib;


public class Utilisateur {
    public static int getUserFromDB(String login, String motDePasse) { //fetch user
        try{ 
            String query = "SELECT * FROM bibliothecaire WHERE Login=? and password=?";
            java.sql.Connection connection=MYSQL_lib.getconnection();
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
    			preparedStmt.setString(1, login);
    			preparedStmt.setString(2, motDePasse);
            ResultSet resultSet = preparedStmt.executeQuery();
            int i=-1;
            while (resultSet.next()) {
    				i= resultSet.getInt(6);
            }
            connection.close();
            return i;
        }
        
        catch (SQLException e) {e.printStackTrace();}
        return -1;
    }
}
