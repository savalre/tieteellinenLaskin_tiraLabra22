<h1>Viikkoraportti 3</h1>
<b>Mitä olen tehnyt tällä viikolla?</b><br>
Tällä viikolla olen tapellut javafx:n kanssa. En saanut asettelua ja jarriksi pakkaamista toimimaan niinkuin olisin halunnut. 
Siksi hylkäsin sen kehittämisen ja keskityin pinon ja jonon implemointiin, ja sitä kautta myös sovelluslogiikan alkuun saamista. 
<br>
<br>
<b>Miten ohjelma on edistynyt?</b><br>
Olen saanut saanut nyt ohjelman toimimaan lyhyillä syötteillä. Koodi on kuitenkin sekavaa, ja lisäksi olen tehnyt joitain
huonoja valintoja syötteen käsittelyn suhteen. Esimerkiksi tällä hetkellä koodi ei ota huomioon lukuja joissa on enemmän kuin yksi numero.
Lisäksi kaikki logiikka on surullisesti samassa java-tiedostossa.   
<br>
<br>
<b>Mitä opin tällä viikolla?</b><br>
Opin miten debugataan configuraatio-ongelmia, ja miten javalla toteutetaan jono. Lisäksi tutustuin tarkemmin shunting yardin toimintaan.
<br>
<br>
<b>Mikä jäi epäselväksi tai tuottanut vaikeuksia?</b><br>
Jos ei ehdi toteuttaa kunnon graafista UI:ta, saako hylätyn kurssista? Riittääkö hyvin toteutetta terminaalin kautta käytettävä tekstiliittymä?
<br>
<br>
<b>Mitä teen seuraavaksi?</b><br>
Seuraavaksi otan kiinni sen, että olen dokumentaatiossa auttamattomasti jäljessä.
Lisäksi pitää confata checkstyle ja testikattavuuden seuranta ja refaktoroida koodi niin että voi laskea useita operaatioita samassa lausekkeessa.
Toteutan myös shunting yard -algoritmin vaatimat operaattoreiden "järjestyksen" (eli esim '*' on "tärkeämpi" kuin '+', joten se pitää käsitellä ensin).


