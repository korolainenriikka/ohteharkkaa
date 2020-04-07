package bob.dao;

import bob.domain.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class SQLBobDao implements BobDao {

    private Connection connection;

    public SQLBobDao(String database) {
        try {
            connection = DriverManager.getConnection(database);
            Statement s = connection.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Muistutukset(id INTEGER PRIMARY KEY, date DATE, description TEXT);");
            s.execute("CREATE TABLE IF NOT EXISTS Tapahtumat(id INTEGER PRIMARY KEY, date DATE, time TIME, description TEXT);");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public String addReminderToDatabase(Reminder newReminder) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Muistutukset(date, description) VALUES (?,?)");
            stmt.setString(1, newReminder.getDate() + "");
            stmt.setString(2, newReminder.getDescription());
            stmt.executeUpdate();
            return ("uusi muistutus lisätty:\n" + newReminder.getDate() + "\n" + newReminder.getDescription());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public List<CalendarItem> getTodaysCalendarItems(LocalDate today) {
        ArrayList<CalendarItem> todaysItems = new ArrayList<>();
        try {
            
            PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM Tapahtumat WHERE date=(?);");
            stmt2.setString(1, today + "");
            ResultSet r2 = stmt2.executeQuery();
            while (r2.next()) {
                todaysItems.add(new Event(LocalDate.parse(r2.getString("date")), LocalTime.parse(r2.getString("time")), r2.getString("description")));
            } 
            todaysItems.add(new Reminder(LocalDate.now(), "*"));
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Muistutukset WHERE date=(?);");
            stmt.setString(1, today + "");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysItems.add(new Reminder(LocalDate.parse(r.getString("date")), r.getString("description")));
            }        
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return todaysItems;
    }

    @Override
    public boolean removeOld(LocalDate today) {
        try {
            PreparedStatement stmtReminder = connection.prepareStatement("DELETE FROM Muistutukset WHERE date < (?)");
            PreparedStatement stmtEvent = connection.prepareStatement("DELETE FROM Tapahtumat WHERE date < (?)");
            stmtReminder.setString(1, today + "");
            stmtEvent.setString(1, today + "");
            stmtReminder.executeUpdate();
            stmtEvent.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public String addEventToDatabase(Event newEvent) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Tapahtumat(date, time, description) VALUES (?,?,?)");
            stmt.setString(1, newEvent.getDate() + "");
            stmt.setString(2, newEvent.getTime() + "");
            stmt.setString(3, newEvent.getDescription());
            stmt.executeUpdate();
            return ("uusi tapahtuma lisätty:\n" + newEvent.getDate() + "\n" + newEvent.getTime() + "\n" + newEvent.getDescription());
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

}
