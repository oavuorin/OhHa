package ryomija;

import karttaelementit.*;
import kayttoliittyma.GraafinenKayttoliittyma;
import esineet.*;
import java.lang.Math;
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
    private int pelitila;
    private String viestit;
    private Inventaario inventaario;
    private Komennonkasittelija komentoKasittelija;
    private GraafinenKayttoliittyma graafKayttis;
    private List<Hirvio> hirviot;
    private List<Hirvio> tuhottavat;
    
    public Peli() {
        this.viestit = "Tervetuloa peliin! Yritä selvitä hengissä. ";
        this.noppa = new Random();
        this.odotus = new Odotusaika();
        this.tuhottavat = new ArrayList<Hirvio>();
        this.komentoKasittelija = new Komennonkasittelija(this);
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
    
    public Inventaario getInventaario() {
        return this.inventaario;
    }
    
    public void setViestit(String muutos) {
        this.viestit = muutos;
    }
    
    public void setPelitila(int tila) {
        this.pelitila = tila;
    }
    
    public void lisaaViesteihin(String lisays) {
        this.viestit += lisays;
    }
    
    /**Kutsuu pelinalustusmetodia ja käynnistää käyttöliittymän.
     * 
     */
    public void aloita() {
        alustaPeli();
        SwingUtilities.invokeLater(graafKayttis);
    }
    
    /** Luo pelille kartta-, pelaaja-, ja hirvio-oliot pelaamista varten.
     * 
     */
    public void alustaPeli() {
        this.pelitila = 1;
        Kartanrakentaja rakentaja = new Kartanrakentaja(this);
        this.kartta = rakentaja.rakennaKartta();
        this.pelaaja = new Pelaaja(1, 1, '@', new Stats(10, 4));
        this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).asetaOlento(this.pelaaja);
        this.hirviot = this.kartta.getHirviot();
        this.inventaario = new Inventaario();
        this.inventaario.lisaaEsine(new Parannusjuoma(this));
        this.inventaario.lisaaEsine(new Parannusjuoma(this));
        this.graafKayttis = new GraafinenKayttoliittyma(this.kartta, this);
    }
    
    /**Toimii samoin kuin alustaPeli(), mutta käytetty vain pelin testaamista varten.
     * 
     */
    public void testiPelinAlustus() {
        this.kartta = new Kartta(10, 10);
        this.kartta.etsiRuutu(0, 0).muutaSeinaksi(true);
        this.pelaaja = new Pelaaja(0, 1, '@', new Stats(10, 4));
        this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).asetaOlento(this.pelaaja);
    }
    
    
    /**
     * Tapahtuu pelaajan antaman komennon jalkeen, antaa tulosteen pelivuorosta kayttoliittymalle
     */
    public String peliKierros(String komento) {
        if (this.pelitila == 1) { 
            return peliKierrosElossa(komento);
        }
        if (this.pelitila == 2) {
            return peliKierrosInventaariossa(komento);
        }
        else {
            return peliKierrosKuolleena();
        }
    }
    
    /**Pistää pelaajan komennon komennonkäsittelijälle ja huolehtii sen jälkeisestä vuorosta.
     * 
     * @param komento pelaajan antama komento
     * @return Merkkijono pelitilanteesta
     */
    public String peliKierrosElossa(String komento) {
        komentoKasittelija.otaKomentoNormaalitilassa(komento);
        String tuloste = "";
        if (this.pelitila == 1) {
            tuhoaHirviot();
            liikutaHirviot();
            tuloste = piirraPelitilanne();
        }
        if (this.pelitila == 2) {
            tuloste = piirraInventaario();
        }
        this.viestit = "";
        return tuloste;
    }
    
    public String peliKierrosInventaariossa(String komento) {
        this.komentoKasittelija.otaKomentoInventaariossa(komento);
        return piirraPelitilanne();
    }
    
    /**Jos pelaaja on kuollut, peli ei enää siirrä komentoja eteenpäin.
     * 
     * @return Merkkijono pelitilanteesta
     */
    public String peliKierrosKuolleena() {
        this.viestit = "Kuolit haavoihisi ja hävisit pelin! Paina rastia lopettaaksesi";
        String tuloste = piirraPelitilanne();
        return tuloste;
    }
    
    
    /**
     * Antaa tulosteen siita, mika nakyy pelaajalle.
     */
    public String piirraPelitilanne() {
        String pelinakyma = "";
        pelinakyma += piirraKarttanakyma();
        pelinakyma += piirraHUD() + "\n" + lisaaRivinvaihdot(this.viestit);
        return pelinakyma;
    }
    
    /**
     * Antaa tulosteen siita, miten kartta näkyy pelaajalle.
     */
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
    
    /**
     * Antaa tulosteen pelaajan statsien senhetkisestä tilasta.
     */
    public String piirraHUD() {
        String tuloste = "HP: " + this.pelaaja.getKyvyt().getHP() + " Voima: " + this.pelaaja.getKyvyt().getVoima() + " Exp: " + this.pelaaja.getKokemus();
        return tuloste;
    }
    
    public String piirraInventaario() {
        return this.inventaario.toString();
    }
    
    /**Metodi kertoo, voiko pelaaja nähdä koordinaateissa olevaan ruutuun.
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean nakokentassa(int x, int y) {
        int dx = Math.abs(x - this.pelaaja.getX());
        int dy = Math.abs(y - this.pelaaja.getY());
        if (dx + dy > 3) {
            return false;
        }
        if (seinanTakana(lahempiX(x), lahempiY(y))) {
            return false;
        }
        return true;
    }
    
    /**Metodi palauttaa true, jos haettu kohta kartalla on pelaajaan nähden seinän takana.
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean seinanTakana(int x, int y) {
        if (this.kartta.etsiRuutu(x, y).onkoSeina()) {
            return true;
        }
        else if (x == this.pelaaja.getX() && y == this.pelaaja.getY()) {
            return false;
        }
        return seinanTakana(lahempiX(x), lahempiY(y));
    }
    
    /**Palauttaa x-arvon, joka on yhden lähempänä pelaajan x-arvoa kuin argumentti.
     * 
     * @param x
     * @return Integer joka on yhden lähempänä x:ää
     */
    public int lahempiX(int x) {
        if (pelaaja.getX() < x) {
            return x - 1;
        }
        else if (pelaaja.getX() > x) {
            return x + 1;
        }
        else {
            return x;
        }
    }
    
    /**Palauttaa y-arvon, joka on yhden lähempänä pelaajan y-arvoa kuin argumentti.
     * 
     * @param y
     * @return integer joka on yhden lähempänä y:tä
     */
    public int lahempiY(int y) {
        if (pelaaja.getY() < y) {
            return y - 1;
        }
        else if (pelaaja.getY() > y) {
            return y + 1;
        }
        else {
            return y;
        }
    }
    
    /**Pelaajahahmo lepää hetken, tarpeeksi levättyään paranee
     * 
     */
    public void lepaaHetki() {
        if (this.odotus.odota()) {
            this.pelaaja.getKyvyt().muutaHP(1);
        }
    }
    
    /**Metodi liikuttaa paratmetrin olentoa kartalla parametrien dX ja dY suuntaan ja hyökkää jos mahdollista.
     * 
     * @param dX kuinka paljon olennon X-arvo muuttuu
     * @param dY kuinka paljon olennon Y-arvo muuttuu
     * @param olento mitä olentoa liikutetaan
     */
    public void liikutaHahmoa(int dX, int dY, Olento olento) {
        int uusiX = olento.getX() + dX;
        int uusiY = olento.getY() + dY;
        if (this.kartta.kartanUlkopuolella(uusiX, uusiY)) {
            return;
        }
        if (ruudussaOlento(this.kartta.etsiRuutu(uusiX, uusiY))) {
            Olento vihollinen = this.kartta.etsiRuutu(uusiX, uusiY).getOlento();
            lyo(olento, vihollinen);
        }
        if (ruutuTyhja(this.kartta.etsiRuutu(uusiX, uusiY))) {
            this.kartta.etsiRuutu(uusiX, uusiY).asetaOlento(olento);
            this.kartta.etsiRuutu(olento.getX(), olento.getY()).asetaOlento(null);
            olento.liikuta(dX, dY);
        }
    }
    
    /**Kuin edellinen, mutta pelaajan liikuttelua varten.
     * 
     * @param dX
     * @param dY 
     */
    public void liikutaPelaajaa(int dX, int dY) {
        liikutaHahmoa(dX, dY, this.pelaaja);
    }
    
    /**Tarkastaa, onko haluttu ruutu tyhjä.
     * 
     * @param ruutu Tarkasteltava ruutu.
     * @return true, jos ruutu on tyhjä, muuten false.
     */
    public boolean ruutuTyhja(Ruutu ruutu) {
        if (!ruutu.onkoSeina() && ruutu.getOlento() == null) {
            return true;
        }
        return false;
    }
    
    
    /**Tarkastaa, onko ruudussa olento.
     * 
     * @param ruutu Tarkasteltava ruutu
     * @return True, jos ruudussa on olento, muuten false
     */
    public boolean ruudussaOlento(Ruutu ruutu) {
        if (ruutu.getOlento() != null) {
            return true;
        }
        return false;
    }
    
    /**Metodilla katsotaan, osutaanko taistelussa.
     * 
     * @param hyokkaaja Hyökkäävä osapuoli
     * @param puolustaja Lyötävä osapuoli
     */
    public void lyo(Olento hyokkaaja, Olento puolustaja) {
        if (hyokkaaja instanceof Hirvio && puolustaja instanceof Hirvio) {
            return;
        }
        if (noppa.nextInt(9) < hyokkaaja.getKyvyt().getVoima()) {
            osuma(hyokkaaja, puolustaja);
        }
        else {
            huti(puolustaja);
        }
        System.out.println();
    }
    
    /**Osumassa vähennetaan kohteen HP:ta ja siirrytään eteenpäin.
     * 
     * @param hyokkaaja hyökkaavä osapuoli
     * @param Kohde lyötävä osapuoli
     */
    public void osuma(Olento hyokkaaja, Olento kohde) {
        Random vahinko = new Random();
        kohde.getKyvyt().muutaHP((vahinko.nextInt(hyokkaaja.getKyvyt().getVoima()) + 1)*-1);
        if (kohde.getKyvyt().getHP() > 0) {
            eiTappavaOsuma(kohde);
        }
        else {
            tappo(kohde);
        }
    }
    
    /**Muuttaa viestiosuuden oikeanlaiseksi, kun joku on saanut osuman joka ei tapa.
     * 
     * @param kohde Olento, jota on lyöty
     */
    public void eiTappavaOsuma(Olento kohde) {
        if (kohde instanceof Hirvio) {
                this.viestit += "Osuit! Vihulla " + kohde.getKyvyt().getHP() + " hiparia jaljella. ";
            }
        else {
                this.viestit += "Vihu osuu sinuun! Sinulla on " + kohde.getKyvyt().getHP() + " hiparia jaljella. ";
        }
    }
    
    /**Muuttaa viestiosuuden oikeanlaiseksi, kun lyönti ei osu.
     * 
     * @param Kohde Olento, jota on yritetty lyödä.
     */
    public void huti(Olento Kohde) {
        if (Kohde instanceof Pelaaja) {
            this.viestit += "Vihollinen löi hudin! ";
        }
        else {
            this.viestit += "Et osunut! ";
        }
    }
    
    /**Kun lyönti tappaa, metodi poistaa Hirvion tai pelaajan tapauksessa lopettaa pelin.
     * 
     * @param Kohde Lyöty olento
     */
    public void tappo(Olento Kohde) {
        if (Kohde instanceof Hirvio) {
            this.viestit += "Surmasit vihollisen!";
            Hirvio monseri = (Hirvio)Kohde;
            this.kartta.etsiRuutu(monseri.getX(), monseri.getY()).asetaOlento(null);
            this.tuhottavat.add(monseri);
        }
        else if (Kohde instanceof Pelaaja) {
            havio();
        }
    }
    
    /**Hahmo poimii maasta esineen ja lisää sen inventaarioon jos se sinne mahtuu. Esine katoaa maasta.
     * 
     */
    public void poimi() {
        if (this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).getEsine() != null) {
            Ruutu poimittavaRuutu = this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY());
            if (this.inventaario.lisaaEsine(poimittavaRuutu.getEsine())) {
                this.viestit += "Poimit esineen " + poimittavaRuutu.getEsine();
                poimittavaRuutu.asetaEsine(null);
                return;
            }
            this.viestit += "Esine ei mahtunut reppuusi. ";
        }
    }
    
    /**Kun pelaaja häviää pelin, peliviesti asetetaan sopivaksi ja tila kuolleeksi.
     * 
     */
    public void havio() {
        this.viestit += "Kuolit haavoihisi ja hävisit pelin! :((((";
        this.pelitila = 0;
    }
    
    /**Liikuttaa hirvio-olioita pelikentällä.
     * 
     */
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
     * Metodi poistaa kuolleet hirviot pois pelistä.
     */
    public void tuhoaHirviot() {
        this.hirviot.removeAll(this.tuhottavat);
        tuhottavat.clear();
    }
    
    /**Metodi lisää ruudulla näkyviin viesteihin rivinvaihtoja, jotta ne eivät ylittäisi ruudun reunaa.
     * 
     * @param teksti Teksti, johon rivinvaihdot lisätään.
     * @return Teksti, johon on lisätty rivinvaihdot.
     */
    public String lisaaRivinvaihdot(String teksti) {
        for (int merkki = 0; merkki < teksti.length(); merkki++) {
            if (merkki % 50 == 0) {
                String alkupaa = teksti.substring(0, merkki) + "\n";
                String loppupaa = teksti.substring(merkki, teksti.length());
                teksti = alkupaa + loppupaa;
            }
        }
        return teksti;
    }
}
