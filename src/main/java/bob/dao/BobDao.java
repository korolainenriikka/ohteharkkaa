package bob.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

public class BobDao {
    
    public static void testmethod() throws ClassNotFoundException{
        //dateUI = käyttismuoto, dateSQL = tietokantamuoto
        /*hox ui:n pitää tietää päivä ei tään
        Date date = new Date();
        SimpleDateFormat formatterUI = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat formatterSQL = new SimpleDateFormat("yyyy-MM-dd");
        String dateUI = formatterUI.format(date);
        String dateSQL = formatterSQL.format(date);*/

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BobDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bobData.db");
//            Statement s = connection.createStatement();
            //s.execute("tässä voi suorittaa tietokantakomentoja, koklaa kans preparedstatementtia!");
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Muistutukset(date, kuvaus) VALUES (?,?)" );
            stmt.setString(1, "13-03-2020");
            stmt.setString(2, "osta margaa!");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } 
         
    }

    
}
