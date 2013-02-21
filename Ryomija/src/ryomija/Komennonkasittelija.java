package ryomija;

import java.util.Scanner;
import ryomija.Peli;
import ryomija.Inventaario;

/**Luokka ottaa vastaan pelaajan komennot ja pistää ne eteenpäin Peli-luokalle.
 * 
 * @author Otto Vuorinen
 */
public class Komennonkasittelija {
    private Scanner lukija;
    private Peli peli;
    
    public Komennonkasittelija(Peli peli) {
        this.lukija = new Scanner(System.in);
        this.peli = peli;
    }
    
    /**Tätä metodia käytetään, mikäli peli on "normaalissa" tilassa (ei kuollut, voittanut tai inventaariossa).
     * 
     * @param komento 
     */
    public void otaKomentoNormaalitilassa(String komento) {
        if (komento.equals("w")) {
            this.peli.liikutaHahmoa(0, -1, this.peli.getPelaaja());
        }
        else if (komento.equals("s")) {
            this.peli.liikutaHahmoa(0, 1, this.peli.getPelaaja());
        }
        else if (komento.equals("a")) {
            this.peli.liikutaHahmoa(-1, 0, this.peli.getPelaaja());
        }
        else if (komento.equals("d")) {
            this.peli.liikutaHahmoa(1, 0, this.peli.getPelaaja());
        }
        else if (komento.equals(".")) {
            this.peli.lepaaHetki();
        }
        else if (komento.equals("i")) {
            this.peli.setPelitila(2);
        }
        else if (komento.equals(",")) {
            this.peli.poimi();
        }
    }
    
    /**Tätä komentoa käytetään, mikäli peli on Inventaario-tilassa.
     * 
     * @param komento 
     */
    public void otaKomentoInventaariossa(String komento) {
        if (onkoNumero(komento)) {
            int kaytettava = Integer.parseInt(komento);
            if (kaytettava > 0 && kaytettava < 10 && kaytettava <= this.peli.getInventaario().getEsineet().size()) {
                this.peli.getInventaario().kaytaEsine(kaytettava);
            }
        }
        if (this.peli.getPelitila() != 3) {
            this.peli.setPelitila(1);
        }
    }
    
    /**Metodilla tarkistetaan, onko annettu komento numero.
     * 
     */
    public boolean onkoNumero(String komento) {
        try {
            Integer.parseInt(komento);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
