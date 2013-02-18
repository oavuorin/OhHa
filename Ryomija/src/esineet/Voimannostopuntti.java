package esineet;

import ryomija.Peli;

public class Voimannostopuntti implements Esine {
    private char symboli;
    private String nimi;
    private Peli peli;
    
    public Voimannostopuntti(Peli peli) {
        this.symboli = 'I';
        this.nimi = "Voimannostopuntti";
        this.peli = peli;
    }
    
    @Override
    public void kayta() {
        this.peli.getPelaaja().getKyvyt().muutaVoima(2);
    }
    
    @Override
    public String toString() {
        return this.nimi;
    }
    
    @Override
    public char getSymboli() {
        return this.symboli;
    }
}