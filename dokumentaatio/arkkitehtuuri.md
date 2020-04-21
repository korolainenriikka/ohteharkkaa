# arkkitehtuuri

Sovellus noudattaa kerrosarkkitehtuuria. Kuvan ui-pakkaus havainnollistaa dao- ja domain-pakkauksen ulkopuolisia luokkia; fxml:n käytön vuoksi luokkien on oltava juuripakkauksessa.

pakkaus- ja luokkakaavio:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/arkkitehtuuri.jpg" width="900" height="1000"/>

sekvenssikaavio uuden muistutuksen lisäämisestä:

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/remindersekvenssi.jpg" width="900" height="1000"/>


Käyttäjän painaessa muistutuksenlisäysnäkymässä lisää-painiketta, painikkeeseen lisätty tapahtumankäsittelijä kutsuu bobServicen metodia CreateNewReminder. BobService luo Reminder-olion, ja antaa sen parametrinä BobDao:n metodille createNewReminder, joka puolestaan lisää muistutuksen tiedot tietokantaan. Lisäyksen onnistuttua metodi palauttaa true. Jos (ja kun) paluuarvo on true, bobService palauttaa metodistaan vahvistusviestin ("uusi muistutus lisätty ..."), jonka UI lisää muistutustenlisäysnäkymään lisää-napin alle. 