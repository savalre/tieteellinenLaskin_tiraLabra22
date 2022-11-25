import java.util.*;

public class laskinLaunch  {
    public List<Character> jono;
    public Stack<String> pino;

    public laskinLaunch() {
        this.jono = new ArrayList<>();
        this.pino = new Stack<>();
    }


    public static void main(String[] args) {
        laskinLaunch launch = new laskinLaunch();
        System.out.println("---- TIETEELLINEN LASKIN v.0.5 ----");
        System.out.println("Kirjoita laskutoimitus.\nVoit" +
                " valita operaattorit [+ - / *].\n" +
                "Syötä laskutoimitus muodossa <A*B>. Voit käytää vain kahta operandia (lukuja 0-9) ja yhtä operaattoria");
        while(true){
            String syote = launch.lueSyote();
            if(syote.equals("loppu")){
                System.out.println("Näkemiin!");
                break;
            }
            System.out.println("Tulos: " + syote);
        }

    }

    public String lueSyote(){
        Scanner lukija = new Scanner(System.in);
        String infix = lukija.nextLine();
        if(infix.equals("0")){
            return "loppu";
        }
        return kasitteleInfix(infix);

    }

    public String kasitteleInfix(String syote){

        boolean error = false;

        String infix = "";

        for(int i = 0; i < syote.length(); i++){
            char merkki = syote.charAt(i);
            Boolean luku = Character.isDigit(merkki);

            if(luku){
                infix += merkki;
            } else {
                if(!(this.pino.isEmpty())){

                        /*if(merkkiPinoon.equals(")")){
                    //jos ")", niin poppopqueueueue kunnes löytyy "(" ->
                    //kun löytyy, lopeta popitus ja jatka
                    //jos pino empty == ei löydy "(" heitä error ja palauta "hv anna kunnon syöte"
                    }*/

                if(tarkistaOperaattorinTarkeys(merkki) <= tarkistaOperaattorinTarkeys(pino.peek().charAt(0))){
                    infix += pino.pop();
                }
                }

                //jos merkkiPinoon tärkeys > kuin pinonEka
                    //==> pushaa stackiin
                //oletus että merkki on tärkeydeltään >= kuin pinossa oleva, jolloin se menee pinon päällimmäiseksi
                this.pino.add(String.valueOf(merkki));
            }
        }

        if(error){
            return "Virheellinen syöte, ole hyvä ja tarkista!";
        }

        while(!pino.isEmpty()){
            infix += pino.pop();
        }

        System.out.println("stackki on " + this.pino);


        System.out.println("infix on " + infix);

        return kasittelePostfix(infix);
    }

    public Integer tarkistaOperaattorinTarkeys(Character c){
        if((c == '+') || (c =='-')){
            return 1;
        }

        if((c == '*') || (c == '/')){
            return 2;
        }

        //oletetaan operaattorin olevan ^

        return -1;
    }


    public String kasittelePostfix(String postfix){
        for(int i = 0; i < postfix.length(); i++){
            char merkki = postfix.charAt(i);
            Boolean luku = Character.isDigit(merkki);
            if(luku){
                this.pino.add(String.valueOf(merkki));
            } else {
                char operaattori = merkki;
                int toinenOperandi = Integer.parseInt(this.pino.pop());
                int ensimmäinenOperandi = Integer.parseInt(this.pino.pop());
                if(operaattori == '+'){
                    int tulos = ensimmäinenOperandi + toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }
                if(operaattori == '-'){
                    int tulos = ensimmäinenOperandi - toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if(operaattori == '/'){
                    int tulos = ensimmäinenOperandi / toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }

                if(operaattori == '*'){
                    int tulos = ensimmäinenOperandi * toinenOperandi;
                    this.pino.push(String.valueOf(tulos));
                }
            }
        }

        return pino.pop();
    }


}
