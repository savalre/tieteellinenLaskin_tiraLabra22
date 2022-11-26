import java.util.*;

public class SyotteenKasittelija {
    public List<Character> jono;
    public Stack<String> pino;

    public SyotteenKasittelija() {
        this.jono = new ArrayList<>();
        this.pino = new Stack<>();
    }


    public String kasitteleInfix(String syote) {

        boolean error = false;

        String infix = "";

        for (int i = 0; i < syote.length(); i++) {
            char merkki = syote.charAt(i);

            if (Character.isDigit(merkki)) {

                infix += merkki;

            } else if (merkki == '(') {

                pino.push(String.valueOf(merkki));

            } else if (merkki == ')') {

                while (!pino.isEmpty() && !("(".equals(pino.peek()))) {
                    infix += pino.pop();
                }

                pino.pop();
                //jos ")", niin poppopqueueueue kunnes löytyy "(" ->
                //kun löytyy, lopeta popitus ja jatka
                //jos pino empty == ei löydy "(" heitä error ja palauta "hv anna kunnon syöte"
            } else {

                while (!(pino.isEmpty()) && tarkistaOperaattorinTarkeys(merkki) <= tarkistaOperaattorinTarkeys(pino.peek().charAt(0))) {
                    infix += pino.pop();
                }

                this.pino.push(String.valueOf(merkki));

            }

        }

        while (!pino.isEmpty()) {
            infix += pino.pop();
        }

        return kasittelePostfix(infix);
    }

    public Integer tarkistaOperaattorinTarkeys(Character c) {
        if ((c == '+') || (c == '-')) {
            return 1;
        }

        if ((c == '*') || (c == '/')) {
            return 2;
        }
        return -1;
    }


    public String kasittelePostfix(String postfix) {

        for (int i = 0; i < postfix.length(); i++) {
            char merkki = postfix.charAt(i);

            if (Character.isDigit(merkki)) {
                this.pino.push(String.valueOf(merkki));
            } else {
                char operaattori = merkki;
                int toinenOperandi = Integer.valueOf(this.pino.pop());
                int ensimmäinenOperandi = Integer.valueOf(this.pino.pop());
                if (operaattori == '+') {
                    int tulos = ensimmäinenOperandi + toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }
                if (operaattori == '-') {
                    int tulos = ensimmäinenOperandi - toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if (operaattori == '/') {
                    int tulos = ensimmäinenOperandi / toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if (operaattori == '*') {
                    int tulos = ensimmäinenOperandi * toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }
            }
        }

        return pino.pop();
    }

}
