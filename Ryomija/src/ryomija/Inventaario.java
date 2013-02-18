package ryomija;

import esineet.Esine;
import java.util.ArrayList;

public class Inventaario {
    private ArrayList<Esine> esineet;
    
    public Inventaario() {
        this.esineet = new ArrayList<Esine>();
    }
    
    public ArrayList getEsineet() {
        return this.esineet;
    }
    
    public void lisaaEsine(Esine esine) {
        this.esineet.add(esine);
    }
    
    public void kaytaEsine(int jarjestysnro) {
        this.esineet.get(jarjestysnro - 1).kayta();
    }
    
    @Override
    public String toString() {
        String palautus = "";
        int jarjestysnro = 1;
        for (Esine esine : esineet) {
            palautus += jarjestysnro + ". " + esine.toString() + "\n";
            jarjestysnro++;
        }
        return palautus;
    }
}