package esineet;

import ryomija.Peli;

public class Panssarilevy implements Esine{
    private char symboli;
    private String nimi;
    private Peli peli;
    
    public Panssarilevy(Peli peli) {
        this.symboli = '=';
        this.nimi = "Panssarilevy";
        this.peli = peli;
    }
    
    @Override
    public void kayta() {
        this.peli.lisaaViesteihin("Asetit panssarilevyn päällesi ja kestät nyt hieman enemmän kurmotusta.");
        this.peli.getPelaaja().getKyvyt().muutaMaxHP(5);
    }
    
    @Override
    public char getSymboli() {
        return this.symboli;
    }
    
    @Override
    public String toString() {
        return this.nimi;
    }
}
