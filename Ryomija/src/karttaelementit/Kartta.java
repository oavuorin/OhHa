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
    private Map kartta;
    private int leveys;
    private int korkeus;
    
    public Kartta(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        
        this.kartta = new HashMap<Integer, List<Ruutu>>();
        for (int y = 0; y < korkeus; y++) {
            List rivi = new ArrayList<Ruutu>();
            for (int x = 0; x < leveys; x++) {
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
    
    public boolean kartanUlkopuolella(int x, int y) {
        if (x < 0 || x > this.leveys - 1 || y < 0 || y > this.korkeus) {
            return true;
        }
        return false;
    }

    public Ruutu etsiRuutu(int x, int y) {
        //jokin fiksumpi tapa ruudun hakemiseen?
        if (kartanUlkopuolella(x, y)) {
            return null;
        }
        Object rivi = this.kartta.get(y);
        List verrattavaRivi = new ArrayList<Ruutu>();
        
        if (verrattavaRivi.getClass() != rivi.getClass()) {
            return null;
        }
        
        ArrayList oikeaRivi = (ArrayList) rivi;
        
        Object ruutu = oikeaRivi.get(x);
        Ruutu verrattavaRuutu = new Ruutu(false);
        
        if (verrattavaRuutu.getClass() != ruutu.getClass()) {
            return null;
        }
        
        Ruutu oikeaRuutu = (Ruutu) ruutu;
        return oikeaRuutu;
    }
}
