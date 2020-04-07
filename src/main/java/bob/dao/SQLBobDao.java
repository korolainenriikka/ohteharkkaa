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

    public List<Reminder> getTodaysReminders(LocalDate today) {
        ArrayList<Reminder> todaysReminders = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Muistutukset WHERE date=(?);");
            stmt.setString(1, today + "");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysReminders.add(new Reminder(LocalDate.parse(r.getString("date")), r.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return todaysReminders;
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

    public List<Event> getTodaysEvents(LocalDate today) {
        ArrayList<Event> todaysEvents = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tapahtumat WHERE date=(?);");
            stmt.setString(1, today + "");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysEvents.add(new Event(LocalDate.parse(r.getString("date")), LocalTime.parse(r.getString("time")), r.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return todaysEvents;
    }

    public Connection getConnection() {
        return connection;
    }

}
