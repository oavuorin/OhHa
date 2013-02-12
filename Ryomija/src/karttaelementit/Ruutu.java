package karttaelementit;

/**
 * Pelialue koostuu ruuduista, jotka voivat pitää sisällään tyhjää tilaa, seinän tai olennon.
 * 
 * @author Otto Vuorinen
 */
public class Ruutu {
    boolean seina;
    boolean nahty;
    Olento olento;
    
    public Ruutu(boolean seina) {
        this.seina = seina;
        this.nahty = false;
    }
    
    public boolean onkoSeina() {
        return this.seina;
    }
    
    public void muutaSeinaksi(boolean seina) {
        this.seina = seina;
    }
    
    public void asetaOlento(Olento olento) {
        this.olento = olento;
    }
    
    /**Poistaa olennon ruudusta.
     * 
     */
    public void poistaOlento() {
        this.olento = null;
    }
    
    public Olento getOlento() {
        return this.olento;
    }
    
    /**Palauttaa ruudussa olevan sisällön symbolin.
     * 
     * @return Ruudussa olevan olennon/seinän/tyhjän symboli
     */
    public char naytaSisalto() {
        if (seina) {
            return '#';
        }
        if (olento == null) {
            return '.';
        }
        return this.olento.getSymboli();
    }
}
