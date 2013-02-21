package karttaelementit;

import java.io.File;
import java.util.Scanner;
import ryomija.Stats;
import ryomija.Peli;
import esineet.*;

/**Kartanrakentaja huolehtii siitä, että pelikartalle tulee oikeat jutut oikeisiin paikkoihin.
 * 
 * @author ghaas
 */
public class Kartanrakentaja {
    
    private Peli peli;
    
    public Kartanrakentaja(Peli peli) {
        this.peli = peli;
    }
    
    /**rakennaKartta-metodilla avataan tiedosto jonka pohjalta kartta luodaan ja kutsutaan muut rakennusmetodit.
     * 
     * @return Valmis kartta, johon on lisätty Hirviot ja Esineet.
     */
    public Kartta rakennaKartta() {
        File tiedosto = new File("tasot/taso.txt");
        
        try {
            int leveys = lueKartanLeveys(tiedosto);
            int korkeus = lueKartanKorkeus(tiedosto);
            Kartta kartta = new Kartta(leveys, korkeus);
            lisaaSeinatJaOlennot(kartta, tiedosto);
            return kartta;
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui. Tiedostoa tasot/taso.txt ei löydy");
            return null;
        }
    }
    
    /**Metodi lukee tiedostosta kartan leveyden.
     * 
     * @param tiedosto Karttatiedosto
     * @return kartan leveys
     * @throws Exception Heitetään, mikäli tiedostoa ei löydy
     */
    public int lueKartanLeveys(File tiedosto) throws Exception {
        Scanner lukija = new Scanner(tiedosto);
        int leveys = lukija.nextLine().length();
        lukija.close();
        return leveys;
    }
    
    /**Metodi lukee tiedostosta kartan korkeuden.
     * 
     * @param tiedosto Karttatiedosto
     * @return kartan korkeus
     * @throws Exception Heitetään, mikäli tiedostoa ei löydy
     */
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
    
    /**Metodi lukee karttatiedostoa merkki kerrallaan ja asettaa merkkien pohjalta ruutujen sisällön sopivaksi.
     * 
     * @param kartta kartta, johon muutokset päivitetään
     * @param tiedosto tiedosto, jonka pohjalta karttaa muokataan
     * @throws Exception heitetään, mikäli tiedostoa ei löydy
     */
    public void lisaaSeinatJaOlennot(Kartta kartta, File tiedosto) throws Exception {
        Scanner lukija = new Scanner(tiedosto);
        
        int rivinro = 0;
        
        while (lukija.hasNext()) {
            String rivi = lukija.nextLine();
            for (int merkki = 0; merkki < rivi.length(); merkki++) {
                if (rivi.charAt(merkki) == '#') {
                    kartta.etsiRuutu(merkki, rivinro).muutaSeinaksi(true);
                }
                if (rivi.charAt(merkki) != '#' && rivi.charAt(merkki) != '.') {
                    lisaaOlentoTaiEsineRuutuun(kartta.etsiRuutu(merkki, rivinro), rivi.charAt(merkki), merkki, rivinro);
                }
            }
            rivinro++;
        }
        
        lukija.close();
    }
    
    /**Tämä lisää merkin pohjalta ruutuun tietyn olennon tai esineen.
     * 
     * @param ruutu Ruutu, johon olento tai esine lisätään
     * @param merkki char, jonka pohjalta asetettava olento/esine päätetään
     * @param x ruudun x-arvo
     * @param y ruudun y-arvo
     */
    public void lisaaOlentoTaiEsineRuutuun(Ruutu ruutu, char merkki, int x, int y) {
        if (merkki == '@') {
            ruutu.asetaOlento(new Pelaaja(x, y, '@', new Stats(10, 5)));
        }
        else if (merkki == 'r') {
            ruutu.asetaOlento(new Hirvio(x, y, 'r', new Stats(3, 2), "Rotta"));
        }
        else if (merkki == 'o') {
            ruutu.asetaOlento(new Hirvio(x, y, 'o', new Stats(5, 3), "Örkki"));
        }
        else if (merkki == 'P') {
            ruutu.asetaOlento(new Hirvio(x, y, 'P', new Stats(15, 5), "Tappajapeikko"));
        }
        else if (merkki == 'Y') {
            ruutu.asetaEsine(new VoitonAmuletti(this.peli));
        }
        else if (merkki == '!') {
            ruutu.asetaEsine(new Parannusjuoma(this.peli));
        }
        else if (merkki == 'I') {
            ruutu.asetaEsine(new Voimannostopuntti(this.peli));
        }
        
        else if (merkki == '=') {
            ruutu.asetaEsine(new Panssarilevy(this.peli));
        }
    }
}
