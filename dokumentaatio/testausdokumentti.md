# Testausdokumentti
(outline mallina todoapp testausdoc)
Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

* bobservicelle tarvittavat integraatiotestit
* yksikkötestit sinne, minne integraatio ei ulotu!

### Sovelluslogiikka

...

### DAO

Ohjelman DAO-luokille on toteutettu testit hyödyntäen erillistä testitietokantaa, joka aina luodaan ja poistetaan testien ajon yhteydessä.

### Testikattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on ... ja haarautumakattavuus ...
* screenshot testikattavuusraportista!!
* testaamatta jäivät... daon catchit!

## Järjestelmätestaus

Sovelluksen järjestelmätestaus suoritettiin manuaalisesti.

### Asennus

### Toiminnallisuudet

((tänne et sovelluksen koodissa vaihdeltiin päivää ja aikaa ja tarkasteltiin juttui))

(lataa jar ja release ja testaa kaikki mahollinen sitte tänne mitä teit!)

## Sovellukseen jääneet laatuongelmat

Sovellus ei tällä hetkellä ota lainkaan kantaa muistutusten suorittamiseen muutoin kuin erillisellä kirjaamisella lopeta päivä -sivulla.
Mikäli siis esim. päivä lopetetaan useasti, sovellus duplikoi olemassa olevia muistutuksia.

Lisäksi sovellus ei anna järkevää virheilmoitusta, mikäli ...(avaa dao system err)

