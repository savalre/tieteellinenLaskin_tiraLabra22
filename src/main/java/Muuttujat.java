import java.util.HashMap;

public class Muuttujat {
    /**
     * Luokka käsittelee käyttäjän tallentamia muuttujia.
     */
    public HashMap<String, String> muuttujat;

    public Muuttujat() {
        this.muuttujat = new HashMap<>();
    }

    /**
     * Hakee avaimen perusteella HashMapista arvon.
     * @param avain HashMapiin tallennetun muuttujan nimi
     * @return avaimen arvo
     */
    public String getArvo(final String avain) {
        return muuttujat.get(avain);
    }

    /**
     * Tarkistaa löytyykö HashMapista tallennettuja muuttujia.
     * @return true tai false riippuen onko HashMap tyhjä vai ei
     */
    public boolean isEmpty() {
        return muuttujat.isEmpty();
    }

    /**
     * Poistaa muuttuja-arvo-parin HashMapista.
     * @param nimi poistettavan muuttujan avain
     */
    public void poista(final String nimi) {
        this.muuttujat.remove(nimi);
    }

    /**
     * Lisää muuttujan ja arvon avain-arvo-parina HashMapiin.
     * Mitään ei tallenneta jos nimi tai arvo on tyhjä.
     * @param nimi muuttujan nimi
     * @param arvo muuttujaan yhdistettävä arvo
     */
    public void lisaa(final String nimi, final String arvo) {
        if (nimi.isEmpty() || arvo.isEmpty()) {
            System.out.println("Tarkista muuttuja!\n");
        } else {
            this.muuttujat.put(nimi, arvo);
        }
    }

    @Override
    public String toString() {
        return muuttujat.toString();
    }
}
