package kayttoliittyma;

import java.util.Scanner;
import ryomija.Peli;

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
    
    public void otaKomento(String komento) {
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
    }
}
