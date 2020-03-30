package bob.domain;

import java.time.LocalDate;

public class Reminder{
    private LocalDate date;
    private String description;

    public Reminder(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
     
}
