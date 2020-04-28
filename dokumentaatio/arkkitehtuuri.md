# Arkkitehtuurikuvaus

## Rakenne

Sovellus noudattaa kolmitasoista kerrosarkkitehtuuria, koodin pakkausrakenne on seuraava:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/arkkitehtuuri.jpg" width="900" height="1000"/> TÄHÄN SIMPPELI PAKKAUSKAAVIO

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

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/arkkitehtuuri.jpg" width="900" height="1000"/> TÄHÄN LUOKKA/PAKKAUSKAAVIO

## Tietojen pysyväistallennus

Pakkauksen _bob.dao_ luokka _SQLBobDao_ huolehtii tietojen tallentamisesta tietokantaan.

Luokka noudattaa Data Access Object-suunnittelumallia ja se on tarvittaessa mahdollista korvata uusilla toteutuksilla, jos esimerkiksi päätetään vaihtaa tallennuspaikka tiedostoon tai internetiin. Dao-luokka on toteutettu rajapinnan taakse, jolloin se voidaan tarvittaessa vaihtaa ilman muutoksia muuhun sovellukseen.

Sovelluslogiikan testauksessa hyödynnetäänkin tätä siten, että testeissä käytetään tiedostoon tallentavien DAO-olioiden sijaan keskusmuistiin tallentavia toteutuksia.

### Tietokanta

Sovellus tallettaa kalenterin ja työajan tiedot tietokantaan.

tietokantakaavio:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/arkkitehtuuri.jpg" width="900" height="1000"/> TÄHÄN TIETOKANTAKAAVIO

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka muutaman päätoiminnallisuuden osalta sekvenssikaavioina.

#### käyttäjän kirjaantuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan painiketta _loginButton_ etenee sovelluksen kontrolli seuraavasti:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-4b.png" width="750">

Painikkeen painamiseen reagoiva [tapahtumankäsittelijä](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/ui/TodoUi.java#L92) kutsuu sovelluslogiikan _appService_ metodia [login](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/TodoService.java#L73) antaen parametriksi kirjautuneen käyttäjätunnuksen. Sovelluslogiikka selvittää _userDao_:n avulla onko käyttäjätunnus olemassa. Jos on, eli kirjautuminen onnistuu, on seurauksena se että käyttöliittymä vaihtaa näkymäksi _todoScenen_, eli sovelluksen varsinaisen päänäkymän ja renderöi näkymään kirjautuneen käyttäjän todot eli tekemättömät työt.

#### uuden käyttäjän luominen

Kun uuden käyttäjän luomisnäkymässä on syötetty käyttäjätunnus joka ei ole jo käytössä sekä nimi ja klikataan painiketta _createUser_ etenee sovelluksen kontrolli seuraavasti:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-5.png" width="750">

[Tapahtumakäsittelijä](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/ui/TodoUi.java#L138) kutsuu sovelluslogiikan metodia [createUser](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/TodoService.java#L111) antaen parametriksi luotavan käyttäjän tiedot. Sovelluslogiikka selvittää _userDao_:n avulla onko käyttäjätunnus olemassa. Jos ei, eli uuden käyttäjän luominen on mahdollista, luo sovelluslogiikka _User_-olion ja tallettaa sen kutsumalla _userDao_:n metodia _create_. Tästä seurauksena on se, että käyttöliittymä vaihtaa näkymäksi _loginScenen_ eli kirjautumisnäkymän.

#### Todon luominen

Uuden todon luovan _createTodo_-painikkeen klikkaamisen jälkeen sovelluksen kontrolli eteneeseuraavasti:

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/a-6.png" width="750">

[Tapahtumakäsittelijä](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/ui/TodoUi.java#L193) kutsuu sovelluslogiikan metodia [createTodo](https://github.com/mluukkai/OtmTodoApp/blob/master/src/main/java/todoapp/domain/TodoService.java#L29) antaen parametriksi luotavan työn tiedot. Sovelluslogiikka luo uuden _Todo_-olion ja 
 tallettaa sen kutsumalla _todoDao_:n metodia _create_. Tästä seurauksena on se, että käyttöliittymä päivittää näytettävät todot kutsumalla omaa metodiaan _redrawTodolist_.

#### Muut toiminnallisuudet

Sama periaate toistoo sovelluksen kaikissa toiminnallisuuksissa, käyttöliittymän tapahtumakäsittelijä kutsuu sopivaa sovelluslogiikan metodia, sovelluslogiikka päivittää todojen tai kirjautuneen käyttäjän tilaa. Kontrollin palatessa käyttäliittymään, päivitetään tarvittaessa todojen lista sekä aktiivinen näkyvä.

## Ohjelman rakenteeseen jääneet heikkoudet

### käyttöliittymä

Graafinen käyttöliittymä on toteutettu määrittelemällä lähes koko käyttöliittymän struktuuri luokan _TodoUi_ metodissa _start_. Ainakin kaikkien sovelluksen kolmen päänäkymän rakentava koodi olisi syytä erottaa omiksi metodeikseen tai kenties luokiksi. Muuttujien nimentää olisi myös syytä parantaa. 

Käyttöliittymän rakenteen ohjelmallinen määrittely kannattaisi kenties korvata FXML-määrittelyllä, tällöin sovelluslogiikan ja käyttöliittymän tapahtumankäsittelijöiden välinen kommunikointi ei hukkuisi GUI-elementtejä rakentavan koodin sekaan.

### DAO-luokat

FileDao-toteutuksiin on jäänyt paljon toisteista koodia, molemmat mm. sisältävät hyvin samankaltaisen logiikan tiedoston lukemiseen ja tidostoon kirjoittamiseen. Tämä koodi olisi syytä eroittaa omaan luokkaansa.

DAO-toteutusten automaattiset testit tekisivät refaktoroinnin suhteellisen riskittömäksi.
