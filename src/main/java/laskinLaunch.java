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

        System.out.println("Postfix on " + syote);

    }

    public String lueSyote(){
        Scanner lukija = new Scanner(System.in);
        String infix = lukija.nextLine();
        String postfix = kasitteleInfix(infix);
        return postfix;

    }

    public String kasitteleInfix(String infix){
        //List<Character> jono = new ArrayList<Character>();
        for(int i = 0; i < infix.length(); i++){
            char merkki = infix.charAt(i);
            Boolean luku = Character.isDigit(merkki);
            System.out.println(merkki + " onko luku? " + luku);
            if(luku){
                this.jono.add(merkki);
            } else {
                this.pino.push(String.valueOf(merkki));
            }
        }

        return "jee";
    }


    public static void kasittelePostfix(){

    }

}
