# Vaatimusmäärittely

## Sovelluksen tarkoitus

Bob the personal assistant on henkilökohtainen sihteeri, jonka tarkoituksena on ulkoistaa mahdollisimman paljon arkisten asioiden muistamista.

## Käyttäjät

Sovellus on täysin henkilökohtainen, omiin tarpeisiin kustomoitu ja yhtä lailla omien tarpeiden mukaan elävä ja kehittyvä.

## Toteutetut toiminnallisuudet

### Muistutukset

Muistutusten lisäämisen tarkoitus on esimerkiksi eliminoida kirjastosakot lisäämällä laina-ajat tietokantaan, kirjata ylös koulutöiden deadlineja, ym. Toiminnallisuuteen kuuluu muistutuksen luominen, ja luodun muistutuksen näyttäminen määritettynä päivänä. Vanhentuneet muistutukset poistuvat tietokannasta automaattisesti.

Ohjelman lopeta päivä -toiminnossa muistutukset voidaan merkata tehdyksi, ja tallennettaessa tehdyt poistuvat. Tekemättömät siirtyvät seuraavana päivänä sovellusta avattaessa ko. päivälle, ja samalla lykätty muistutus merkataan huutomerkillä, joita kertyy tarvittaessa muistutukseen myös useampia lykkäyspäivien määrän mukaisesti.

### Kalenteritapahtumat

Ohjelmaan voidaan lisätä kalenteritapahtumia, joilla on päivämäärä, sekä aika. Nämä tapahtumat näytetään aloitussivulla aikajärjestyksessä kyseisenä päivänä.

### Vappufiilikset

Vuoden kohokohdan eli vappupäivän lähentyessä myös bobi sai juhlasta osansa. Ensimmäisen näkymän vappufiilikset-osassa on kutakin vappua edeltävää ajanjaksoa sopivasti kuvaava fiilistelykuva.

Huonon huumorintajun, sekä tiettyjen ideoiden ohittamattomuuden vuoksi vappufiiliksiin vaikuttaa olennaisesti myös boolean-tyyppinen pandemia-muuttuja.

### Työskentelytila

Työskentele-välilehden alla on työaikaa seuraava ajastin, sekä mahdollisuus soittaa [Harry Potter -taustaääniä](https://www.ambient-mixer.com/). Työaika on aina päiväkohtainen, ja ajastin nollaantuu päivän vaihtuessa. Tehty työaika päivittyy tietokantaan aina ohjelman sulkemisen yhteydessä, ja lopeta päivä-sivulla näkyy päivän aikana tehty työmäärä.

## Jatkokehitysideoita

### Ohjelman väärään/epätavalliseen käyttöön varautuminen

* primary scenestä skrollattava kun sivulla on paljon sisältöä

* pitkien muistutus- ja tapahtumatekstien tapauksessa Label pitäisi saada usealle riville

### Parannuksia olemassa oleviin toiminnallisuuksiin
 
 * työaika-ajastin tallentaa jonkinlaiseen havainnollistavaan muotoon dataa työajoista (esim. kirjoittaa työaikakirjanpito-markdowneja)
 
 * työaikaa ajastaessa voitaisiin jotenkin merkata, mitä ko. ajalla on tehty
 
 * kalenteria (muistutukset ja tapahtumat) voi jälkikäteen tarkastella ja muokata

### Muita toiminnallisuuksia (perusversion laajennukset)

 * kotitöiden tekemistä aikatauluttava toiminnallisuus (aloitettu, tallessa gitin ulkopuolella)
 
 * jonkinlaisten muistilistojen ylläpito, esim. hyviä kirjoja, pakkauslistaa, ym.

### Suuruudenhulluja toiminnallisuuksia

eli toiminnallisuuksia, joita sisältävän bottisihteerin omistamisessa olisi jo *suurmiehen meininkiä*

* Automaattinen kauppalistantekijä
