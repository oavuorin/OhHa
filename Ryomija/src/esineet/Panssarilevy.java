package esineet;

import ryomija.Peli;

/**Panssarilevy-esineellä voi kasvattaa pelaajan maksimi-HP:n arvoa.
 * 
 * @author ghaassy
 */
public class Panssarilevy implements Esine{
    private char symboli;
    private String nimi;
    private Peli peli;
    
    public Panssarilevy(Peli peli) {
        this.symboli = '=';
        this.nimi = "Panssarilevy";
        this.peli = peli;
    }
    
    /**Kun panssarilevy käytetään, se nostaa pelaajan maxHP:ta viidellä ja lisää asianmukaisen viestin.
     * 
     */
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
