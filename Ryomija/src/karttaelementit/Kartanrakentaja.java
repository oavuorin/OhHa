package karttaelementit;

import java.io.File;
import java.util.Scanner;

/**Kartanrakentaja huolehtii siitä, että pelikartalle tulee oikeat jutut oikeisiin paikkoihin.
 * 
 * @author ghaas
 */
public class Kartanrakentaja {
    
    public Kartanrakentaja() {
    }
    
    
    public void lisaaSeinat(Kartta kartta) {
        File tiedosto = new File("tasot/taso.txt");
        Scanner lukija = null;
        
        try {
            lukija = new Scanner(tiedosto);
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui, haista vittu");
            return;
        }
        
        int rivinro = 0;
        
        while (lukija.hasNext()) {
            String rivi = lukija.nextLine();
            for (int merkki = 0; merkki < rivi.length(); merkki++) {
                if (rivi.charAt(merkki) == '#') {
                    kartta.etsiRuutu(merkki, rivinro).muutaSeinaksi(true);
                }
            }
            rivinro++;
        }
        
        lukija.close();
    }
    
    public void lisaaOlennot() {
    }
}
