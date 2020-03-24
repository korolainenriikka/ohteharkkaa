package bob.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BobDao {

    // connectionin ja preparedstatementin luomiseen oma metodi tänne? yksinkeraistais
    public static void createReminder(String date, String description) {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bobData.db");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Muistutukset(date, description) VALUES (?,?)");
            stmt.setString(1, date);
            stmt.setString(2, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<String> getTodaysReminders() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        System.out.println(formattedDate);

        ArrayList<String> today = new ArrayList<>();

        Connection connection = null;

        try {
            //daten muunnos??
            connection = DriverManager.getConnection("jdbc:sqlite:bobData.db");
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
