package bob.domain;

public class Reminder{
    private String date;
    private String description;

    public Reminder(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    
      
}
