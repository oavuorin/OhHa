package karttaelementit;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Kartta pitää sisällään ruudut, joista pelialue koostuu
 * 
 * @author Otto Vuorinen
 */
public class Kartta {
    private Map<Integer, List<Ruutu>> kartta;
    private int leveys;
    private int korkeus;
    
    public Kartta(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        
        this.kartta = new HashMap<Integer, List<Ruutu>>();
        for (int y = 0; y < this.korkeus; y++) {
            List rivi = new ArrayList<Ruutu>();
            for (int x = 0; x < this.leveys; x++) {
                rivi.add(new Ruutu(false));
            }
            this.kartta.put(y, rivi);
        }
    }
    
    public int getLeveys() {
        return this.leveys;
    }
    
    public int getKorkeus() {
        return this.korkeus;
    }
    
    /**Palauttaa listan kaikista hirvioista kartassa.
     * 
     * @return List hirvioista
     */
    public List getHirviot() {
        List hirviolista = new ArrayList<Hirvio>();
        
        for (int y = 0; y < this.korkeus; y++) {
            for (int x = 0; x < this.leveys; x++) {
                if (etsiRuutu(x, y).getOlento() != null) {
                    if (etsiRuutu(x, y).getOlento() instanceof Hirvio) {
                        hirviolista.add(etsiRuutu(x, y).getOlento());
                    }
                }
            }
        }
        
        return hirviolista;
    }
    
    /**Metodi tarkistaa, onko haluttu sijainti pelialueen ulkopuolella.
     * 
     * @param x sijainnin x-arvo
     * @param y sijainnin y-arvo
     * @return true, jos on ulkopuolella, muuten false
     */
    public boolean kartanUlkopuolella(int x, int y) {
        if (x < 0 || x > this.leveys - 1 || y < 0 || y > this.korkeus) {
            return true;
        }
        return false;
    }

    /**Metodi etsii parametreissa saamastaan sijainnista ruudun.
     * 
     * @param x Halutun ruudun x-arvo
     * @param y Halutun ruudun y-arvo
     * @return Halutussa kohdassa oleva Ruutu-olio. Palauttaa null, jos ruutua ei halutusta kohdasta löydy.
     */
    public Ruutu etsiRuutu(int x, int y) {
        //jokin fiksumpi tapa ruudun hakemiseen?
        if (kartanUlkopuolella(x, y)) {
            return null;
        }
        List verrattavaRivi = this.kartta.get(y);
        
        Object ruutu = verrattavaRivi.get(x);
        Ruutu verrattavaRuutu = new Ruutu(false);
        
        if (verrattavaRuutu.getClass() != ruutu.getClass()) {
            return null;
        }
        
        Ruutu oikeaRuutu = (Ruutu) ruutu;
        return oikeaRuutu;
    }
}
