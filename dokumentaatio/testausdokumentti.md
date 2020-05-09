# Testausdokumentti
Ohjelmaa on testattu sekä integraatiotestein JUnit-testeillä, että järjestelmätasolla jo usean kuukauden ajan käyttämällä ohjelmaa sen aiottuun tarkoitukseen.

## Yksikkö- ja integraatiotestaus

Ohjelman sovelluslogiikkaan sekä tiedon pysyväistallennuksesta vastaavaan luokkaan on toteutettu integraatiotestit. Nämä testit kattavat samalla kaikki domain-pakkauksen luokat, joten erillistä yksikkötestausta ei ole ollut tarpeen toteuttaa.

### Sovelluslogiikka

Sovelluslogiikan _BobService_ luokkaa on testattu keskusmuistiin tallentavan valekomponentin avustuksella niin, että kaikki sovelluslogiikan oletetut vahvistusviestit, sekä muut toiminnallisuudet ovat katettuna. Integraatiotestit on pääasiassa toteutettu niin, että sovelluslogiikan välityksellä tallennetaan joko _Event_ tai _Reminder_-olio, ja haetaan sama olio dao:sta toisella metodilla.

### DAO

Ohjelman DAO-luokille on toteutettu testit hyödyntäen erillistä testitietokantaa, joka aina luodaan ja poistetaan testien ajon yhteydessä.

### Testikattavuus

Sovelluksen dao- ja domain-pakkausten keskimääräinen rivi- sekä haaraumakattavuus on 91%.

(kuvassa myös varsin kiinnostava github-bugi, klikkaamalla linkin aukeaa oikea, päivitetty kuva)

<img src="https://github.com/korolainenriikka/BobThePersonalAssistant-ohte2020/blob/master/dokumentaatio/kuvat/jacoco_final.png"/>

Testaamatta jäivät ainoastaan SQLBobDao-luokan catch-lohkot.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus suoritettiin manuaalisesti. 

### Asennus

Sovellus asennettiin asennusohjeiden mukaisesti sekä loppupalautus-releasen, että repositorion kloonauksen avulla.

### Toiminnallisuudet

Sovelluksen toiminnallisuudet testattiin läpi molemmilla asennustavoilla niin kuin ne on määrittelydokumentissa listattu. Testattiin myös sovelluksen toimintaa erilaisissa virhesyötteiden ja ohjelman useilla komentoriveillä suoritetun samanaikaisen ajon tilanteissa. 

## Sovellukseen jääneet laatuongelmat

* Sovellus ei anna järkevää virheilmoitusta, mikäli käyttäjä avaa ohjelman useaan kertaan samanaikaisesti ja suorittaa tietokantaa käyttäviä operaatioita, tai muokkaa manuaalisesti ohjelman tietokantaa ja käyttää ohjelmaa samanaikaisesti.
