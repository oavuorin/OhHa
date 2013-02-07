package kayttoliittyma;

import java.util.Scanner;
import ryomija.Peli;

/**Teksikayttoliittyma-luokka ottaa vastaan pelaajan komennot ja pistää ne eteenpäin Peli-luokalle.
 * 
 * Korvattu graafisella versiolla.
 * 
 * @author Otto Vuorinen
 */
public class Tekstikayttoliittyma {
    private Scanner lukija;
    private Peli peli;
    
    public Tekstikayttoliittyma(Peli peli) {
        this.lukija = new Scanner(System.in);
        this.peli = peli;
    }
    
    public void otaKomento() {
        while (true) {
            String komento = lukija.nextLine();
            if (komento.equals("w")) {
                this.peli.liikutaHahmoa(0, -1, this.peli.getPelaaja());
                break;
            }
            else if (komento.equals("s")) {
                this.peli.liikutaHahmoa(0, 1, this.peli.getPelaaja());
                break;
            }
            else if (komento.equals("a")) {
                this.peli.liikutaHahmoa(-1, 0, this.peli.getPelaaja());
                break;
            }
            else if (komento.equals("d")) {
                this.peli.liikutaHahmoa(1, 0, this.peli.getPelaaja());
                break;
            }
            else if (komento.equals(".")) {
                this.peli.lepaaHetki();
                break;
            }
            //virheilmoitus jos huono komento (ei kuitenkaan kuluta vuoroa tms
        }
    }
}
