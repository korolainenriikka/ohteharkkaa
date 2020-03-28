# Ohjelmistotekniikka, harjoitustyö: Bob the personal assistant

"*Rutiinit roboteille ja analyysit ammattilaisille*" -*E.K. 20.3.2020*.

Bob the personal assistant on henkilökohtainen sihteeri, jonka ydin yllä: mitä enemmän on ulkoistettu, sitä enemmän on ulkoistettu, ja sitä enemmän on aikaa mukavammille asioille. Kyseessä on käyttöön tehtävä, muutenkin varsin yleistarpeellinen ohjelmisto.

Lisäksi, on tuskin kiellettävissä, että elämänhallinnan ulkoistaminen itsetehdylle botille on melko tyylikästä.

*Due to the current situation with Covid-19*:

Matkasuunnitelmien peruuntumisen, runsaahkon ajan ja yleisen botin näpyttelyn mukavuuden vuoksi työstä tulee todennäköisesti huomattavasti vaatimuksia laajempi. Mikäli ohjaaja tai katselmoija huomaa koodissa jotain korjattavaa, joka ei varsinaisiin tavoitteisiin sisälly, korjausehdotukset otetaan ilolla vastaan.

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/resources/images/primarySceneBottom.jpg" width="300" height="300"/>

## komentorivitoiminnot

### ohjelman suoritus

ohjelma suoritetaan hakemiston juuressa komennolla 

mvn compile exec:java -Dexec.mainClass=bob.Main

### testaus

Testeistä on toteutettuna yksikkötestit muutamia (ks. alla) lukuun ottamatta kaikille muille paitsi käyttöliittymästä vastaaville luokille.

Testit lisäävät vielä tällä hetkellä tietoa tietokantaan, eikä poistotoiminnallisuuksia niin testien kuin itse ohjelman tarpeeseen ole vielä toteutettu.

## dokumentaatio

[määrittelydokumentti](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/vaatimusmaarittely.md)

[työaikakirjanpito](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/työaikakirjanpito.md)


