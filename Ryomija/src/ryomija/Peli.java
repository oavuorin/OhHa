package ryomija;

import karttaelementit.*;
import kayttoliittyma.GraafinenKayttoliittyma;
import kayttoliittyma.Komennonkasittelija;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 * Peli-luokka pyörittää ja hallinnoi peliä ja sen elementtejä.
 * 
 * @author Otto Vuorinen
 */
public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    private Random noppa;
    private Odotusaika odotus;
    private boolean kaynnissa;
    private String viestit;
    private Komennonkasittelija tekstikayttis;
    private GraafinenKayttoliittyma graafKayttis;
    private ArrayList<Hirvio> hirviot;
    private ArrayList<Hirvio> tuhottavat;
    
    public Peli() {
        this.kaynnissa = true;
        this.viestit = "Tervetuloa peliin! Yritä selvitä hengissä. ";
        this.noppa = new Random();
        this.odotus = new Odotusaika();
        this.hirviot = new ArrayList<Hirvio>();
        this.tuhottavat = new ArrayList<Hirvio>();
        this.tekstikayttis = new Komennonkasittelija(this);
    }
    
    public Kartta getKartta() {
        return this.kartta;
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    public Odotusaika getOdotus() {
        return this.odotus;
    }
    
    public void setViestit(String muutos) {
        this.viestit = muutos;
    }
    
    public void aloita() {
        alustaPeli();
        SwingUtilities.invokeLater(graafKayttis);
        //peliKierros();
    }
    
    public void alustaPeli() {
        this.kaynnissa = true;
        this.kartta = new Kartta(15, 10);
        lisaaSeinat();
        this.pelaaja = new Pelaaja(1, 1, '@', new Stats(10, 4));
        this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).asetaOlento(this.pelaaja);
        Hirvio orkki = new Hirvio(4, 1, 'o', new Stats(5, 5), 5);
        this.kartta.etsiRuutu(orkki.getX(), orkki.getY()).asetaOlento(orkki);
        hirviot.add(orkki);
        this.graafKayttis = new GraafinenKayttoliittyma(this.kartta, this);
    }
    
    public void testiPelinAlustus() {
        this.kartta = new Kartta(10, 10);
        this.kartta.etsiRuutu(0, 0).muutaSeinaksi(true);
        this.pelaaja = new Pelaaja(0, 1, '@', new Stats(10, 4));
        this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).asetaOlento(this.pelaaja);
    }
    
    public void lisaaSeinat() {
        for (int y = 0; y < this.kartta.getKorkeus(); y++) {
            for (int x = 0; x < this.kartta.getLeveys(); x++) {
                if (x == 0 || y == 0 || x == this.kartta.getLeveys() - 1 || y == this.kartta.getKorkeus() - 1) {
                    this.kartta.etsiRuutu(x, y).muutaSeinaksi(true);
                }
                if (x == 2 && y != 8) {
                    this.kartta.etsiRuutu(x, y).muutaSeinaksi(true);
                }
            }
        }
    }
    
    /**
     * Tapahtuu pelaajan antaman komennon jalkeen, antaa tulosteen pelivuorosta kayttoliittymalle
     */
    public String peliKierros(String komento) {
        if (this.kaynnissa) { 
            tekstikayttis.otaKomento(komento);
            tuhoaHirviot();
            liikutaHirviot();
            String tuloste = piirraPelitilanne();
            this.viestit = "";
            return tuloste;
        }
        else {
            this.viestit = "Kuolit haavoihisi ja hävisit pelin! Paina rastia lopettaaksesi";
            String tuloste = piirraPelitilanne();
            return tuloste;
        }
    }
    
    /**
     * Antaa tulosteen siita, mika nakyy pelaajalle.
     */
    public String piirraPelitilanne() {
        String pelinakyma = "";
        pelinakyma += piirraKarttanakyma();
        pelinakyma += piirraHUD() + "\n" + this.viestit;
        return pelinakyma;
    }
    
    public String piirraKarttanakyma() {
        String karttanakyma = "";
        for (int y = 0; y < this.kartta.getKorkeus(); y++) {
            for (int x = 0; x < this.kartta.getLeveys(); x++) {
                Ruutu naytettavaRuutu = this.kartta.etsiRuutu(x, y);
                if (nakokentassa(x, y)) {
                    karttanakyma += naytettavaRuutu.naytaSisalto();
                }
                else {
                    karttanakyma += " ";
                }
            }
            karttanakyma += "\n";
        }
        return karttanakyma;
    }
    
    public String piirraHUD() {
        String tuloste = "HP: " + this.pelaaja.getKyvyt().getHP() + " Voima: " + this.pelaaja.getKyvyt().getVoima() + " Exp: " + this.pelaaja.getKokemus();
        return tuloste;
    }
    
    /**Metodi kertoo, voiko pelaaja nähdä koordinaateissa olevaan ruutuun.
     * 
     * @param x
     * @param y
     * @return 
     */
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
    
    /**Pelaajahahmo lepää hetken, tarpeeksi levättyään paranee
     * 
     */
    public void lepaaHetki() {
        if (this.odotus.odota()) {
            this.pelaaja.getKyvyt().muutaHP(1);
        }
    }
    
    /**Metodi liikuttaa paratmetrin olentoa kartalla parametrien suuntaan ja hyökkää jos mahdollista.
     * 
     * @param dX
     * @param dY
     * @param olento 
     */
    public void liikutaHahmoa(int dX, int dY, Olento olento) {
        int uusiX = olento.getX() + dX;
        int uusiY = olento.getY() + dY;
        if (this.kartta.kartanUlkopuolella(uusiX, uusiY)) {
            return;
        }
        // jonkinlainen virheilmoitus kun ei voi liikkua
        if (ruudussaHirvio(this.kartta.etsiRuutu(uusiX, uusiY))) {
            Olento vihollinen = this.kartta.etsiRuutu(uusiX, uusiY).getOlento();
            lyo(olento, vihollinen);
        }
        //ylipaatansa ois kiva jos otuksen liikuttaminen hoitus vahan napparammin (ei tarttis saataa noiden asetaolento- ja liikuta-hommeleiden kaa)
        if (ruutuTyhja(this.kartta.etsiRuutu(uusiX, uusiY))) {
            this.kartta.etsiRuutu(uusiX, uusiY).asetaOlento(olento);
            this.kartta.etsiRuutu(olento.getX(), olento.getY()).asetaOlento(null);
            olento.liikuta(dX, dY);
        }
    }
    
    public void liikutaPelaajaa(int dX, int dY) {
        liikutaHahmoa(dX, dY, this.pelaaja);
    }
    
    public boolean ruutuTyhja(Ruutu ruutu) {
        if (!ruutu.onkoSeina() && ruutu.getOlento() == null) {
            return true;
        }
        return false;
    }
    
    public boolean ruudussaHirvio(Ruutu ruutu) {
        if (ruutu.getOlento() != null) {
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
    
    public void osuma(Olento Kohde) {
        Kohde.getKyvyt().muutaHP(-3);
        if (Kohde.getKyvyt().getHP() > 0) {
            if (Kohde instanceof Hirvio) {
                this.viestit += "Osuit! Vihulla " + Kohde.getKyvyt().getHP() + " hiparia jaljella. ";
            }
            else {
                this.viestit += "Vihu osuu sinuun! Sinulla on " + Kohde.getKyvyt().getHP() + " hiparia jaljella. ";
            }
        }
        else {
            tappo(Kohde);
        }
    }
    
    public void huti(Olento Kohde) {
        if (Kohde instanceof Pelaaja) {
            this.viestit += "Vihollinen löi hudin! ";
        }
        else {
            this.viestit += "Et osunut! ";
        }
    }
    
    public void tappo(Olento Kohde) {
        if (Kohde instanceof Hirvio) {
            this.viestit += "Vihollinen kuoli!";
            Hirvio monseri = (Hirvio)Kohde;
            this.pelaaja.lisaaKokemusta(monseri.getExp());
            this.kartta.etsiRuutu(monseri.getX(), monseri.getY()).asetaOlento(null);
            this.tuhottavat.add(monseri);
        }
        else if (Kohde instanceof Pelaaja) {
            havio();
        }
    }
    
    public void havio() {
        this.viestit += "Kuolit haavoihisi ja hävisit pelin! :((((";
        this.kaynnissa = false;
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
    
    /**
     * Metodi poistaa kuolleet hirviot posi pelistä.
     */
    public void tuhoaHirviot() {
        this.hirviot.removeAll(this.tuhottavat);
        tuhottavat.clear();
    }
}
