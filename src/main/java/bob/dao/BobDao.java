package bob.dao;

import bob.domain.Reminder;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BobDao {
    
    private Connection connection;
    
    public BobDao() {
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:bobData.db");
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

    public List<String> getTodaysReminders() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);

        ArrayList<String> today = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT description FROM Muistutukset WHERE date=(?);");
            stmt.setString(1, formattedDate);
            ResultSet r = stmt.executeQuery();
            while (r.next()) {
                today.add(r.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return today;
    }

}

// date-syntaksit:
//dateUI = käyttismuoto, dateSQL = tietokantamuoto
/*hox ui:n pitää tietää päivä ei tään
        Date date = new Date();
        SimpleDateFormat formatterUI = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatterSQL = new SimpleDateFormat("yyyy-MM-dd");
        String dateUI = formatterUI.format(date);
        String dateSQL = formatterSQL.format(date);*/
