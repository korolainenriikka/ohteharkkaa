package bob.domain;

import java.time.LocalDate;

/**
 * Muistutusta kuvaava luokka.
 */
public class Reminder implements CalendarItem {

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

    /**
     * Metodi palauttaa merkkijonoesityksenä muistutuksen kuvauksen.
     *
     * @return merkkijonoesitys
     */
    @Override
    public String toString() {
        return this.description;
    }
}
