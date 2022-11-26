import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SyotteenKasittelijaTest {

    public SyotteenKasittelija kasittelija;

    @Before
    public void setUp() {
        this.kasittelija = new SyotteenKasittelija();
    }

    @Test
    public void infixKasitellaanOikeinYhdellaOperaattorilla() {
        kasittelija.infixPostfixiksi("3+2");
        String infix = kasittelija.postfix;
        String oletus = "32+";

        assertEquals(oletus,infix);
    }

    @Test
    public void infixKasitellaanOikeinKahdellaEriarvoisellaOperaattorilla() {
        kasittelija.infixPostfixiksi("3+2*5");
        String infix = kasittelija.postfix;
        String oletus = "325*+";

        assertEquals(oletus,infix);
    }

    @Test
    public void infixKasitellaanOikeinSulkujenKanssa() {
        kasittelija.infixPostfixiksi("3-7*(6/2+3)");
        String infix = kasittelija.postfix;
        String oletus = "3762/3+*-";

        assertEquals(oletus,infix);
    }

    /*@Test
    public void infixinKasittelyEpaonnistuuKunSyotteessaOnVainYksiSulku() {
        String syote = kasittelija.kasitteleInfix("5-(3+2");
        String oletus = "32+"; //KORJAA

        assertEquals(oletus,syote);
    }*/

    @Test
    public void operaattorinOllessaPlusTaiMiinusTarkeysOnYksi() {
        int syote = kasittelija.operaattorinTarkeys('+');
        int tarkeys = 1;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaKertoTaiJakoTarkeysOnKaksi() {
        int syote = kasittelija.operaattorinTarkeys('*');
        int tarkeys = 2;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaJokinMuuTarkeysOnMiinusYksi() {
        int syote = kasittelija.operaattorinTarkeys('?');
        int tarkeys = -1;

        assertEquals(tarkeys,syote);
    }


    @Test
    public void laskutoimitusOikeinYhdellaOperaattorilla() {
        String tulos = kasittelija.kasittelePostfix("32+");
        String oletus = "5";

        assertEquals(oletus,tulos);
    }

    @Test
    public void laskutoimitusOikeinKahdellaEriarvoisellaOperaattorilla() {
        String tulos = kasittelija.kasittelePostfix("325*+");
        String oletus = "13";

        assertEquals(oletus,tulos);
    }

    @Test
    public void laskutoimitusOikeinKunLausekkeessaSulkuja() {
        String tulos = kasittelija.kasittelePostfix("3762/3+*-");
        String oletus = "-39";

        assertEquals(oletus,tulos);
    }
}
