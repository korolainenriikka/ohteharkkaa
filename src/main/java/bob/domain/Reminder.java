package bob.domain;

import java.time.LocalDate;

public class Reminder implements CalendarItem {

    private LocalDate date;
    private String description;
    private Boolean done;

    public Reminder(LocalDate date, String description) {
        this.date = date;
        this.description = description;
        this.done = false;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
