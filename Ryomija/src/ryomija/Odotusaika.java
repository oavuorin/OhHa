package ryomija;

/**
 * Odotusaika-luokkaa käytetään pelaajahahmon lepoajan mittaamiseen
 * 
 * @author Otto Vuorinen
 */
public class Odotusaika {
    private int vuorojaOdotettu;
    private int raja;
    
    public Odotusaika() {
        this.raja = 5;
        this.vuorojaOdotettu = 0;
    }
    
    public Odotusaika(int raja) {
        this.raja = raja;
        this.vuorojaOdotettu = 0;
    }
    
    public int getOdotettuAika() {
        return this.vuorojaOdotettu;
    }
    
    public int getRaja() {
        return this.raja;
    }
    
    /**
     * Metodi palauttaa true kun vuoroja on odotettu tarpeeksi ja nollautuu tämän jälkeen.
     * 
     */
    public boolean odota() {
        this.vuorojaOdotettu++;
        if (this.vuorojaOdotettu >= raja) {
            this.vuorojaOdotettu = 0;
            return true;
        }
        return false;
    }
}
