import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Luokka käsittelee käyttäjän syöttämän laskutoimituksen. Annettu laskutoimitus
 * on String. Konstruktorissa alustetaan algoritmin käyttämä pino, sekä String
 * postfix johon syötteen postfix-muoto tallentuu.
 */
public class SyotteenKasittelija {

    private final Stack<String> pino;

    public SyotteenKasittelija() {
        this.pino = new Stack<>();
    }

    /**
     * Shunting yard -algoritmi käsittelee tässä metodissa infix-muotoisen, käyttäjän antaman
     * syötteen postfix-muotoon myöhempää evaluaatiota varten.
     *
     * @param syote     käyttäjän terminaaliin syöttämä laskutoimitus (muoto esim. 3+2)
     * @param muuttujat käyttäjän tallentamat muuttujat
     * @return metodi palauttaa sitä kutsuneelle käyttöliittymälle postfixin evaluaation tuloksen.
     */
    public String infixPostfixiksi(String syote, Muuttujat muuttujat) {

        List<String> postfix = new ArrayList<>();

        String[] palat = syote.split("(?<=[-^+*/(),])|(?=[-^+*/(),])");
        String edellinen = null;

        for (String s : palat) {
            String merkki = s.trim();
            if (merkki.isEmpty()) {
                continue;
            }

            if (muuttujat.muuttujat.containsKey(merkki)) {
                merkki = muuttujat.getArvo(merkki);
            }

            if (onNumero(merkki)) {
                postfix.add(merkki);

            } else if (merkki.equals("(")) {
                pino.push(merkki);

            } else if (merkki.equals(")")) {
                while (!pino.isEmpty() && !(pino.peek().equals("("))) {
                    postfix.add(pino.pop());
                }

                pino.pop();

            } else {
                if (merkki.equals("-") && (edellinen == null || (!onNumero(edellinen) && !edellinen.equals(")")))) {
                    merkki = "_";
                }
                while (!(pino.isEmpty()) && (
                        (onkoVasenAssosiaatio(merkki) && operaattorinTarkeys(merkki) <= operaattorinTarkeys(pino.peek())) ||
                                operaattorinTarkeys(merkki) < operaattorinTarkeys(pino.peek())
                )) {
                    postfix.add(pino.pop());
                }

                if (onFunktio(merkki)) {
                    this.pino.push(merkki);
                } else {
                    this.pino.push(merkki);

                }


            }
            edellinen = merkki;
        }

        while (!pino.isEmpty()) {
            postfix.add(pino.pop());
        }
        return kasittelePostfix(postfix);
    }

    /**
     * Metodi palauttaa operaattorin "tärkeyttä" vastaavan kokonaisluvun. Suuremman tärkeyden omaavat operaatiot
     * suoritetaan ensin.
     *
     * @param c Operaattori jonka tärkeys arvioidaan
     * @return kokonaisluku operaattorille asetettujen arvojen mukaan, tai -1 jos tuntematon operaattori
     */
    public int operaattorinTarkeys(String c) {

        if ((c.equals("+")) || (c.equals("-"))) {
            return 1;
        }

        if ((c.equals("*")) || (c.equals("/"))) {
            return 2;
        }

        if ((c.equals("_"))) {
            return 3;
        }

        if ((c.equals("^"))) {
            return 4;
        }

        if (onFunktio(c)) {
            return 5;
        }

        return -1;
    }

    /**
     * Tarkistetaan onko operaattori vasemmalle assosioitunut
     *
     * @param c tarkistettava operaattori
     * @return true tai false riippuen assosiatiivisuudesta
     */

    public boolean onkoVasenAssosiaatio(String c) {

        if ((c.equals("+")) || (c.equals("-"))) {
            return true;
        }

        if ((c.equals("*")) || (c.equals("/"))) {
            return true;
        }

        if ((c.equals("^"))) {
            return false;
        }

        if (onFunktio(c)) {
            return true;
        }

        if ((c.equals("_"))) {
            return false;
        }

        throw new RuntimeException("Tuntematon operaattori");
    }

    /**
     * Tässä suoritetaan postfix-muotoisen käyttäjäsyötteen evaluaatio, eli suoritetaan itse laskutoimitus.
     *
     * @param postfix käyttäjän syötteestä luotu postfix-notaation mukainen laskutoimitus
     * @return palauttaa laskutoimituksen tuloksen metodille infixPostfixiksi, joka palauttaa sen käyttäjälle
     */
    public String kasittelePostfix(List<String> postfix) {

        for (String merkki : postfix) {
            if (onFunktio(merkki)) {
                double operandi = Double.parseDouble(this.pino.pop());
                double tulos = 0.0;

                if (merkki.equals("sqrt")) {
                    tulos = Math.sqrt(operandi);
                }

                if (merkki.equals("sin")) {
                    tulos = Math.sin(operandi);
                }

                if (merkki.equals("cos")) {
                    tulos = Math.cos(operandi);
                }

                if (merkki.equals("tan")) {
                    tulos = Math.tan(operandi);
                }

                this.pino.push(String.valueOf(tulos));

            } else if (onNumero(merkki)) {
                this.pino.push(merkki);

            } else if (merkki.equals("_")) {
                double operandi = Double.parseDouble(this.pino.pop());
                double tulos = -operandi;
                this.pino.push(String.valueOf(tulos));

            } else {
                String operaattori = merkki;
                double toinenOperandi = Double.parseDouble(this.pino.pop());
                double ensimmainenOperandi = Double.parseDouble(this.pino.pop());
                double tulos = 0.0;

                if (operaattori.equals("^")) {
                    tulos = Math.pow(ensimmainenOperandi, toinenOperandi);
                }
                if (operaattori.equals("+")) {
                    tulos = ensimmainenOperandi + toinenOperandi;
                }
                if (operaattori.equals("-")) {
                    tulos = ensimmainenOperandi - toinenOperandi;
                }

                if (operaattori.equals("/")) {
                    tulos = ensimmainenOperandi / toinenOperandi;
                }

                if (operaattori.equals("*")) {
                    tulos = ensimmainenOperandi * toinenOperandi;
                }

                this.pino.push(String.valueOf(tulos));

            }
        }

        String tulos = pino.pop();

        return tulos;
    }

    /**
     * Tarkistetaan onko syötteen osa numero vai ei
     *
     * @param pala syötteen osa jota tarkastellaan
     * @return true tai false
     */
    private static boolean onNumero(String pala) {
        try {
            Double.parseDouble(pala);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Tarkistetaan onko syötteen osa funktio vai ei
     *
     * @param pala syötteen osa jota tarkastellaan
     * @return true tai false
     */

    private static boolean onFunktio(String pala) {
        return pala.equals("sqrt") || pala.equals("sin") || pala.equals("cos") || pala.equals("tan");
    }

}
