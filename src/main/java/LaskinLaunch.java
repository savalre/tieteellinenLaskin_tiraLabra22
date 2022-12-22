import java.util.*;

public class LaskinLaunch {
    /**
     *
     */
    public SyotteenKasittelija kasittelija;
    public static Muuttujat muuttujat;

    public LaskinLaunch() {
        this.kasittelija = new SyotteenKasittelija();
        this.muuttujat = new Muuttujat();
    }

    /**
     * @param args
     */

    public static void main(String[] args) {
        LaskinLaunch launch = new LaskinLaunch();
        System.out.println("---- TIETEELLINEN LASKIN v.0.5 ----");
        while (true) {
            System.out.println("Komennot:\n" +
                    "<1> Kirjoita laskutoimitus\n" +
                    "<2> Lisää muuttuja\n" +
                    "<3> Poista muuttuja\n" +
                    "<0> Lopeta ohjelma");
            System.out.println("--------------------------------");

            if(!(muuttujat.isEmpty())){
                System.out.println("Tallennetut muuttujat :");
                System.out.println(muuttujat.toString());
                System.out.println("--------------------------------");
            }
            String syote = launch.lueSyote();
            if (syote.equals("loppu")) {
                System.out.println("Näkemiin!");
                break;
            }
            System.out.println(syote);
        }

    }

    /**
     * @return
     */

    public String lueSyote() {
        Scanner lukija = new Scanner(System.in);
        String komento = lukija.nextLine();

        if (komento.equals("0")) {
            return "loppu";
        }

        if(komento.equals("1")){
           System.out.println("Syötä laskutoimitus muodossa <A*B>. Voit käytää vain positiivisia kokonaislukuja. Operaattoreita [+ - / * ( )] voi käyttää vapaasti.\n" +
           "Valmiit funktiot: sqrt(arvo), sin(arvo), cos(arvo), tan(arvo), min(arvo,arvo), max(arvo,arvo)\n");
           komento = lukija.nextLine();
           return kasittelija.infixPostfixiksi(komento);
        }

        if(komento.equals("2")){
            System.out.println("Syötä muuttujan nimi: ");
            String nimi = lukija.nextLine();
            System.out.println("Syötä arvo: ");
            String arvo = lukija.nextLine();
            muuttujat.lisaa(nimi,arvo);
        }

        if(komento.equals("3")){
            System.out.println("Mikä muuttuja poistetaan: ");
            String nimi = lukija.nextLine();
            muuttujat.poista(nimi);
        }

        return "";

    }

}
