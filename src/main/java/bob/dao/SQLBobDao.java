package bob.dao;

import bob.domain.*;
import java.sql.*;
import java.time.*;
import java.util.*;

/**
 * Tiedon pysyväistallennuksesta tietokantaan vastaava luokka.
 */
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
            s.execute("CREATE TABLE IF NOT EXISTS Reminders(id INTEGER PRIMARY KEY, date DATE, description TEXT);");
            s.execute("CREATE TABLE IF NOT EXISTS Events(id INTEGER PRIMARY KEY, date DATE, time TIME, description TEXT);");
            s.execute("CREATE TABLE IF NOT EXISTS Worktime(id INTEGER PRIMARY KEY, date DATE, time TIME);");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Metori lisää parametrina annetun Event-olion tietokannan events-tauluun.
     *
     * @param newEvent lisättävä tapahtuma
     *
     * @return true/false (onnistuiko lisäys)
     */
    public boolean addEventToDatabase(Event newEvent) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Events(date, time, description) VALUES (?,?,?)");
            stmt.setString(1, newEvent.getDate() + "");
            if (newEvent.getTime() == null) {
                stmt.setString(2, null);
            } else {
                stmt.setString(2, newEvent.getTime() + "");
            }
            stmt.setString(3, newEvent.getDescription());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metori lisää parametrina annetun Reminder-olion tietokannan
     * reminders-tauluun.
     *
     * @param newReminder lisättävä muistutus
     *
     * @return true/false (onnistuiko lisäys)
     */
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

    /**
     * Metodi poistaa tietokannasta kaikki kalenteritapahtumat sekä
     * muistutukset, joiden päivämäärä on ennen parametrina annettua
     * päivämäärää.
     *
     * @param today päivämäärä, jota edeltävä tieto poistetaan
     *
     * @return true/false (onnistuiko lisäys)
     */
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

    /**
     * Metodi palauttaa päivän kalenteritapahtumat ajan mukaan järjestyksessä.
     *
     * @param today päivä, jolta tapahtumat palautetaan.
     *
     * @return lista tapahtumista
     */
    @Override
    public List<CalendarItem> getTodaysEventsSorted(LocalDate today) {
        List<CalendarItem> todaysEvents = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Events WHERE date=(?) ORDER BY time;");
            stmt.setString(1, today + "");
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                if (r.getString("time") == null) {
                    todaysEvents.add(new Event(LocalDate.parse(r.getString("date")), null, r.getString("description")));
                } else {
                    todaysEvents.add(new Event(LocalDate.parse(r.getString("date")), LocalTime.parse(r.getString("time")), r.getString("description")));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return todaysEvents;
    }

    /**
     * Metodi palauttaa päivän muistutukset.
     *
     * @param today päivä, jolta muistutukset palautetaan.
     *
     * @return lista muistutuksista
     */
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

    /**
     * Metodi päivittää tietokantaan tehdyn työn määrän.
     *
     * @param workTime työaika
     * @param date päivä, jonka työaika päivitetään
     */
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

    /**
     * Metodi hakee tietokannasta tehdyn työajan. Jos aikaa ei löydy,
     * tietokantaan lisätään tyhjää työaikaa indikoiva rivi.
     *
     * @param date päivä, jonka työaika haetaan
     *
     * @return työaika LocalTime-oliona
     */
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
