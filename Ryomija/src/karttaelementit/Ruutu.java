package karttaelementit;

public class Ruutu {
    boolean seina;
    Olento olento;
    
    public Ruutu(boolean seina) {
        this.seina = seina;
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
