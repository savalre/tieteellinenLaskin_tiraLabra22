import java.util.*;

public class LaskinLaunch {
    /**
     * Laskimen käyttöliittymä
     */
    public SyotteenKasittelija kasittelija;
    public Muuttujat muuttujat;
    public String tulos;

    public LaskinLaunch() {
        this.kasittelija = new SyotteenKasittelija();
        muuttujat = new Muuttujat();
        tulos = "";
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
                    "Tallenna laskutoimituksen tulos muuttujaan kirjoittamalla laskun jälkeen save <muuttujanNimi>\n" +
                    "<2> Lisää muuttuja\n" +
                    "<3> Poista muuttuja\n" +
                    "<0> Lopeta ohjelma");
            System.out.println("--------------------------------");

            if(!(launch.muuttujat.isEmpty())){
                System.out.println("Tallennetut muuttujat :");
                System.out.println(launch.muuttujat.toString());
                System.out.println("--------------------------------");
            }
            String syote = launch.lueSyote(launch.muuttujat);

            if (syote.equals("loppu")) {
                System.out.println("Näkemiin!");
                break;
            }
            System.out.println(syote);
        }

    }

    /**
     * @return palauttaa komennosta riippuen joko syötetyn tuloksen, lopettaa ohjelman, tai tallentaa/poistaa muuttujan
     * @param muuttujat käyttäjän tallentamat muuttujat
     */

    public String lueSyote(Muuttujat muuttujat) {
        Scanner lukija = new Scanner(System.in);
        String komento = lukija.nextLine();

        if (komento.equals("0")) {
            return "loppu";
        }

        if(komento.equals("1")){
           System.out.println("Syötä laskutoimitus muodossa <A*B>. Voit käytää vain positiivisia reaalilukuja. Operaattoreita [+ - / * ( ) ^] voi käyttää vapaasti.\n" +
           "Valmiit funktiot: sqrt(arvo), sin(arvo), cos(arvo), tan(arvo)\n" +
                   "MUUTTUJAAN TALLENNUS: kirjoita laskutoimituksen jälkeen save <muuttujan nimi>, niin viimeisin laskutulos tallentuu muuttujiin");
           komento = lukija.nextLine();
           try{
               tulos = kasittelija.infixPostfixiksi(komento,muuttujat);
               return tulos;

           } catch (Exception e){
                System.out.println("VIRHE, TARKISTA LAUSEKE");
            }
        }

        if(komento.equals("2")){
            System.out.println("Syötä muuttujan nimi: ");
            String nimi = lukija.nextLine();
            System.out.println("Syötä arvo: ");
            String arvo = lukija.nextLine();
            this.muuttujat.lisaa(nimi,arvo);
        }

        if(komento.equals("3")){
            System.out.println("Mikä muuttuja poistetaan: ");
            String nimi = lukija.nextLine();
            this.muuttujat.poista(nimi);
        }

        if(komento.contains("save")){
            if(tulos.length() != 0){
                String[] muuttuja = komento.split(" ");
                if(muuttuja.length >= 2){
                    this.muuttujat.lisaa(muuttuja[1],tulos);
                }
            }else{
                    System.out.println("Ei tallennettavaa tulosta");

            }

        }

        return "";

    }

}
