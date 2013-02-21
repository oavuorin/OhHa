package ryomija;

import esineet.Esine;
import java.util.ArrayList;

/**Pitää sisällään listan esineistä, jotka on pelaajan "repussa" ja joita pelaaja voi käyttää.
 * 
 * @author ghaassy
 */
public class Inventaario {
    private ArrayList<Esine> esineet;
    
    public Inventaario() {
        this.esineet = new ArrayList<Esine>();
    }
    
    public ArrayList getEsineet() {
        return this.esineet;
    }
    
    /**Mikäli pelaajan reppu ei ole täynnä, lisätään sinne uusi esine.
     * 
     * @param esine lisättävä esine
     * @return true, mikäli esine mahtui reppuun
     */
    public boolean lisaaEsine(Esine esine) {
        if (this.esineet.size() >= 9) {
            return false;
        }
        this.esineet.add(esine);
        return true;
    }
    
    /**Käyttää esineen inventaariosta.
     * 
     * @param jarjestysnro monesko esine listalla halutaan käyttää
     */
    public void kaytaEsine(int jarjestysnro) {
        this.esineet.get(jarjestysnro - 1).kayta();
        this.esineet.remove(jarjestysnro - 1);
    }
    
    @Override
    public String toString() {
        if (this.esineet.isEmpty()) {
            return "Sinulla ei ole esineitä inventaariossasi.";
        }
        String palautus = "";
        int jarjestysnro = 1;
        for (Esine esine : esineet) {
            palautus += jarjestysnro + ". " + esine.toString() + "\n";
            jarjestysnro++;
        }
        return palautus;
    }
}
