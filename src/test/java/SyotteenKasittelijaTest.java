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
        kasittelija.kasitteleInfix("3+2");
        String infix = kasittelija.infix;
        String oletus = "32+";

        assertEquals(oletus,infix);
    }

    @Test
    public void infixKasitellaanOikeinKahdellaEriarvoisellaOperaattorilla() {
        kasittelija.kasitteleInfix("3+2*5");
        String infix = kasittelija.infix;
        String oletus = "325*+"; 

        assertEquals(oletus,infix);
    }

    @Test
    public void infixKasitellaanOikeinSulkujenKanssa() {
        kasittelija.kasitteleInfix("3-7*(6/2+3)");
        String infix = kasittelija.infix;
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
        int syote = kasittelija.tarkistaOperaattorinTarkeys('+');
        int tarkeys = 1;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaKertoTaiJakoTarkeysOnKaksi() {
        int syote = kasittelija.tarkistaOperaattorinTarkeys('*');
        int tarkeys = 2;

        assertEquals(tarkeys,syote);
    }

    @Test
    public void operaattorinOllessaJokinMuuTarkeysOnMiinusYksi() {
        int syote = kasittelija.tarkistaOperaattorinTarkeys('?');
        int tarkeys = -1;

        assertEquals(tarkeys,syote);
    }



    /*Testejä:
    *   kasittelePostfix*/

}
