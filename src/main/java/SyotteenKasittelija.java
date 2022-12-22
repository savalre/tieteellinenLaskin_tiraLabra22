import java.util.*;

public class SyotteenKasittelija {
    /**
     * Luokka käsittelee käyttäjän syöttämän laskutoimituksen. Annettu laskutoimitus
     * on String-muotoinen. Konstruktorissa alustetaan algoritmin käyttämä pino, sekä String
     * postfix johon syötteen postfix-muoto tallentuu.
     */
    public Stack<String> pino;

    public SyotteenKasittelija() {
        this.pino = new Stack<>();
    }

    public String kasitteleSyote(String syote){
       /* if(!validoi(syote)){
            return "virhe";
        }*/
        this.pino = new Stack<>();

        return infixPostfixiksi(syote);
    }

    /**
     * Shunting yard -algortmi käsittelee tässä metodissa infix-muotoisen, käyttäjän antaman
     * syötteen postfix-muotoon myöhempää evaluaatiota varten.
     * @param syote  käyttäjän terminaaliin syöttämä laskutoimitus (muoto esim. 3+2)
     * @return  metodi palauttaa sitä kutsuneelle käyttöliittymälle postfixin evaluaation tuloksen.
     */
    public String infixPostfixiksi(String syote) {

        String postfix = "";

        String[] palat = syote.split("(?<=[-+*/(),])|(?=[-+*/(),])");
        System.out.println("palat " + Arrays.toString(palat));
        for (int i = 0; i < palat.length; i++) {
            String merkki = palat[i];

            if (onNumero(merkki)) {
                postfix += merkki+",";

            } else if (merkki.equals("(")) {

                pino.push(merkki);


            } else if (merkki.equals(")")) {

                while (!pino.isEmpty() && !(pino.peek().equals("("))) {
                    postfix += pino.pop();
                }

                pino.pop();

            } else {

                while (!(pino.isEmpty()) && operaattorinTarkeys(merkki) <= operaattorinTarkeys(pino.peek())) {
                    postfix += pino.pop();
                }

                this.pino.push(merkki);

            }

        }

        while (!pino.isEmpty()) {
            postfix += pino.pop();
        }

        String tulos = kasittelePostfix(postfix);
        System.out.println("TULOS LÖYTYI " + postfix);
        return tulos;
    }

    /**
     * Metodi palauttaa operaattorin "tärkeyttä" vastaavan kokonaisluvun. Suuremman tärkeyden omaavat operaatiot
     * suoritetaan ensin.
     * @param c Operaattori jonka tärkeys arvioidaan
     * @return  kokonaisluku operaattorille asetettujen arvojen mukaan, tai -1 jos tuntematon operaattori
     */
    public Integer operaattorinTarkeys(String c) {
        if ((c.equals("+")) || (c.equals("-"))) {
            return 1;
        }

        if ((c.equals("*")) || (c.equals("/"))) {
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
        System.out.println("kasittelePostfix " + postfix);
        String[] lasku = postfix.split("(?<=[-+*/(),])|(?=[-+*/(),])");
        System.out.println("lasku " + Arrays.toString(lasku));


        for (int i = 0; i < lasku.length; i++) {
            String merkki = lasku[i];
            System.out.println("kierros " + i + "jamerkki" + merkki);

            if(merkki.equals(",")){
                System.out.println("PILKKU");
                continue;
            } else if (onNumero(merkki)) {
                System.out.println("NUMERO " + merkki);
                this.pino.push(merkki);
            } else {
                System.out.println("else: operaattori " + merkki);
                String operaattori = merkki;
                System.out.println("operaattori " + operaattori);
                int toinenOperandi = Integer.parseInt(this.pino.pop());
                int ensimmainenOperandi = Integer.parseInt(this.pino.pop());

                if (operaattori.equals("+")) {
                    int tulos = ensimmainenOperandi + toinenOperandi;
                    System.out.println("tulos " + tulos);
                    this.pino.push(String.valueOf(tulos));
                }
                if (operaattori.equals("-")) {
                    int tulos = ensimmainenOperandi - toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if (operaattori.equals("/")) {
                    int tulos = ensimmainenOperandi / toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if (operaattori.equals("*")) {
                    int tulos = ensimmainenOperandi * toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }
            }
        }

        String tulos = pino.pop();

        return tulos;
    }

    public Boolean validoi(String syote){
        boolean validi = false;

        for(int i = 0; i < syote.length(); i++){

        }
        /*
        - kaveriton sulku
        - operaattori
        * */
        return validi;
    }

    private static boolean onNumero(String pala) {
        try {
            Integer.parseInt(pala);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
