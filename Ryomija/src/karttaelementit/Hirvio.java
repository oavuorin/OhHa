package karttaelementit;

import ryomija.Stats;

public class Hirvio extends Olento {
    
    private int exp;
    
    public Hirvio(int x, int y, char symboli) {
        super(x, y, symboli);
        this.exp = 0;
    }
    
    public Hirvio(int x, int y, char symboli, Stats kyvyt) {
        super(x, y, symboli, kyvyt);
        this.exp = 0;
    }
    
    public Hirvio(int x, int y, char symboli, Stats kyvyt, int exp) {
        super(x, y, symboli, kyvyt);
        this.exp = exp;
    }
    
    public void muutaExp(int haluttuMaara) {
        this.exp = haluttuMaara;
    }
    
    public int getExp() {
        return this.exp;
    }
    
}
