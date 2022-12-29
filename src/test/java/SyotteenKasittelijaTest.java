import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SyotteenKasittelijaTest {

    public SyotteenKasittelija kasittelija;
    public Muuttujat muuttujat = new Muuttujat();

    private void oletaVirhe(String syote, Muuttujat muuttujat) {
        try {
            this.kasittelija.infixPostfixiksi(syote, muuttujat);
        } catch (Exception ex) {
            // Onnistui
            return;
        }
        throw new RuntimeException("Ei virhettä: " + syote);
    }

    private void oletaTulos(String syote, double arvo) {
        String tulos = this.kasittelija.infixPostfixiksi(syote, muuttujat);
        assertEquals(arvo, Double.parseDouble(tulos), 1e-9);
    }

    @Before
    public void setUp() {
        this.kasittelija = new SyotteenKasittelija();
    }

    @Test
    public void oikeaTulosYhdellaOperaattorilla() {
        String syote = "3+2";
        oletaTulos(syote,5.0);
    }

    @Test
    public void oikeaTulosKahdellaEriarvoisellaOperaattorilla() {
        String syote = "3+2*5";
        oletaTulos(syote,13.0);
    }

    @Test
    public void oikeaTulosSulkujenKanssa() {
        String syote = "3-7*(6/2+3)";
        oletaTulos(syote,-39.0);
    }

    @Test
    public void oikeaTulosKunNegatiivinenLukuEkanaOperandina() {
        String syote = "-1+8";
        oletaTulos(syote,7);
    }

    @Test
    public void oikeaTulosKunNegatiivinenLukuTokanaOperandina() {
        String syote = "10 - -1";
        oletaTulos(syote,11.0);
    }

    @Test
    public void toinenOperandiOnNegatiivinenLuku() {
        String syote = "1 + -1 * 5";
        oletaTulos(syote,-4);
    }

    @Test
    public void oikeaTulosKunEriarvoisetOperaattorit() {
        String syote = "1+1*5";
        oletaTulos(syote,6.0);
    }


    @Test
    public void oikeaTulosKunSyotteessaTallennettuMuuttuja() {
        muuttujat.lisaa("x","22");
        String syote = "x+22";
        oletaTulos(syote,44.0);
    }

    @Test
    public void oikeaTulosPotenssilaskulla() {
        String syote = "2^2";
        oletaTulos(syote,4.0);
    }

    @Test
    public void eiTulostaKunSyotteessaOnVainYksiSulku() {
        String syote = "5-(3+2";
        oletaVirhe(syote,muuttujat);
    }

    @Test
    public void eiTulostaKunSyotteessaParittomatSulut() {
        String syote = "5^((3+2)-2";
        oletaVirhe(syote,muuttujat);
    }

    @Test
    public void eiTulostaKunSyotteessaOlematonMuuttuja() {
        String syote = "x+3";
        oletaVirhe(syote,muuttujat);
    }

    @Test
    public void operaattorinOllessaPlusTaiMiinusTarkeysOnYksi() {
        int syote = kasittelija.operaattorinTarkeys("+");
        int tarkeys = 1;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaKertoTaiJakoTarkeysOnKaksi() {
        int syote = kasittelija.operaattorinTarkeys("*");
        int tarkeys = 2;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaEtumerkkiMiinusTarkeysOnKolme() {
        int syote = kasittelija.operaattorinTarkeys("_");
        int tarkeys = 3;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaPotenssiTarkeysOnNelja() {
        int syote = kasittelija.operaattorinTarkeys("^");
        int tarkeys = 4;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaFunktioTarkeysOnViisi() {
        int syote = kasittelija.operaattorinTarkeys("sin");
        int tarkeys = 5;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaJokinMuuTarkeysOnMiinusYksi() {
        int syote = kasittelija.operaattorinTarkeys("?");
        int tarkeys = -1;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void oikeaTulosSinFunktiolla() {
        String syote = "sin(22)";
        oletaTulos(syote,-0.008851309290403876);
    }

    @Test
    public void oikeaTulosTanFunktiolla() {
        String syote = "tan(22)";
        oletaTulos(syote,0.00885165604168446);
    }

    @Test
    public void oikeaTulosCosFunktiolla() {
        String syote = "cos(22)";
        oletaTulos(syote,-0.9999608263946371);
    }

    @Test
    public void oikeaTulosSqrtFunktiolla() {
        String syote = "sqrt(22)";
        oletaTulos(syote,4.69041575982343);
    }

    @Test
    public void eiTulostaVirheelliselläFunktiolla() {
        String syote = "asqrt(22)";
        oletaVirhe(syote,muuttujat);
    }

    @Test
    public void eiTulostaSuluttomallaFunktiolla() {
        String syote = "sqrt 22";
        oletaVirhe(syote,muuttujat);
    }

    @Test
    public void miinusSulkujenEdessä() {
        String syote = "-(22)";
        oletaTulos(syote,-22.0);
    }

    @Test
    public void miinusFunktionEdessä() {
        String syote = "-sqrt(9)";
        oletaTulos(syote,-3.0);
    }


    @Test
    public void oikeaTulosTuplasuluilla() {
        String syote = "(sqrt(22)-2)*3";
        oletaTulos(syote,8.07124727947);
    }
}
