# Vaatimusmäärittely

## Sovelluksen tarkoitus

Bob the personal assistant on henkilökohtainen sihteeri, jonka tarkoituksena on ulkoistaa mahdollisimman paljon arkisten asioiden muistamista ihan arkielämän helpotukseksi.

## Käyttäjät

Sovellus on täysin henkilökohtainen, omiin tarpeisiin kustomoitu ja yhtä lailla omien tarpeiden mukaan elävä ja kehittyvä.

## Toiminnallisuudet

Ohjelman ensimmäinen toiminnallisuus on muistutusten lisääminen. Tarkoitus on esimerkiksi eliminoida kirjastosakot lisäämällä laina-ajat tietokantaan, kirjata ylös koulutöiden deadlineja, ym. Toiminnallisuuteen kuuluu muisututuksen luominen, ja luodun muistutuksen näyttäminen määritettynä päivänä

### Seuraavat muutokset

Seuraavaksi ohjelmaan on tarkoitus lisätä uusi toiminnallisuus, sekä parantaa olemassaolevaa:

Muistutuksiin lisätään toiminnallisuus, joka poistaa automaattiseseti vanhentuneet muistutukset. Koska käytössä on huomattu, että usein muistutus vaatii jonkinlaisia toimenpiteitä, muistutus muutetaan todo-tyyppiseksi, siis sille lisätään boolean-muutuuja. Tekemättä jääneiden muistutusten on tarkoitus siirtyä automaattisesti seuraavalle päivälle. Muistutuksen lisäämisen näkymää parannetaan muuttamalla päivämäärävalikko kalenterityyppiseksi, jolloin esim. viikonpäivän näkee selkeämmin, ja muutetaan tietokantaan lisäyksen vahvistusviesti sellaiseksi, ettei se näy enää kun sivulle palataan myöhemmin.

Uutena toiminnallisuutena lisätään kalenteritapahtumat, jotka ovat muuten hyvin muistutusten tyyppisiä, mutta niillä ei ole boolean-muuttujaa, ja päivän lisäksi voidaan lisätä aika. Nämä näytetään etusivulla  muistutusten tyylisesti.

## Jatkokehitysideoita

### Muistutustoiminnallisuus
 
 * käytettävyyttä lisäisi, kun lisää muistutus -sivun kentissä olisi automaattisesti lisäyspäivä.

 * tyylikkäämpää olisi vahvistusviestin näyttäminen jonkinlaisena ponnahdusikkunana.

 * tietokanta ei pysy tällä järjestelmällä kovin siistinä; vanhat muistutukset tulisi poistaa. Ensin olisi kuitenkin tarkistettava, että ko. asia on hoidossa (päivän vaihtaminen?)

 * muistutusten lisääminen viikottain, päivittäin ym.

 ### Muita toiminnallisuuksia (perusversion laajennukset)

 * kalenteritapahtumat; tähän palaamme kun niitä taas kalenterissa on

 * treenipäiväkirja (linkki tietokantasovelluksen sivulle)

 * iltanäkymä, jossa bobi kyselisi mitä on tullut tehtyä ja mitkä tehtävät on syytä siirtä myöhempään

 * työaikakirjanpito, joka tekee automaattisesti kurssien vaatimat dokumentit

### Suuruudenhulluja toiminnallisuuksia

eli toiminnallisuuksia, joita sisältävän bottisihteerin omistamisessa olisi jo *suurmiehen meininkiä*

* Automaattinen kauppalistantekijä
