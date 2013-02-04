package ryomija;

import karttaelementit.*;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

//Kayttoliittymametodit yms. ovat taalla vain testaamista varten ja lopulta korvataan graafisilla versioilla.
public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    private Random noppa;
    private Scanner lukija;
    private Odotusaika odotus;
    private ArrayList<Hirvio> hirviot;
    private ArrayList<Hirvio> tuhottavat;
    
    public Peli() {
        this.noppa = new Random();
        this.lukija = new Scanner(System.in);
        this.odotus = new Odotusaika();
        this.hirviot = new ArrayList<Hirvio>();
        this.tuhottavat = new ArrayList<Hirvio>();
    }
    
    public Kartta getKartta() {
        return this.kartta;
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    public void aloita() {
        System.out.println("Seikkailu alkaa!");
        alustaPeli();
        peliKierros();
    }
    
    public void alustaPeli() {
        this.kartta = new Kartta(10, 10);
        lisaaSeinat();
        this.pelaaja = new Pelaaja(1, 1, '@', new Stats(10, 4));
        this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).asetaOlento(this.pelaaja);
        Hirvio orkki = new Hirvio(4, 1, 'o', new Stats(5, 2), 5);
        this.kartta.etsiRuutu(orkki.getX(), orkki.getY()).asetaOlento(orkki);
        hirviot.add(orkki);
    }
    
    public void lisaaSeinat() {
        for (int y = 0; y < this.kartta.getLeveys(); y++) {
            for (int x = 0; x < this.kartta.getKorkeus(); x++) {
                if (x == 0 || y == 0 || x == this.kartta.getLeveys() - 1 || y == this.kartta.getKorkeus() - 1) {
                    this.kartta.etsiRuutu(x, y).muutaSeinaksi(true);
                }
                if (x == 2 && y != 8) {
                    this.kartta.etsiRuutu(x, y).muutaSeinaksi(true);
                }
            }
        }
    }
    
    public void peliKierros() {
        while (true) {
            piirraKartta();
            String komento = lukija.nextLine();
            otaKomento(komento);
            tuhoaHirviot();
            liikutaHirviot();
        }
    }
    
    public void piirraKartta() {
        for (int y = 0; y < this.kartta.getKorkeus(); y++) {
            for (int x = 0; x < this.kartta.getLeveys(); x++) {
                Ruutu naytettavaRuutu = this.kartta.etsiRuutu(x, y);
                //if (nakokentassa(x, y)) {
                    System.out.print(naytettavaRuutu.naytaSisalto());
                //}
                //else {
                //    System.out.print("~");
                //}
            }
            System.out.println();
        }
        System.out.println("HP: " + this.pelaaja.getKyvyt().getHP() + " Voima: " + this.pelaaja.getKyvyt().getVoima() + " Exp: " + this.pelaaja.getKokemus());
    }
    
    public boolean nakokentassa(int x, int y) {
        int dx = x - this.pelaaja.getX();
        int dy = y - this.pelaaja.getY();
        if (dx < 0) {
            dx *= -1;
        }
        if (dy < 0) {
            dy *= -1;
        }
        if (dx + dy > 3) {
            return false;
        }
        return true;
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
        else if (komento.equals(".")) {
            if (this.odotus.lepaa()) {
                this.pelaaja.getKyvyt().muutaHP(1);
            }
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
        if (hyokkaaja instanceof Hirvio && puolustaja instanceof Hirvio) {
            return;
        }
        if (noppa.nextInt(9) < hyokkaaja.getKyvyt().getVoima()) {
            osuma(puolustaja);
        }
        else {
            huti(puolustaja);
        }
        System.out.println();
    }
    
    public void osuma(Olento olento) {
        olento.getKyvyt().muutaHP(-3);
        if (olento.getKyvyt().getHP() > 0) {
            if (olento instanceof Hirvio) {
                System.out.print("Osuit! ");
                System.out.print("Vihulla " + olento.getKyvyt().getHP() + " hiparia jaljella. ");
            }
            else {
                System.out.print("Vihu osuu sinuun! Sinulla on " + olento.getKyvyt().getHP() + " hiparia jaljella. ");
            }
        }
        else {
            tappo(olento);
        }
    }
    
    public void huti(Olento olento) {
        if (olento instanceof Pelaaja) {
            System.out.print("Vihollinen löi hudin! ");
        }
        else {
            System.out.print("Et osunut! ");
        }
    }
    
    public void tappo(Olento olento) {
        if (olento instanceof Hirvio) {
            System.out.print("Vihollinen kuoli!");
            Hirvio monseri = (Hirvio)olento;
            this.pelaaja.lisaaKokemusta(monseri.getExp());
            this.kartta.etsiRuutu(monseri.getX(), monseri.getY()).asetaOlento(null);
            this.tuhottavat.add(monseri);
        }
        else if (olento instanceof Pelaaja) {
            //havio();
        }
    }
    
    public void liikutaHirviot() {
        for (Hirvio hirvio : this.hirviot) {
            if (nakokentassa(hirvio.getX(), hirvio.getY())) {
                if (hirvio.getX() < this.pelaaja.getX()) {
                    liikutaHahmoa(1, 0, hirvio);
                }
                else if (hirvio.getX() > this.pelaaja.getX()) {
                    liikutaHahmoa(-1, 0, hirvio);
                }
                else if (hirvio.getY() < this.pelaaja.getY()) {
                    liikutaHahmoa(0, 1, hirvio);
                }
                else if (hirvio.getY() > this.pelaaja.getY()) {
                    liikutaHahmoa(0, -1, hirvio);
                }
            }
        }
    }
    
    public void tuhoaHirviot() {
        this.hirviot.removeAll(this.tuhottavat);
        tuhottavat.clear();
    }
}
