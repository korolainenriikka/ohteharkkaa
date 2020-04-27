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
        } catch (SQLException e) {
            System.err.println(e);

        }
        createDatabase();
    }

    private void createDatabase() {
        try {
            Statement s = connection.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS Reminders(id INTEGER PRIMARY KEY, date DATE, description TEXT, done BOOLEAN DEFAULT 'false');");
            s.execute("CREATE TABLE IF NOT EXISTS Events(id INTEGER PRIMARY KEY, date DATE, time TIME, description TEXT);");
            s.execute("CREATE TABLE IF NOT EXISTS Worktime(id INTEGER PRIMARY KEY, date DATE, time TIME);");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public boolean addEventToDatabase(Event newEvent) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Events(date, time, description) VALUES (?,?,?)");
            stmt.setString(1, newEvent.getDate() + "");
            stmt.setString(2, newEvent.getTime() + "");
            stmt.setString(3, newEvent.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean addReminderToDatabase(Reminder newReminder) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Reminders(date, description) VALUES (?,?)");
            stmt.setString(1, newReminder.getDate() + "");
            stmt.setString(2, newReminder.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeOld(LocalDate today) {
        try {
            PreparedStatement stmt1 = connection.prepareStatement("DELETE FROM Events WHERE date < (?)");
            PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM Reminders WHERE date < (?)");
            stmt1.setString(1, today + "");
            stmt2.setString(1, today + "");
            stmt1.executeUpdate();
            stmt2.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<CalendarItem> getTodaysEventsSorted(LocalDate today) {
        List<CalendarItem> todaysEvents = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Events WHERE date=(?) ORDER BY date;");
            stmt.setString(1, today + "");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysEvents.add(new Event(LocalDate.parse(r.getString("date")), LocalTime.parse(r.getString("time")), r.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return todaysEvents;
    }

    @Override
    public List<CalendarItem> getTodaysReminders(LocalDate today) {
        List<CalendarItem> todaysReminders = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Reminders WHERE date=(?);");
            stmt.setString(1, today + "");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                todaysReminders.add(new Reminder(LocalDate.parse(r.getString("date")), r.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return todaysReminders;
    }

    public Connection getConnection() {
        return connection;
    }

    public void updateWorkTime(LocalTime workTime, LocalDate date) {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE Worktime SET time=(?) WHERE date=(?)");
            stmt.setString(1, workTime + "");
            stmt.setString(2, date + "");
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public LocalTime getWorkTime(LocalDate date) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Worktime WHERE date=(?)");
            stmt.setString(1, date + "");
            ResultSet r = stmt.executeQuery();
            if (!r.next()) {
                insertNoWorkToDb(date);
            } else {
                return LocalTime.parse(r.getString("time"));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return LocalTime.parse("00:00:00");
    }

    private void insertNoWorkToDb(LocalDate date) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Worktime(date, time) VALUES ((?),'00:00:00')");
            stmt.setString(1, date + "");
            stmt.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

}
