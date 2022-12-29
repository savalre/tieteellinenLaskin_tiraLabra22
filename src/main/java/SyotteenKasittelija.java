import java.lang.reflect.Array;
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


    /**
     * Shunting yard -algoritmi käsittelee tässä metodissa infix-muotoisen, käyttäjän antaman
     * syötteen postfix-muotoon myöhempää evaluaatiota varten.
     *
     * @param syote käyttäjän terminaaliin syöttämä laskutoimitus (muoto esim. 3+2)
     * @param muuttujat
     * @return metodi palauttaa sitä kutsuneelle käyttöliittymälle postfixin evaluaation tuloksen.
     */
    public String infixPostfixiksi(String syote, Muuttujat muuttujat) {

        List<String> postfix = new ArrayList<>();

        String[] palat = syote.split("(?<=[-^+*/(),])|(?=[-^+*/(),])");
        String edellinen = null;

        for (int i = 0; i < palat.length; i++) {
            String merkki = palat[i].trim();
            if (merkki.isEmpty()) {
                continue;
            }
            System.out.println("Merkki '" + merkki + "'  pino=" + pino);

            if (muuttujat.muuttujat.containsKey(merkki)) {
                merkki = muuttujat.getArvo(merkki);
            }

            if (onNumero(merkki)) {
                postfix.add(merkki);
                System.out.println("Numero " + merkki + " => " + postfix + "  pino=" + pino);

            } else if (merkki.equals("(")) {
                //System.out.println("löytyi (");
                pino.push(merkki);

            } else if (merkki.equals(")")) {
                //System.out.println("löytyi )");
                while (!pino.isEmpty() && !(pino.peek().equals("("))) {
                    postfix.add(pino.pop());
                    //System.out.println("postfix on nyt " + postfix);
                }

                pino.pop();

            } else {
                if (merkki.equals("-")) {
                    System.out.println(String.format("Löytyi -: i=%d edellinen=%s", i, edellinen));
                }
                if (merkki.equals("-") && (edellinen == null || (!onNumero(edellinen) && !edellinen.equals(")")))) {
                    merkki = "_";
                }
                System.out.println("Operaattorin tarkeys  '" + merkki + "' = " + operaattorinTarkeys(merkki));
                System.out.println("onko isompi pinossa" + (!(pino.isEmpty()) && operaattorinTarkeys(merkki) <= operaattorinTarkeys(pino.peek())));
                while (!(pino.isEmpty()) && (
                        (onkoVasenAssosiaatio(merkki) && operaattorinTarkeys(merkki) <= operaattorinTarkeys(pino.peek())) ||
                                operaattorinTarkeys(merkki) < operaattorinTarkeys(pino.peek())
                )) {
                    System.out.println("Operaattorin tarkeys jonossa " + pino.peek() + " = " + operaattorinTarkeys(pino.peek()));

                        postfix.add(pino.pop());

                   // System.out.println("postfix " + postfix);
                }
                if(onFunktio(merkki)){
                   // System.out.println("lisatty pinoon " + (merkki+","));
                    this.pino.push(merkki);
                }else{
                   // System.out.println("lisatty pinoon " + merkki);

                    this.pino.push(merkki);

                }
                if (merkki.equals("-")) {
                    System.out.println("Miinus => " + postfix + "  pino=" + pino);
                }


            }
               edellinen = merkki;
        }

        while (!pino.isEmpty()) {
            postfix.add(pino.pop());
        }
        System.out.println("postfix" + postfix);
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

        throw new RuntimeException("tuntematon operaattori");
    }

    /**
     * Tässä suoritetaan postfix-muotoisen käyttäjäsyötteen evaluaatio, eli suoritetaan itse laskutoimitus.
     *
     * @param postfix käyttäjän syötteestä luotu postfix-notaation mukainen laskutoimitus
     * @return palauttaa laskutoimituksen tuloksen metodille infixPostfixiksi, joka palauttaa sen käyttäjälle
     */
    public String kasittelePostfix(List<String> postfix) {
        //String[] lasku = postfix.split("(?<=[-+*/^()_,])|(?=[-+*/^()_,])");
       // System.out.println(Arrays.toString(lasku));

        for (int i = 0; i < postfix.size(); i++) {
            String merkki = postfix.get(i);

           if (onFunktio(merkki)){
                Double operandi = Double.parseDouble(this.pino.pop());
                Double tulos = 0.0;

                if (merkki.equals("sqrt")){
                    tulos = Math.sqrt(operandi);
                }

                if (merkki.equals("sin")){
                    tulos = Math.sin(operandi);
                }

                if (merkki.equals("cos")){
                    tulos = Math.cos(operandi);
                }

                if (merkki.equals("tan")){
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
                Double toinenOperandi = Double.parseDouble(this.pino.pop());
                Double ensimmainenOperandi = Double.parseDouble(this.pino.pop());
                Double tulos = 0.0;

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


    private static boolean onNumero(String pala) {
        try {
            Double.parseDouble(pala);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean onFunktio(String pala) {
        System.out.println("PALA '"+ pala + "'");
        if(pala.equals("sqrt") ||  pala.equals("sin") || pala.equals("cos") || pala.equals("tan")){
           // System.out.println("CONTAINS");
            return true;
        }

        //System.out.println("NOT CONTAINS");
        return false;
    }

}