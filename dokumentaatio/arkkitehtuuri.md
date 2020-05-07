# Arkkitehtuurikuvaus

## Rakenne

Sovellus noudattaa kolmitasoista kerrosarkkitehtuuria, koodin pakkausrakenne on seuraava:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/pakkaukset.jpg" width="600" height="800"/>

Pakkaus _bob.ui_ sisältää käyttöliittymäluokan sekä FXML-kontrollerit, _bob.domain_ sovelluslogiikan ja _bob.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää päänäkymän sekä neljä päävalikosta löytyvää näkymää:
- uuden muistutuksen lisääminen
- uuden tapahtuman lisääminen
- työskentelytila
- päivän lopetusnäkymä

Näkymät on toteutettu erillisinä [fxml-tiedostoina](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/tree/master/src/main/resources/fxml), joita kutakin kontolloi [sceneController-rajapinnan](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/ui/SceneController.java) toteuttava luokka. Varsinainen JavaFX-käyttöliittymä kommunikoi näkymien kanssa ja lisää sovelluksen [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html) aina pyydetyn näkymän.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, se ainoastaan kutsuu sopivin parametrein sovelluslogiikan toteuttavan olion _bobServicen_ metodeja.

Sovelluksen tilan, siis päänäkymän, työskentelytilan tai lopetusnäkymän tietojen muuttuessa muutokset lisätään käyttöliittymään kutsumalla ko. sceneController-luokan setSceneContent()-metodia, joka tyhjentää näkymän, ja lisää näkymään päivitetyt tiedot ennen varsinasta näkymän vaihtoa.

## Sovelluslogiikka

Sovelluksen datamallin muodostavat luokat [Reminder](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/domain/Reminder.java) sekä [Event](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/domain/Event.java). Lisäksi sovellus käsittelee työaikadataa, mutta koska tähän sisältyy ainoastaan yksi muuttuja, työajan seuranta on toteutettu [BobUi](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/ui/BobUi.java)-luokan oliomuuttujana.

Varsinaisesta sovelluslogiikasta vastaa luokan [BobService](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/domain/BobService.java) ainoa olio. Luokka tarjoaa metodit käyttöliittymän toiminnallisuuksille ja vastaa käyttöliittymän ja pysyväistallennuksesta vastaavien luokkien välisestä kommunikaatiosta.

_BobService_ pääsee käsiksi tallennettuun tietoon tallennuksesta vastaavan pakkauksessa _bob.dao_ sijaitsevan rajapinnan _BobDao_ välityksellä. 

BobServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/pakkauksetJaLuokat.jpg" width="600" height="800"/>

## Tietojen pysyväistallennus

Pakkauksen _bob.dao_ luokka _SQLBobDao_ huolehtii tietojen tallentamisesta tietokantaan.

Luokka noudattaa Data Access Object-suunnittelumallia ja se on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos esimerkiksi päätetään vaihtaa tallennuspaikka tiedostoon tai internetiin. Dao-luokka on toteutettu rajapinnan taakse, jolloin se voidaan tarvittaessa vaihtaa ilman muutoksia muuhun sovellukseen.

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.

### Tietokanta

Sovellus tallettaa kalenterin ja työajan tiedot tietokantaan.

tietokantakaavio:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/dbdiagram.jpg" width="900" height="700"/>

### Päätoiminnallisuudet

#### Uuden muistutuksen lisääminen

Kun sovellukseen lisätään uusi muistutus käyttöliittymän lisää-painikkeesta, kutsutaan ui:n metodia _createNewReminder_. Tämän jälkeen ui:n _newReminderSceneController_ kutsuu sovelluslogiikasta vastaavaa BobService-luokkaa, ja sen metodia _createNewReminder_. _BobService_ puolestaan kutsuu _SQLBobDao_-luokkaa _BobDao_-rajapinnan välityksellä. Muistutus lisätään tietokantaan, ja dao-luokka palauttaa vahvistuksena arvon true. Tämän seurauksena _BobService_ palauttaa merkkijonomuotoisen vahvistusviestin, joka lisätään UI:ssa sovelluksen senhetkiseen näkymään, ja edelleen käyttäjälle.

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/uusi_muistutus_sekvenssi.jpg" width="1000" height="400"/>

#### Uuden kalenteritapahtuman lisääminen

Käyttäjän painaessa kalenteritapahtuman lisäämisnäkymän lisää-painiketta, kutsutaan ui:n _newEventSceneController_:n metodia _handleNewEvent_. Metodi kutsuu _BobService_:n metodia _createNewEvent_, joka luo parametreista uuden _Event_-olion. Tämä olio annetaan parametrina _BobDao_:n metodille _addEventToDatabase_. Tapahtuma lisätään tietokantaan _BobDao_-rajapinnan implementoivassa _SQLBobDao_:ssa, ja onnistuneen lisäyksen vahvistuksena palautetaan _true_. Tätä vastaavasti _BobService_ palauttaa käyttöliittymäluokalle vahvistusviestin, joka lisätään näkymään.

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/uusi_tapahtuma_sekvenssi.jpg" width="1000" height="400"/>

#### Tehtävien tehdyksi kirjaaminen ja päivän lopettaminen

Käyttäjän valittua päivän aikana suoritetut tehtävät ja painettua tallenna ja sulje -painiketta, ui:n luokan _EndDaySceneController_ metodi _saveSelections_ kutsuu jokaista tehtyä tehtävää (muistutusta) kohden _BobService_:n metodia _deleteReminder_. Tämä metodi kutsuu _dao_-luokan samannimistä metodia. Poistojen jälkeen _ui_-luokka kutsuu metodia _stop_, joka työajan tallennettuaan sulkee ohjelman.

Sekvenssikaaviossa yksinkertaistuksena vain yhden muistutuksen poisto.


<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/lopeta_paiva_sekvenssi.jpg" width="1000" height="400"/>
