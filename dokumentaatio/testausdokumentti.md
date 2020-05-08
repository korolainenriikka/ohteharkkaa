# Testausdokumentti
Ohjelmaa on testattu sekä integraatiotestein JUnit-testeillä, että järjestelmätasolla jo usean kuukauden ajan käyttämällä ohjelmaa sen aiottuun tarkoitukseen.

## Yksikkö- ja integraatiotestaus

Ohjelman sovelluslogiikkaan sekä tiedon pysyväistallennuksesta vastaavaan luokkaan on toteutettu integraatiotestit. Nämä testit kattavat samalla kaikki domain-pakkauksen luokat, joten erillistä yksikkötestausta ei ole ollut tarpeen toteuttaa.

### Sovelluslogiikka

Sovelluslogiikan _BobService_ luokkaa on testattu keskusmuistiin tallentavan valekomponentin avustuksella niin, että kaikki sovelluslogiikan oletetut vahvistusviestit, sekä muut toiminnallisuudet ovat katettuna. Integraatiotestit on pääasiassa toteutettu niin, että sovelluslogiikan välityksellä tallennetaan joko _Event_ tai _Reminder_-olio, ja haetaan sama olio dao:sta toisella metodilla.

### DAO

Ohjelman DAO-luokille on toteutettu testit hyödyntäen erillistä testitietokantaa, joka aina luodaan ja poistetaan testien ajon yhteydessä.

### Testikattavuus

Sovelluksen dao- ja domain-pakkausten keskimääräinen rivikattavuus on 91% ja haarautumakattavuus 92%.

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/jacoco_final.png" height=150/>

Testaamatta jäivät ainoastaan SQLBobDao-luokan catch-lohkot.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus suoritettiin manuaalisesti. TÄYTÄ TÄÄ KU KÄYT VIKAA KERTAA SETIT LÄPI!

### Asennus

### Toiminnallisuudet

((tänne et sovelluksen koodissa vaihdeltiin päivää ja aikaa ja tarkasteltiin juttui))

(lataa jar ja release ja testaa kaikki mahollinen sitte tänne mitä teit!)

## Sovellukseen jääneet laatuongelmat

Lisäksi sovellus ei anna järkevää virheilmoitusta, mikäli ...(avaa dao system err)

