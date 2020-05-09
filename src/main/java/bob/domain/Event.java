package bob.domain;

import java.time.*;

/**
 * Kalenteritapahtumaa kuvaava luokka, aika-parametri ei pakollinen
 */
public class Event implements Comparable<Event>, CalendarItem {

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

    /**
     * Metodi palauttaa kalenteritapahtuman merkkijonoesityksenä.
     *
     * @return merkkijonoesitys
     */
    @Override
    public String toString() {
        if (this.time == null) {
            return this.description;
        } else {
            return "klo " + this.time + ": " + this.description;
        }
    }
}
