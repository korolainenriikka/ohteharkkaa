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

Sovelluksen loogisen datamallin muodostavat luokat [Reminder](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/domain/Reminder.java) ja [Event](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/domain/Event.java).

Toiminnallisista kokonaisuuksista vastaa luokan [BobService](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/java/bob/domain/BobService.java) ainoa olio. Luokka tarjoaa kaikille käyttäliittymän toiminnoille oman metodin.

_BobService_ pääsee käsiksi käyttäjiin ja todoihin tietojen tallennuksesta vastaavan pakkauksessa _bobapp.dao_ sijaitsevan rajapinnan _BobDao_ välityksellä. 

TodoServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/pakkauksetJaLuokat.jpg" width="600" height="800"/>

## Tietojen pysyväistallennus

Pakkauksen _bob.dao_ luokka _SQLBobDao_ huolehtii tietojen tallentamisesta tietokantaan.

Luokka noudattaa Data Access Object-suunnittelumallia ja se on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos esimerkiksi päätetään vaihtaa tallennuspaikka tiedostoon tai internetiin. Dao-luokka on toteutettu rajapinnan taakse, jolloin se voidaan tarvittaessa vaihtaa ilman muutoksia muuhun sovellukseen.

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.

### Tietokanta

Sovellus tallettaa kalenterin ja työajan tiedot tietokantaan.

tietokantakaavio:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/dbdiagram.jpg" width="800" height="800"/>

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaavioina.

#### uuden muistutuksen lisääminen

Kun sovellukseen lisätään uusi muistutus käyttöliittymän lisää-painikkeesta, kutsutaan ui:n metodia createNewReminder(). Tämän jälkeen ui:n newReminderSceneController kutsuu sovelluslogiikasta vastaavaa BobService-luokkaa, ja sen metodia createNewReminder(). BobService puolestaan kutsuu bobDao-pakkauksessa olevaa SQLBobDao-luokkaa BobDao-rajapinnan välityksellä. Muistutus lisätään tietokantaan, ja dao-luokka palauttaa vahvistuksena arvon true. Tämän seurauksena BobService palauttaa merkkijonomuotoisen vahvistusviestin, joka lisätään UI:ssa sovelluksen senhetkiseen näkymään, ja edelleen käyttäjälle.

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/uusi_muistutus_sekvenssi.jpg" width="600" height="800"/>
