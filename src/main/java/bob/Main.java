/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bob;
        
import bob.ui.BobUi;
import bob.dao.BobDao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riikoro
 */
public class Main {
    public static void main(String[] args) {
        try {
            //BobUi.main(args);
            BobDao.testmethod();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
   