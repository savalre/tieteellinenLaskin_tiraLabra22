import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MuuttujatTest {

    private Muuttujat muuttujat;

    @Before
    public void setUp() {
        muuttujat = new Muuttujat();
    }

    @Test
    public void uusiMapOnTyhja() {
        assertTrue(muuttujat.isEmpty());
    }

    @Test
    public void nimettyMuuttujaLisataan() {
        String muuttuja = "234";
        muuttujat.lisaa("kissa",muuttuja);
        assertTrue(muuttujat.muuttujat.containsKey("kissa"));
    }

    @Test
    public void arvotonMuuttujaEiLisata() {
        muuttujat.lisaa("kissa","");
        assertFalse(muuttujat.muuttujat.containsKey("kissa"));
    }

    @Test
    public void nimetonMuuttujaEiLisata() {
        muuttujat.lisaa("","222");
        assertFalse(muuttujat.muuttujat.containsValue("222"));
    }

    @Test
    public void toStringToimii() {
        muuttujat.lisaa("kissa","22");
        muuttujat.lisaa("koiro","222");
        assertEquals("{koiro=222, kissa=22}",muuttujat.toString());
    }

    @Test
    public void poistettuMuuttujaPoistuu() {
        muuttujat.lisaa("kissa","222");
        muuttujat.lisaa("koiro","2222");
        muuttujat.poista("kissa");
        assertFalse(muuttujat.muuttujat.containsKey("kissa"));
    }

    @Test
    public void avainPalauttaaOikeanArvon() {
        muuttujat.lisaa("kissa","222");
        muuttujat.lisaa("koiro","2222");
        String arvo = muuttujat.getArvo("kissa");
        assertEquals(arvo,"222");
    }
}
