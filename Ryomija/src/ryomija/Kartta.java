package ryomija;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
    
    public Ruutu etsiRuutu(int y, int x) {
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
    
    public char ruudunSisalto(int y, int x) {
        Ruutu ruutu = etsiRuutu(y, x);
        //lisää tähän virheenkäsittelyä (jos ruutu on null tms)
        if (ruutu.onkoSeina()) {
            return '#';
        }
        return '.';
    }
}
