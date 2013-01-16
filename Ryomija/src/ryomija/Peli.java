package ryomija;

public class Peli {
    private Kartta kartta;
    
    public void aloita() {
        System.out.println("Seikkailu alkaa!");
        luoKartta();
        piirraKartta();
    }
    
    public void luoKartta() {
        this.kartta = new Kartta(10, 10);
    }
    
    public void piirraKartta() {
        for (int y = 0; y < this.kartta.getKorkeus(); y++) {
            for (int x = 0; x < this.kartta.getLeveys(); x++) {
                System.out.print(this.kartta.ruudunSisalto(y, x));
            }
            System.out.println();
        }
    }
}
