import java.util.*;

public class SyotteenKasittelija {
    /**
     * Luokka käsittelee käyttäjän syöttämän laskutoimituksen. Annettu laskutoimitus
     * on String-muotoinen. Konstruktorissa alustetaan algoritmin käyttämä pino, sekä String
     * postfix johon syötteen postfix-muoto tallentuu.
     */
    public Stack<String> pino;
    public String postfix;

    public SyotteenKasittelija() {
        this.pino = new Stack<>();
        this.postfix = "";
    }

    /**
     * Shunting yard -algortmi käsittelee tässä metodissa infix-muotoisen, käyttäjän antaman
     * syötteen postfix-muotoon myöhempää evaluaatiota varten.
     * @param syote  käyttäjän terminaaliin syöttämä laskutoimitus (muoto esim. 3+2)
     * @return  metodi palauttaa sitä kutsuneelle käyttöliittymälle postfixin evaluaation tuloksen.
     */
    public String infixPostfixiksi(String syote) {

        String postfix = "";

        for (int i = 0; i < syote.length(); i++) {
            char merkki = syote.charAt(i);

            if (Character.isDigit(merkki)) {
                postfix += merkki;

            } else if (merkki == '(') {

                pino.push(String.valueOf(merkki));

            } else if (merkki == ')') {

                while (!pino.isEmpty() && !("(".equals(pino.peek()))) {
                    postfix += pino.pop();
                }

                pino.pop();

            } else {

                while (!(pino.isEmpty()) && operaattorinTarkeys(merkki) <= operaattorinTarkeys(pino.peek().charAt(0))) {
                    postfix += pino.pop();
                }

                this.pino.push(String.valueOf(merkki));

            }

        }

        while (!pino.isEmpty()) {
            postfix += pino.pop();
        }

        this.postfix = postfix;

        return kasittelePostfix(this.postfix);
    }

    /**
     * Metodi palauttaa operaattorin "tärkeyttä" vastaavan kokonaisluvun. Suuremman tärkeyden omaavat operaatiot
     * suoritetaan ensin.
     * @param c Operaattori jonka tärkeys arvioidaan
     * @return  kokonaisluku operaattorille asetettujen arvojen mukaan, tai -1 jos tuntematon operaattori
     */
    public Integer operaattorinTarkeys(Character c) {
        if ((c == '+') || (c == '-')) {
            return 1;
        }

        if ((c == '*') || (c == '/')) {
            return 2;
        }

        return -1;
    }

    /**
     * Tässä suoritetaan postfix-muotoisen käyttäjäsyötteen evaluaatio, eli suoritetaan itse laskutoimitus.
     * @param postfix käyttäjän syötteestä luotu postfix-notaation mukainen laskutoimitus
     * @return  palauttaa laskutoimituksen tuloksen metodille infixPostfixiksi, joka palauttaa sen käyttäjälle
     */
    public String kasittelePostfix(String postfix) {

        for (int i = 0; i < postfix.length(); i++) {
            char merkki = postfix.charAt(i);

            if (Character.isDigit(merkki)) {
                this.pino.push(String.valueOf(merkki));
            } else {
                char operaattori = merkki;
                int toinenOperandi = Integer.valueOf(this.pino.pop());
                int ensimmainenOperandi = Integer.valueOf(this.pino.pop());
                if (operaattori == '+') {
                    int tulos = ensimmainenOperandi + toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }
                if (operaattori == '-') {
                    int tulos = ensimmainenOperandi - toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if (operaattori == '/') {
                    int tulos = ensimmainenOperandi / toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if (operaattori == '*') {
                    int tulos = ensimmainenOperandi * toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }
            }
        }

        return pino.pop();
    }
}
