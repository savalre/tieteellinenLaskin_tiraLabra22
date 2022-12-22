import java.util.HashMap;

public class Muuttujat {
    public HashMap<String, String> muuttujat;

    public Muuttujat(){
        this.muuttujat = new HashMap<>();
    }

    public String getArvo(String avain) {
        return muuttujat.get(avain);
    }

    public boolean isEmpty() {
        return muuttujat.isEmpty();
    }

    public void poista(String nimi) {
        this.muuttujat.remove(nimi);
    }

    public void lisaa(String nimi, String arvo) {
        if(nimi.isEmpty() || arvo.isEmpty()){
            System.out.println("Tarkista muuttuja!\n");
        } else{
            this.muuttujat.put(nimi,arvo);
        }
    }

    @Override
    public String toString(){
        String lista = muuttujat.toString();
        return lista;
    }
}
