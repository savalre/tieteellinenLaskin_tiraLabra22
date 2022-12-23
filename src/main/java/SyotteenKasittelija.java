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

    public String kasitteleSyote(String syote) {
       /* if(!validoi(syote)){
            return "virhe";
        }*/
        this.pino = new Stack<>();

        return infixPostfixiksi(syote);
    }

    /**
     * Shunting yard -algortmi käsittelee tässä metodissa infix-muotoisen, käyttäjän antaman
     * syötteen postfix-muotoon myöhempää evaluaatiota varten.
     *
     * @param syote käyttäjän terminaaliin syöttämä laskutoimitus (muoto esim. 3+2)
     * @return metodi palauttaa sitä kutsuneelle käyttöliittymälle postfixin evaluaation tuloksen.
     */
    public String infixPostfixiksi(String syote) {

        String postfix = "";

        String[] palat = syote.split("(?<=[-^+*/(),])|(?=[-^+*/(),])");

        for (int i = 0; i < palat.length; i++) {
            String merkki = palat[i];

            if (LaskinLaunch.muuttujat.muuttujat.containsKey(merkki)) {
                merkki = LaskinLaunch.muuttujat.getArvo(merkki);
            }

            if (onNumero(merkki)) {
                postfix += merkki + ",";

            } else if (merkki.equals("(")) {
                //System.out.println("löytyi (");
                pino.push(merkki);

            } else if (merkki.equals(")")) {
                //System.out.println("löytyi )");
                while (!pino.isEmpty() && !(pino.peek().equals("("))) {
                    postfix += pino.pop();
                    //System.out.println("postfix on nyt " + postfix);
                }

                pino.pop();

            } else {
               // System.out.println("Operaattorin tarkeys  " + merkki + " = " + operaattorinTarkeys(merkki));
               // System.out.println("onko isompi pinossa" + (!(pino.isEmpty()) && operaattorinTarkeys(merkki) <= operaattorinTarkeys(pino.peek())));
                while (!(pino.isEmpty()) && operaattorinTarkeys(merkki) <= operaattorinTarkeys(pino.peek())) {
                  //  System.out.println("Operaattorin tarkeys jonossa " + pino.peek() + " = " + operaattorinTarkeys(pino.peek()));
                    if(onFunktio(pino.peek())){
                      //  System.out.println("funktio lisätään");
                        postfix += pino.pop()+",";
                    }else{
                        postfix += pino.pop();

                    }
                   // System.out.println("postfix " + postfix);
                }
                if(onFunktio(merkki)){
                   // System.out.println("lisatty pinoon " + (merkki+","));
                    this.pino.push(merkki+",");
                }else{
                   // System.out.println("lisatty pinoon " + merkki);

                    this.pino.push(merkki);

                }

            }

        }

        while (!pino.isEmpty()) {
            postfix += pino.pop();
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
    public Integer operaattorinTarkeys(String c) {
        if ((c.equals("+")) || (c.equals("-"))) {
            return 1;
        }

        if ((c.equals("*")) || (c.equals("/"))) {
            return 2;
        }

        if ((c.equals("^"))) {
            return 3;
        }

        if (c.contains("sin") || c.contains("tan") || c.contains("cos") || c.contains("sqrt")) {
            return 4;
        }

        return -1;
    }

    /**
     * Tässä suoritetaan postfix-muotoisen käyttäjäsyötteen evaluaatio, eli suoritetaan itse laskutoimitus.
     *
     * @param postfix käyttäjän syötteestä luotu postfix-notaation mukainen laskutoimitus
     * @return palauttaa laskutoimituksen tuloksen metodille infixPostfixiksi, joka palauttaa sen käyttäjälle
     */
    public String kasittelePostfix(String postfix) {
        String[] lasku = postfix.split("(?<=[-+*/^(),])|(?=[-+*/^(),])");
       // System.out.println(Arrays.toString(lasku));

        for (int i = 0; i < lasku.length; i++) {
            String merkki = lasku[i];

            if (merkki.equals(",")) {
                continue;
            } else if (onFunktio(merkki)){
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

        String tulos = "Tulos: " + pino.pop();

        return tulos;
    }

    public Boolean validoi(String syote) {
        boolean validi = false;

        for (int i = 0; i < syote.length(); i++) {

        }
        /*
        - kaveriton sulku
        - operaattori
        * */
        return validi;
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
        if(pala.contains("sqrt") ||  pala.contains("sin") || pala.contains("cos") || pala.contains("tan")){
           // System.out.println("CONTAINS");
            return true;
        }

        //System.out.println("NOT CONTAINS");
        return false;
    }

}