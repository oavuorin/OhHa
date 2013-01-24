package ryomija;

import karttaelementit.*;
import java.util.Random;

public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    private Random noppa;
    
    public Peli() {
        this.noppa = new Random();
    }
    
    public void aloita() {
        System.out.println("Seikkailu alkaa!");
        alustaPeli();
        testikavely();
    }
    
    public void alustaPeli() {
        this.kartta = new Kartta(10, 10);
        this.pelaaja = new Pelaaja(3, 3, '@', new Stats(10, 4));
    }
    
    public void piirraKartta() {
        for (int y = 0; y < this.kartta.getKorkeus(); y++) {
            for (int x = 0; x < this.kartta.getLeveys(); x++) {
                Ruutu naytettavaRuutu = this.kartta.etsiRuutu(x, y);
                System.out.print(naytettavaRuutu.naytaSisalto());
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void peliKierros() {
        while (true) {
            piirraKartta();
            //otaKomento();
        }
    }
    
    //jakamista toisiin metodeihin
    public void liikutaHahmoa(int dX, int dY, Olento olento) {
        if (olento.getX() + dX < 0 || olento.getX() + dX > this.kartta.getLeveys() ||
            olento.getY() + dY < 0 || olento.getY() + dY > this.kartta.getKorkeus()) {
            return;
        }
        // hahmo ei saa liikkua yli ruudun + jonkinlainen virheilmoitus kun ei voi liikkua + mita kay kun tormaa hirvioon
        if (this.kartta.etsiRuutu(olento.getX() + dX, olento.getY() + dY).getOlento() != null) {
            Olento vihollinen = this.kartta.etsiRuutu(olento.getX() + dX, olento.getY() + dY).getOlento();
            lyo(olento, vihollinen);
            if (vihollinen.getKyvyt().getHP() <= 0) {
                this.kartta.etsiRuutu(olento.getX() + dX, olento.getY() + dY).asetaOlento(null);
            }
        }
        //ylipaatansa ois kiva jos otuksen liikuttaminen hoitus vahan napparammin (ei tarttis saataa noiden asetaolento- ja liikuta-hommeleiden kaa)
        if (ruutuTyhja(this.kartta.etsiRuutu(olento.getX() + dX, olento.getY() + dY))) {
            this.kartta.etsiRuutu(olento.getX() + dX, olento.getY() + dY).asetaOlento(olento);
            this.kartta.etsiRuutu(olento.getX(), olento.getY()).asetaOlento(null);
            olento.liikuta(dX, dY);
        }
    }
    
    public boolean ruutuTyhja(Ruutu ruutu) {
        if (!ruutu.onkoSeina() && ruutu.getOlento() == null) {
            return true;
        }
        return false;
    }
    
    public void lyo(Olento hyokkaaja, Olento puolustaja) {
        if (noppa.nextInt(9) < hyokkaaja.getKyvyt().getVoima()) {
            System.out.println("Osuit!");
            puolustaja.getKyvyt().muutaHP(-3);
            if (puolustaja.getKyvyt().getHP() > 0) {
                System.out.println("Vihulla " + puolustaja.getKyvyt().getHP() + " hiparia jaljella");
            }
            else {
                System.out.println("Vihollinen kuoli!");
            }
        }
        else {
            System.out.println("Et osunut!");
        }
    }
    
    public void testikavely() {
        Ruutu alkuRuutu = this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY());
        alkuRuutu.asetaOlento(this.pelaaja);
        this.kartta.etsiRuutu(this.pelaaja.getX() + 1, this.pelaaja.getY()).muutaSeinaksi(true);
        Hirvio orkki = new Hirvio(3, 4, 'o', new Stats(10, 3));
        this.kartta.etsiRuutu(3, 4).asetaOlento(orkki);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
    }
    
    public void testitappelu() {
        Hirvio orkki = new Hirvio(3, 3, 'o', new Stats(10, 3));
        lyo(this.pelaaja, orkki);
        lyo(this.pelaaja, orkki);
        lyo(this.pelaaja, orkki);
    }
}
