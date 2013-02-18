package ryomija;

/**
 * Stats-luokka pitää sisällään olentojen numeeriset arvot.
 * 
 * @author Otto Vuorinen
 */
public class Stats {
    int HP;
    int maxHP;
    int voima;
    
    public Stats(int HP, int voima) {
        this.HP = HP;
        this.maxHP = HP;
        this.voima = voima;
    }
    
    public int getHP() {
        return this.HP;
    }
    
    public int getVoima() {
        return this.voima;
    }
    
    public int getMaxHP() {
        return this.maxHP;
    }
    
    public void muutaHP(int muutos) {
        this.HP += muutos;
        if (this.HP > this.maxHP) {
            this.HP = this.maxHP;
        }
    }
    
    public void muutaVoima(int muutos) {
        this.voima += muutos;
    }
}
