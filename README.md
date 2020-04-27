# Ohjelmistotekniikka, harjoitustyö: Bob the personal assistant

"*Rutiinit roboteille ja analyysit ammattilaisille*" -*E.K. 20.3.2020*.

Bob the personal assistant on henkilökohtainen sihteeri, jonka ydin yllä: mitä enemmän on ulkoistettu, sitä enemmän on ulkoistettu, ja sitä enemmän on aikaa mukavammille asioille. Kyseessä on käyttöön tehtävä, yleistarpeellinen ohjelmisto.

*Due to the current situation with Covid-19*:

Matkasuunnitelmien peruuntumisen, runsaahkon ajan ja yleisen botin näpyttelyn mukavuuden vuoksi työstä tulee todennäköisesti huomattavasti vaatimuksia laajempi. Mikäli ohjaaja tai katselmoija huomaa koodissa jotain korjattavaa tai hoksaa hauskoja kehitysidoita, jotka eivät välttämättä kurssin puitteisiin sisälly, ehdotuksia otetaan ilolla vastaan.

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/src/main/resources/images/primarySceneBottom.jpg" width="300" height="300"/>

## releaset

[viikko 5](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/releases)

## komentorivitoiminnot

### ohjelman suoritus

ohjelma suoritetaan hakemiston juuressa komennolla 

```
mvn compile exec:java -Dexec.mainClass=bob.Main
```
## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Testikattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston, joka ajetaan komennolla 

```
java -jar target/Bob-0.000-SNAPSHOT.jar
```
(huom. vielä tuntemattomasta syystä ohjelma toimii ainoastaan näin, siirtymällä hakemistoon target ja ajamalla jar siellä osa kuvatiedostoista puuttuu. (ohjaaja/katselmoija jos hoksaat mikä on vikana apu kelpaa!))

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Virheilmoitukset löytyvät selaimella avattavasta tiedostosta _target/site/checkstyle.html_

## Dokumentaatio

[määrittelydokumentti](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/vaatimusmaarittely.md)

[arkkitehtuurikuvaus](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/arkkitehtuuri.md)

[työaikakirjanpito](https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/työaikakirjanpito.md)


