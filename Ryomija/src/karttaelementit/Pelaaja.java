package karttaelementit;

import ryomija.Stats;

/**
 * Pelaaja on pelaajan ohjaama hahmo pelissä.
 * 
 * @author Otto Vuorinen
 */
public class Pelaaja extends Olento {
    
    /**Kokemus-muuttujan toiminnallisuus jäi lopulta pelissä toteuttamatta.
     * 
     */
    private int kokemus;
    
    public Pelaaja(int x, int y, char symboli) {
        super(x, y, symboli, new Stats(10, 4));
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
