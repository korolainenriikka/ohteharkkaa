package bob.domain;

import bob.dao.BobDao;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BobService {
    
    private BobDao bobDao;

    public BobService(BobDao bobdao) {
        this.bobDao = bobDao;
    }
    
    //uuden muistutuksen lisääminen
    public boolean createReminder(String content) {
        try {   
            //pääsee atm tänne muttei eteenpäi
            bobDao.createReminder(content);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
}
