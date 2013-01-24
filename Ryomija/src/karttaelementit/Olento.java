package karttaelementit;

// yliluokka jolle pohjautuu sekä pelaaja että hirviot

import ryomija.Stats;

public class Olento {
    private int x;
    private int y;
    private char symboli;
    private Stats kyvyt;
    
    public Olento(int x, int y, char symboli) {
        this.x = x;
        this.y = y;
        this.symboli = symboli;
    }
    
    public Olento(int x, int y, char symboli, Stats kyvyt) {
        this.x = x;
        this.y = y;
        this.symboli = symboli;
        this.kyvyt = kyvyt;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public char getSymboli() {
        return this.symboli;
    }
    
    public Stats getKyvyt() {
        return this.kyvyt;
    }
    
    public void liikuta(int dX, int dY) {
        this.x += dX;
        this.y += dY;
    }
}
