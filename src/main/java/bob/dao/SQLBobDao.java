package bob.dao;

import bob.domain.Event;
import bob.domain.Reminder;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLBobDao implements BobDao{
    
    private Connection connection;
    
    public SQLBobDao(String database) {
        
        try{
            connection = DriverManager.getConnection(database);
            Statement s = connection.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Muistutukset(id INTEGER PRIMARY KEY, date DATE, description TEXT);");
            s.execute("CREATE TABLE IF NOT EXISTS Tapahtumat(id INTEGER PRIMARY KEY, date DATE, time TIME, description TEXT);");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        
    }
    
    public String addReminderToDatabase(Reminder newReminder) {    
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Muistutukset(date, description) VALUES (?,?)");
            stmt.setString(1, newReminder.getDate()+"");
            stmt.setString(2, newReminder.getDescription());
            stmt.executeUpdate();
            return("uusi muistutus lisätty:\n"+ newReminder.getDate() +"\n" + newReminder.getDescription());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<String> getTodaysReminders(LocalDate today) {
        ArrayList<String> todaysReminders = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT description FROM Muistutukset WHERE date=(?);");
            stmt.setString(1, today+"");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysReminders.add(r.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return todaysReminders;
    }

    @Override
    public void removeOldReminders(LocalDate today) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Muistutukset WHERE date <= (?)");
            stmt.setString(1, today+"");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String addEventToDatabase(Event newEvent) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Tapahtumat(date, time, description) VALUES (?,?,?)");
            stmt.setString(1, newEvent.getDate()+"");
            stmt.setString(2, newEvent.getTime()+"");
            stmt.setString(2, newEvent.getDescription());
            stmt.executeUpdate();
            return("uusi tapahtuma lisätty:\n"+ newEvent.getDate() +"\n" + newEvent.getDescription());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
    
    public List<String> getTodaysEvents(LocalDate today) {
        ArrayList<String> todaysEvents = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT time, description FROM Tapahtumat WHERE date=(?);");
            stmt.setString(1, today+"");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysEvents.add("klo" + r.getString("time") + ": " + r.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return todaysEvents;
    }

}
