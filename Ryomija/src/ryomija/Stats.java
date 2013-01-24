package ryomija;

//luokka pitaa sisallaan olennon numeeriset ominaisuudet
public class Stats {
    int HP;
    int voima;
    
    public Stats(int HP, int voima) {
        this.HP = HP;
        this.voima = voima;
    }
    
    public int getHP() {
        return this.HP;
    }
    
    public int getVoima() {
        return this.voima;
    }
    
    public void muutaHP(int muutos) {
        this.HP += muutos;
    }
}