package karttaelementit;

import ryomija.Stats;


public class Pelaaja extends Olento {
    private int kokemus;
    
    public Pelaaja(int x, int y, char symboli) {
        super(x, y, symboli);
        this.kokemus = 0;
    }
    
    public Pelaaja(int x, int y, char symboli, Stats kyvyt) {
        super(x, y, symboli, kyvyt);
        this.kokemus = 0;
    }
    
    public void lisaaKokemusta(int muutos) {
        this.kokemus += muutos;
    }
    
    public int getKokemus() {
        return this.kokemus;
    }
}
