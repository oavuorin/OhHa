package karttaelementit;

import ryomija.Stats;


public class Pelaaja extends Olento {
    public Pelaaja(int x, int y, char symboli) {
        super(x, y, symboli);
    }
    
    public Pelaaja(int x, int y, char symboli, Stats kyvyt) {
        super(x, y, symboli, kyvyt);
    }
}
