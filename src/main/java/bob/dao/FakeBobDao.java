package bob.dao;

import bob.domain.*;
import java.time.*;
import java.util.*;

/**
 * Tiedon pysyväistallennuksen valekomponentti testejä varten.
 */
public class FakeBobDao implements BobDao {

    private ArrayList<CalendarItem> reminders;
    private ArrayList<CalendarItem> events;
    private LocalTime worktime;

    public FakeBobDao() {
        this.reminders = new ArrayList<>();
        this.events = new ArrayList<>();
        this.worktime = LocalTime.parse("00:00:00");
    }

    /**
     * Metodi lisää parametrina annetun Event-olion tietokannan events-taulua
     * simuloivaan listaan.
     *
     * @param newEvent lisättävä tapahtuma
     *
     * @return true/false (onnistuiko lisäys)
     */
    @Override
    public boolean addEventToDatabase(Event newEvent) {
        if (newEvent.getDescription() == null || newEvent.getDate() == null) {
            return false;
        }
        events.add(newEvent);
        return true;
    }

    /**
     * Metori lisää parametrina annetun Reminder-olion tietokannan
     * reminders-taulua simuloivaan listaan.
     *
     * @param newReminder lisättävä muistutus
     *
     * @return true/false (onnistuiko lisäys)
     */
    @Override
    public boolean addReminderToDatabase(Reminder newReminder) {
        if (newReminder.getDescription() == null || newReminder.getDate() == null) {
            return false;
        }
        reminders.add(newReminder);
        return true;
    }

    /**
     * Metodi palauttaa päivän kalenteritapahtumat ajan mukaan järjestyksessä.
     *
     * @param today päivä, jolta tapahtumat palautetaan.
     *
     * @return lista tapahtumista
     */
    @Override
    public List<CalendarItem> getTodaysEventsSorted(LocalDate today) {
        return events;
    }

    /**
     * Metodi palauttaa päivän muistutukset.
     *
     * @param today päivä, jolta muistutukset palautetaan.
     *
     * @return lista muistutuksista
     */
    @Override
    public List<CalendarItem> getTodaysReminders(LocalDate today) {
        return reminders;
    }

    /**
     * Metodi poistaa tietokannasta kaikki kalenteritapahtumat sekä
     * muistutukset, joiden päivämäärä on ennen parametrina annettua
     * päivämäärää.
     *
     * @param today päivämäärä, jota edeltävä tieto poistetaan
     *
     * @return true/false (onnistuiko lisäys)
     */
    @Override
    public boolean removeOld(LocalDate today) {
        return true;
    }

    /**
     * Metodi päivittää worktime-muuttujaan tehdyn työn määrän.
     *
     * @param workTime työaika
     * @param date päivä, jonka työaika päivitetään
     */
    @Override
    public void updateWorkTime(LocalTime workTime, LocalDate date) {
        this.worktime = workTime;
    }

    @Override
    public LocalTime getWorkTime(LocalDate date) {
        return worktime;
    }

    /**
     * Metodi poistaa parametrina annettujen tietojen mukaiset muistutukset.
     *
     * @param text muistutuksen kuvaus
     * @param day muistutuksen päivämäärä
     */
    @Override
    public void deleteReminder(String text, LocalDate day) {
        for (CalendarItem item : reminders) {
            if (item.getDescription() == text) {
                reminders.remove(item);
                break;
            }
        }
    }

}
