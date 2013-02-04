package ryomija;

public class Odotusaika {
    private int vuorojaOdotettu;
    
    public Odotusaika() {
        this.vuorojaOdotettu = 0;
    }
    
    public boolean lepaa() {
        this.vuorojaOdotettu++;
        if (this.vuorojaOdotettu >= 5) {
            this.vuorojaOdotettu = 0;
            return true;
        }
        return false;
    }
}
