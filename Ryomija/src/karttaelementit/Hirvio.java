package karttaelementit;

import ryomija.Stats;

/**
 * Hirvio-luokalla luodaan tietokoneen ohjastamat pelihahmot
 * 
 * @author ghaas
 */
public class Hirvio extends Olento {
    
    private String nimi;
    
    public Hirvio(int x, int y, char symboli, Stats kyvyt) {
        super(x, y, symboli, kyvyt);
        this.nimi = "Hirviö";
    }
    
    public Hirvio(int x, int y, char symboli, Stats kyvyt, String nimi) {
        super(x, y, symboli, kyvyt);
        this.nimi = nimi;
    }
    
    public String getNimi() {
        return this.nimi;
    }
}
