package karttaelementit;

import esineet.Esine;

/**
 * Pelialue koostuu ruuduista, jotka voivat pitää sisällään tyhjää tilaa, seinän tai olennon.
 * 
 * @author Otto Vuorinen
 */
public class Ruutu {
    boolean seina;
    boolean nahty;
    Olento olento;
    Esine esine;
    
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
    
    public void poistaOlento() {
        this.olento = null;
    }
    
    public Olento getOlento() {
        return this.olento;
    }
    
    public void asetaEsine(Esine esine) {
        this.esine = esine;
    }
    
    public void poistaEsine() {
        this.esine = null;
    }
    
    public Esine getEsine() {
        return this.esine;
    }
    
    /**Palauttaa ruudussa olevan sisällön symbolin.
     * 
     * @return Ruudussa olevan olennon/seinän/tyhjän symboli
     */
    public char naytaSisalto() {
        if (seina) {
            return '#';
        }
        if (this.olento != null) {
            return this.olento.getSymboli();
        }
        else if (this.esine != null) {
            return this.esine.getSymboli();
        }
        return '.';
    }
}
