package bob.domain;

import java.sql.Time;
import java.time.LocalDate;

public class Event {
    private LocalDate date;
    private Time time;
    private String description;

    public Event(LocalDate date, Time time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }
    
    
}
