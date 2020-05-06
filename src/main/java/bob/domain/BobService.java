package bob.domain;

import bob.dao.BobDao;
import java.time.*;
import java.util.*;

/**
 * Sovelluslogiikka.
 */
public class BobService {

    private BobDao bobDao;

    public BobService(BobDao bobDao) {
        this.bobDao = bobDao;
    }

    /**
     * Metodi luo parametreistä uuden event-olion, lisää olion dao:n
     * välityksellä tietokantaan, ja palauttaa vahvistusviestin.
     *
     * @param date päivä, jolloin tapahtuma on
     * @param time tapahtuman ajankohta
     * @param description tapahtuman kuvaus
     *
     * @return vahvistusviesti
     */
    public String createNewEvent(LocalDate date, LocalTime time, String description) {
        Event newEvent = new Event(date, time, description);
        boolean added = bobDao.addEventToDatabase(newEvent);
        if (added && newEvent.getTime() != null) {
            return "uusi tapahtuma lisätty:\n" + newEvent.getDate() + "\n" + newEvent.getTime() + "\n" + newEvent.getDescription();
        } else if (added) {
            return "uusi tapahtuma lisätty:\n" + newEvent.getDate() + "\n" + newEvent.getDescription();
        } else {
            return "virhe!";
        }
    }

    /**
     * Metodi luo parametreista uuden reminder-loin, lisää olion tietokantaan
     * dao-luokan välityksllä, ja palauttaa vahvistusviestin.
     *
     * @param date muistutuksen päivämäärä
     * @param description muistutuksen kuvaus
     *
     * @return vahvistusviesti
     */
    public String createNewReminder(LocalDate date, String description) {
        Reminder newReminder = new Reminder(date, description);
        if (bobDao.addReminderToDatabase(newReminder)) {
            return "uusi muistutus lisätty:\n" + newReminder.getDate() + "\n" + newReminder.getDescription();
        } else {
            return "virhe!";
        }
    }

    /**
     * Metodi poistaa tietokannasta kaikki parametrina annettua päivämäärää
     * edeltävät kalenteritapahtumat ja muistutukset dao-luokan välityksellä.
     *
     * @param
     *
     * @return
     */
    public boolean removeOld(LocalDate date) {
        return bobDao.removeOld(date);
    }

    /**
     * Metodi palauttaa parametrina annettua luokkaa sekä päivää vastaavat
     * kalenteritapahtumat tai muistutukset niiden merkkijonoesityksenä
     * tietokannasta dao-luokan välityksellä.
     *
     * @param cls luokka, jonka tiedot haetaan
     * @param date päivä, jolta tiedot haetaan
     *
     * @return lista olioiden tostringeistä
     */
    public List<String> getDaysItemsAsString(Class<?> cls, LocalDate date) {
        List<String> itemToStrings = new ArrayList<>();
        List<CalendarItem> todaysItems = new ArrayList<>();
        if (cls == Event.class) {
            todaysItems = bobDao.getTodaysEventsSorted(date);
        } else {
            todaysItems = bobDao.getTodaysReminders(date);
        }
        for (CalendarItem i : todaysItems) {
            itemToStrings.add(i.toString());
        }
        return itemToStrings;
    }

    /**
     * Metodi luo parametreistä uuden muistutuksen, lisää päivämäärää yhdellä ja
     * lisää muistutuksen dao-luokan välityksellä tietokantaan.
     *
     * @param text muistutuksen kuvaus
     * @param date muistutuksen päivämäärä
     */
    public void moveReminderToNextDay(String text, LocalDate date) {
        bobDao.addReminderToDatabase(new Reminder(date.plusDays(1), text));
    }

    /**
     * Metodi tallentaa dao-luokan välityksellä työajan tietokantaan.
     *
     * @param time työaika
     * @param date päivä, jolloin työskennellään
     */
    public void saveWorkedTime(LocalTime time, LocalDate date) {
        bobDao.updateWorkTime(time, date);
    }

    /**
     * Metodi palauttaa tietokannasta haetun työajan kysytyltä päivältä.
     *
     * @param date päivä, jolta työaika haetaan
     *
     * @return työaika
     */
    public LocalTime getWorkTime(LocalDate date) {
        return bobDao.getWorkTime(date);
    }

    /**
     * Metodi palauttaa tietokannasta haetun työajan kysytyltä päivältä.
     *
     * @param date päivä, jolta työaika haetaan
     *
     * @return työaika
     */
    public void deleteReminderByDescription(String text) {
        bobDao.deleteReminderByDescription(text);
    }
}
