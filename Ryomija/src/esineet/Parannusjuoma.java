package esineet;

import ryomija.Peli;

public class Parannusjuoma implements Esine {
    private char symboli;
    private String nimi;
    public Peli peli;
    
    public Parannusjuoma(Peli peli) {
        this.symboli = '!';
        this.nimi = "Parannusjuoma";
        this.peli = peli;
    }
    
    @Override
    public void kayta() {
        this.peli.getPelaaja().getKyvyt().muutaHP(5);
        this.peli.lisaaViesteihin("Joit parannusjuoman. ");
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
