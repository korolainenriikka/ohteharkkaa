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
        /*Date date = new Date();
        SimpleDateFormat formatterSQL = new SimpleDateFormat("yyyy-MM-dd");
        String dateSQL = formatterSQL.format(date);
                
        Reminder reminder = new Reminder(dateSQL, content);*/
        try {   
            bobDao.createReminder();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
}
