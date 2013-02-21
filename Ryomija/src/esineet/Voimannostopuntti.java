package esineet;

import ryomija.Peli;

/**Voimannostopuntti kasvattaa pelaajan Voima-arvoa yhdellä.
 * 
 */
public class Voimannostopuntti implements Esine {
    private char symboli;
    private String nimi;
    private Peli peli;
    
    public Voimannostopuntti(Peli peli) {
        this.symboli = 'I';
        this.nimi = "Voimannostopuntti";
        this.peli = peli;
    }
    
    /**Käytettäessä voimannostopuntti kasvattaa pelaajan Voimaa yhdellä ja lisää asianmukaisen viestin.
     * 
     */
    @Override
    public void kayta() {
        this.peli.getPelaaja().getKyvyt().muutaVoima(1);
        this.peli.lisaaViesteihin("Nostit puntteja ja sait yhden voimapisteen lisää!");
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
