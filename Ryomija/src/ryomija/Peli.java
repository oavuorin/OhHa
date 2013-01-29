package ryomija;

import karttaelementit.*;
import java.util.Random;
import java.util.Scanner;

public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    private Random noppa;
    private Scanner lukija;
    
    public Peli() {
        this.noppa = new Random();
        this.lukija = new Scanner(System.in);
    }
    
    public void aloita() {
        System.out.println("Seikkailu alkaa!");
        alustaPeli();
        peliKierros();
    }
    
    public void alustaPeli() {
        this.kartta = new Kartta(10, 10);
        this.pelaaja = new Pelaaja(3, 3, '@', new Stats(10, 4));
        this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).asetaOlento(this.pelaaja);
    }
    
    public void peliKierros() {
        while (true) {
            piirraKartta();
            String komento = lukija.nextLine();
            otaKomento(komento);
        }
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
    
    public void otaKomento(String komento) {
        if (komento.equals("w")) {
            liikutaHahmoa(0, -1, this.pelaaja);
        }
        else if (komento.equals("s")) {
            liikutaHahmoa(0, 1, this.pelaaja);
        }
        else if (komento.equals("a")) {
            liikutaHahmoa(-1, 0, this.pelaaja);
        }
        else if (komento.equals("d")) {
            liikutaHahmoa(1, 0, this.pelaaja);
        }
    }
    
    //jakamista toisiin metodeihin
    public void liikutaHahmoa(int dX, int dY, Olento olento) {
        if (olento.getX() + dX < 0 || olento.getX() + dX > this.kartta.getLeveys() - 1 ||
            olento.getY() + dY < 0 || olento.getY() + dY > this.kartta.getKorkeus() - 1) {
            return;
        }
        // jonkinlainen virheilmoitus kun ei voi liikkua
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
}
