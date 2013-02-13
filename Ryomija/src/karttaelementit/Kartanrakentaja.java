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
    
    public Kartta rakennaKartta() {
        File tiedosto = new File("tasot/taso.txt");
        
        try {
            int leveys = lueKartanLeveys(tiedosto);
            int korkeus = lueKartanKorkeus(tiedosto);
            Kartta kartta = new Kartta(leveys, korkeus);
            lisaaSeinat(kartta, tiedosto);
            return kartta;
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui, haista vittu");
            return null;
        }
    }
    
    public int lueKartanLeveys(File tiedosto) throws Exception {
        Scanner lukija = new Scanner(tiedosto);
        int leveys = lukija.nextLine().length();
        lukija.close();
        return leveys;
    }
    
    public int lueKartanKorkeus(File tiedosto) throws Exception {
        Scanner lukija = new Scanner(tiedosto);
        int korkeus = 0;
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            korkeus++;
        }
        lukija.close();
        return korkeus;
    }
    
    public void lisaaSeinat(Kartta kartta, File tiedosto) throws Exception {
        Scanner lukija = new Scanner(tiedosto);
        
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
