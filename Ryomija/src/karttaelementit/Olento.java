package karttaelementit;

// yliluokka jolle pohjautuu sekä pelaaja että hirviot

public class Olento {
    private int x;
    private int y;
    private char symboli;
    
    public Olento(int x, int y, char symboli) {
        this.x = x;
        this.y = y;
        this.symboli = symboli;
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
    
    public void liikuta(int dX, int dY) {
        this.x += dX;
        this.y += dY;
    }
}
