package bob.dao;

import bob.domain.Reminder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLBobDao implements BobDao{
    
    private Connection connection;
    
    public SQLBobDao(String database) {
        
        
        try{
            connection = DriverManager.getConnection(database);
            Statement s = connection.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Muistutukset(id INTEGER PRIMARY KEY, date DATE, description TEXT);");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
    }
    
    public String addReminderToDatabase(Reminder newReminder) {    
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Muistutukset(date, description) VALUES (?,?)");
            stmt.setString(1, newReminder.getDate());
            stmt.setString(2, newReminder.getDescription());
            stmt.executeUpdate();
            return("uusi muistutus lisätty:\n"+ newReminder.getDate() +"\n" + newReminder.getDescription());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<String> getTodaysReminders(String today) {
        ArrayList<String> todaysReminders = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT description FROM Muistutukset WHERE date=(?);");
            stmt.setString(1, today);
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysReminders.add(r.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return todaysReminders;
    }

}
