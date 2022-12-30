<h1>Toteutusdokumentti</h1>

<h3>Ohjelman yleisrakenne</h3>Ohjelma koostuu kolmesta luokasta. 
<br><br>
<b>LaskinLaunch.java:</b><br>
Luokka vastaa ohjelman käynnistyksestä ja käyttöliittymästä. Se tulkitsee käyttäjän syötteet, ja kutsuu toiminnosta riippuen muita luokkia.
<br>
<br>
<b>SyotteenKasittelija.java:</b><br>
Vastaa käyttäjän laskutoimitussyötteen käsittelystä postfix-muotoon, sekä postfix-muotoisen lausekkeen evaluoimisesta tulokseksi.
<br>
<br>
<b>Muuttujat.java:</b><br>
Käyttäjän on mahdollista tallentaa muuttujia ohjelman muistiin session ajaksi. Muuttujat-luokka sisältää muuttujien tallentamiseen käytettävän HashMapin ja sen vaatimat metodit.
<br>
<br>
<h3>Saavutetut aika- ja tilavaativuudet</h3>
Hyvän pseudokoodin algoritmista löytää esimerkiksi Wikipediasta.<br>

#### Aikavaativuus

Kirjallisuudessa shunting yard -algoritmin aikavaativuus on pahimmassa tapauksessa O(n). Toteutuksessani algoritmi muuttaa infix-muotoisen syötteen postfix-muotoon käymällä jokaisen lausekkeen merkin läpi vain kerran. Lisäksi käytetään operaatioita pop() ja push(), joiden aikavaativuus on O(1). Täten algoritmin pahimman tapauksen aikavaativuus toteutuksessani on O(n), sillä aikavaativuus on suoraan verrannollinen syötteen merkkijonon pituuteen.


#### Tilavaativuus

Kirjallisuudessa shunting yard -algoritmin tilavaativuus on pahimmassa tapauksessa O(n). Tilavaativuus määräytyy pinon koon mukaan. Algoritmi käyttää pinoa syötteenkäsittelyn aikana operaattoreiden tallentamiseen, jolloin pinon koon enimmäismäärä on syötteen merkkien määrä n. Täten katsoisin, että olen saanut toteutettua tilavaatimuksen O(n).


<br><br>
<h3>Puutteet ja parannusehdotukset</h3>
Ohjelma ei tunnista suoraan ennen sulkuja olevaa numeroa kertolaskuksi. Olisi myös hyvä, jos ohjelman muuttujiin voisi tallentaa evaluoimattomia lausekkeita. Lisäksi koodin rakennetta tulisi parantaa, varsinkin SyotteenKasittelijän ollessa monihaarainen if-spagettihirviö.

<h3>Lähteet:</h3>
[Wikipedia: Shunting yard](https://en.wikipedia.org/wiki/Shunting_yard_algorithm)

