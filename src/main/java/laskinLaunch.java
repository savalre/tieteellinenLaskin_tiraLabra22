import java.io.*;
import java.util.*;

public class laskinLaunch  {
    public List<Character> jono;
    public Stack<String> pino;

    public laskinLaunch() {
        this.jono = new ArrayList<Character>();
        this.pino = new Stack<>();
    }


    public static void main(String[] args) {
        laskinLaunch launch = new laskinLaunch();
        System.out.println("---- TIETEELLINEN LASKIN v.0.5 ----");
        System.out.println("Kirjoita laskutoimitus.\nVoit" +
                " valita operaattorit [+ - / *].\n" +
                "Syötä laskutoimitus muodossa <A*B> ja käytä vain kahta operandia.");
        String syote = launch.lueSyote();

        System.out.println("Tulos: " + syote);

    }

    public String lueSyote(){
        Scanner lukija = new Scanner(System.in);
        String infix = lukija.nextLine();
        String postfix = kasitteleInfix(infix);
        return postfix;

    }

    public String kasitteleInfix(String infix){

        for(int i = 0; i < infix.length(); i++){
            char merkki = infix.charAt(i);
            Boolean luku = Character.isDigit(merkki);

            if(luku){
                this.jono.add(merkki);
            } else {
                this.pino.push(String.valueOf(merkki));
            }
        }

        jono.add(pino.pop().charAt(0));


        StringBuffer puhveri = new StringBuffer();

        for (Character c : jono) {
            puhveri.append(c);
        }

        String tulos = kasittelePostfix(puhveri.toString());

        return tulos;
    }


    public String kasittelePostfix(String postfix){
        for(int i = 0; i < postfix.length(); i++){
            char merkki = postfix.charAt(i);
            Boolean luku = Character.isDigit(merkki);
            System.out.println(merkki + " onko luku? " + luku);
            if(luku){
                this.pino.add(String.valueOf(merkki));
            } else {
                char operaattori = merkki;
                int toinenOperandi = Integer.valueOf(this.pino.pop());
                int ensimmäinenOperandi = Integer.valueOf(this.pino.pop());
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
