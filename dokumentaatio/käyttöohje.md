<h1>Käyttöohje</h1>

<h3>Ohjelman suoritus</h3>

[Ohjelman viimeisin versio löytyy täältä](https://github.com/savalre/tieteellinenLaskin_tiraLabra22/releases/tag/final)<br>

Kun jar on ladattu koneelle, ohjelman voi suorittaa terminaalin kautta komennolla <b>java -jar tieteellinenLaskin-1.0-SNAPSHOT.jar</b>.<br>
Ohjelma toimii java-versiolla 8 =>.

<h3>Toiminnallisuudet ja hyväksytyt syötteet</h3>

Ohjelma tukee seuraavia toiminnallisuuksia: <br>

| Toiminnallisuus    | Komento 
| ----------- | ----------- | 
| Laskutoimitus     | 1       | 
| Laskutoimituksen arvon tallennus muuttujaan    | save _muuttujanNimi_        | 
| Muuttujan tallennus   | 2        | 
| Muuttujan poisto  | 3       | 
| Ohjelman lopettaminen    | 0        | 


#### Laskutoimituksen hyväksymät syötteet

Laskutoimitus syötetään muodossa _A-B_. Hyväksyttävät luvut laskutoimituksiin ovat reaalilukuja. Operaattoreina voi käyttää merkkejä [+ - / * ( ) ^]. Ohjelman valmiit funktiot ovat sqrt(arvo), sin(arvo), cos(arvo), tan(arvo). Funktiot tarvitsevat sulut, muuten ohjelma ei hyväksy niitä. Liukuluvuilla laskiessa desimaalien erottelijana on piste.

#### Laskutoimituksen arvon tallennus muuttujaan

Juuri evaluoidun laskutoimituksen arvon voi tallentaa muuttujaan komennolla <b>save _muuttujanNimi_</b>. HUOMIOI, että muuttujan nimi voi olla vain yksiosainen, eikä ohjelma hyväksy siihen välilyöntejä (esim. ohjelma hylkää muuttujan nimeltä "koira X", muttei "koiraX"). Ohjelma muistaa viimeisimmän oikeellisen syötteen tuloksen.

<h3>Yksikkötestien ajaminen</h3>
Ohjelman yksikkötestit voi ajaa terminaalin kautta komennolla <bmaven clean test<b>.<br>

<h3>Kattavuusraportin generointi</h3>
Testikattavuusraportin voi luoda terminaalin kautta komennolla <b>mvn test jacoco:report</b>.
