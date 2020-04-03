package bob.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements Comparable<Event>{
    private LocalDate date;
    private LocalTime time;
    private String description;

    public Event(LocalDate date, LocalTime time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Event otherEvent) {
        return this.time.compareTo(otherEvent.time);
    }
    
    @Override
    public String toString(){
        return "klo " + this.time + ": " + this.description;
    }
    
    
}
