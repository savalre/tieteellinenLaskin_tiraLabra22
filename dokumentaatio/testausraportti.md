<h1>Testausraportti</h1>

<h3>Ohjelman testaus</h3>
Ohjelman luonteen vuoksi testaus suoritettiin laajasti yksikkötesteillä. Yksikkötestit tehtiin ns. suorittavista luokista, joita oli tässä tapauksessa luokat Muuttujat.java ja SyotteenKasittelija.java. Yksikkötesteissä pyrittiin yli 96 % haaraumakattavuuteen, ja tarkoitus oli kattaa myös virhetilanteet laajasti. Testit voi jakaa kahteen kategoriaan: oletetaan oikea tulos, tai oletetaan virhe. Koska ohjelma ei erittele erilaisten virheiden välillä, olettamalla virheen oletetaan, että ohjelma ilmoittaa väärästä syötteestä/tapahtumasta kaatumisen sijaan.<br>
Yksikkötesteissä on testattu niin syötteen muokkaamista postfix-muotoon kuin postfixin evaluoimista (SyotteenKasittelijaTest, kuin Muuttujat-luokan toimintaa. Infix->postfix -muokkauksen ja postfixin evaluoimisen eri kohtia on testattu, joko olettamalla oikea tulos tai virhe. Näin on pyritty varmistamaan, että ohjelman kaikissa vaiheissa (joita on mooonta) kaikki menee oikein, eikä ohjelma anna virheellistä tulosta koskaan.<br>
Käyttöliittymätestaus suoritettiin manuaalisesti koettamalla kaataa ohjelma väärillä syötteillä ja komennoilla.Käyttöliittymä ei itse suorita mitään toiminnallisuuksia, mitä ei olisi testattu muiden luokkien yksikkötesteissä, joten jätin sen yksikkätestauksen ulkopuolelle.
<br>
<br>
<h3>Yksikkötestit ja kattavuusraportti</h3>
Ohjelman yksikkötestit voi ajaa terminaalin kautta komennolla <b>maven clean test</b>.<br>
Testikattavuusraportin voi luoda terminaalin kautta komennolla <b>mvn test jacoco:report</b>.
<br><br>
<h3>Yksikkötestien kattavuusraportti </h3>
  
![Kattavuusraportti](https://github.com/savalre/tieteellinenLaskin_tiraLabra22/blob/332da1a92aa712d70a63956e050cfdba89aa7115/dokumentaatio/testikattavuus.png) <br>

  En kerinnyt poistaa jacocon asetuksista käyttöliittymän (LaskinLaunch) jättämistä raportin ulkopuolelle, joten se vääristää hieman haaraumakattaumaa.
