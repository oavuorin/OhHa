package karttaelementit;

import ryomija.Stats;

/**
 * Hirvio-luokalla luodaan tietokoneen ohjastamat pelihahmot
 * 
 * @author ghaas
 */
public class Hirvio extends Olento {
    
    public Hirvio(int x, int y, char symboli) {
        super(x, y, symboli);
    }
    
    public Hirvio(int x, int y, char symboli, Stats kyvyt) {
        super(x, y, symboli, kyvyt);
    }
    
}
