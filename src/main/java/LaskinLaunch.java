import java.util.*;

public class LaskinLaunch {
    /**
     *
     */
    public SyotteenKasittelija kasittelija;

    public LaskinLaunch() {
        this.kasittelija = new SyotteenKasittelija();
    }

    /**
     * @param args
     */

    public static void main(String[] args) {
        LaskinLaunch launch = new LaskinLaunch();
        System.out.println("---- TIETEELLINEN LASKIN v.0.5 ----");
        System.out.println("Komennot:\n" +
                "<1>Kirjoita laskutoimitus\n" +
                "<2> Lisää muuttuja\n" +
                "<3> Poista muuttuja\n" +
                "<0>Lopeta ohjelma");
        while (true) {
            String syote = launch.lueSyote();
            if (syote.equals("loppu")) {
                System.out.println("Näkemiin!");
                break;
            }
            System.out.println("Tulos: " + syote);
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
           System.out.println("Syötä laskutoimitus muodossa <A*B>. Voit käytää vain positiivisia kokonaislukuja. Operaattoreita [+ - / * ( )] voi käyttää vapaasti.\n" );
           komento = lukija.nextLine();
        }

        if(komento.equals("3")){

        }


        return kasittelija.infixPostfixiksi(komento);

    }

}
