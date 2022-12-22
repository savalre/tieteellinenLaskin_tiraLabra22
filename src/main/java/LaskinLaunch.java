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
        System.out.println("Kirjoita laskutoimitus.\nVoit" +
                " valita operaattorit [+ - / * ( )].\n" +
                "Syötä laskutoimitus muodossa <A*B>. Voit käytää vain kahta operandia (lukuja 0-9). Operaattoreita voi käyttää vapaasti.\n" +
                "Lopeta ohjelma syöttämällä 0.");
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
        String infix = lukija.nextLine();
        if (infix.equals("0")) {
            return "loppu";
        }
        return kasittelija.infixPostfixiksi(infix);

    }

}
