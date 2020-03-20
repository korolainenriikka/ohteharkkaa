package bob.dao;

import java.sql.*;

public class BobDao {
    
    public static String testmethod(String date, String description){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bobData.db");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Muistutukset(date, description) VALUES (?,?)" );
            stmt.setString(1, date);
            stmt.setString(2, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
        return "moi";
         
    }

    public static void createReminder(String date, String description){
        testmethod(date, description);
    }

    
}
