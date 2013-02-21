package esineet;

import ryomija.Peli;

/**VoitonAmuletin käytettyään pelaaja voittaa pelin.
 * 
 */
public class VoitonAmuletti implements Esine {
    private char symboli;
    private String nimi;
    private Peli peli;
    
    public VoitonAmuletti(Peli peli) {
        this.symboli = 'Y';
        this.nimi = "Mystinen amuletti";
        this.peli = peli;
    }
    
    /**Kun pelaaja käyttää VoitonAmuletin, lisätään sopiva viesti ja asetetaan peli voitetuksi.
     * 
     */
    @Override
    public void kayta() {
        this.peli.lisaaViesteihin("Laitoit Zkklunrmin pyhän amuletin kaulaasi, muutuit jumalaksi ja pääsit pois hirveästä luolastosta! ");
        this.peli.voitto();
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
