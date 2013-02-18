package esineet;

import ryomija.Peli;

public class Parannusjuoma implements Esine {
    private char symboli;
    private String nimi;
    public Peli peli;
    
    public Parannusjuoma(Peli peli) {
        this.symboli = 'i';
        this.nimi = "Parannusjuoma";
        this.peli = peli;
    }
    
    @Override
    public void kayta() {
        this.peli.getPelaaja().getKyvyt().muutaHP(5);
    }
    
    @Override
    public String toString() {
        return this.nimi;
    }
}
