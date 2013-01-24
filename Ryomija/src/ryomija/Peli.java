package ryomija;

import karttaelementit.Kartta;
import karttaelementit.Ruutu;
import karttaelementit.Pelaaja;
import karttaelementit.Hirvio;
import karttaelementit.Olento;

public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    
    public void aloita() {
        System.out.println("Seikkailu alkaa!");
        alustaPeli();
        testikierros();
    }
    
    public void alustaPeli() {
        this.kartta = new Kartta(10, 10);
        this.pelaaja = new Pelaaja(3, 3, '@');
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
    
    public void liikutaHahmoa(int dX, int dY, Olento olento) {
        // hahmo ei saa liikkua yli ruudun + jonkinlainen virheilmoitus kun ei voi liikkua + mita kay kun tormaa hirvioon
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
    
    public void testikierros() {
        Ruutu alkuRuutu = this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY());
        alkuRuutu.asetaOlento(this.pelaaja);
        this.kartta.etsiRuutu(this.pelaaja.getX() + 1, this.pelaaja.getY()).muutaSeinaksi(true);
        piirraKartta();
        liikutaHahmoa(0, 1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(0, -1, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(1, 0, this.pelaaja);
        piirraKartta();
        liikutaHahmoa(-1, 0, this.pelaaja);
        piirraKartta();
    }
}
