/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ryomija;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import karttaelementit.Kartta;
import karttaelementit.Hirvio;
import karttaelementit.Pelaaja;
import esineet.Parannusjuoma;

/**
 *
 * @author oavuorin
 */
public class PeliTest {
    
    private Peli peli;
    
    public PeliTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        peli.testiPelinAlustus();
    }
    
    @Test
    public void nakokentanUlkopuolelleEiNay() {
        assertEquals(false, peli.nakokentassa(4, 1));
    }
    
    @Test
    public void nakokentanSisalleNakyy() {
        peli.alustaPeli();
        assertEquals(true, peli.nakokentassa(3, 1));
    }
    
    @Test
    public void hahmoEiLiikuPainSeinaa() {
        peli.liikutaHahmoa(0, -1, peli.getPelaaja());
        assertEquals(1, peli.getPelaaja().getY());
    }
    
    @Test
    public void hahmoVoiLiikkuaTyhjaanRuutuun() {
        peli.liikutaHahmoa(0, 1, peli.getPelaaja());
        assertEquals(2, peli.getPelaaja().getY());
    }
    
    @Test
    public void hahmoEiLiikuUlosKartalta() {
        peli.liikutaHahmoa(-1, 0, peli.getPelaaja());
        peli.liikutaHahmoa(-1, 0, peli.getPelaaja());
        peli.liikutaHahmoa(-1, 0, peli.getPelaaja());
        assertEquals(0, peli.getPelaaja().getX());
    }
    
    @Test
    public void rivinvaihtojenLisaaminenToimii() {
        String testiString = "";
        for (int merkki = 0; merkki < 100; merkki++) {
            testiString += "a";
        }
        testiString = this.peli.lisaaRivinvaihdot(testiString);
        assertEquals('\n', testiString.charAt(50));
    }
    
    @Test
    public void rivinvaihtojenLisaaminenToimiiAaritapauksessa() {
        String testiString = "";
        for (int merkki = 0; merkki < 50; merkki++) {
            testiString += "a";
        }
        testiString = this.peli.lisaaRivinvaihdot(testiString);
        assertEquals('\n', testiString.charAt(50));
    }
    
    @Test
    public void havionViestiToimii() {
        peli.havio();
        assertEquals(true, peli.getViestit().contains("Kuolit haavoihisi ja hävisit pelin! :(((("));
    }
    
    @Test
    public void havioMuuttaaPelitilanOikein() {
        peli.havio();
        assertEquals(0, peli.getPelitila());
    }
    
    @Test
    public void voitonViestiToimii() {
        peli.voitto();
        assertEquals(true, peli.getViestit().contains("Voitit pelin!"));
    }
    
    @Test
    public void voittoMuuttaaPelitilanOikein() {
        peli.voitto();
        assertEquals(3, peli.getPelitila());
    }
    
    @Test
    public void poimiminenToimiiJosRuudussaOnEsine() {
        peli.getKartta().etsiRuutu(0, 1).asetaEsine(new Parannusjuoma(peli));
        peli.poimi();
        assertEquals(true, peli.getViestit().contains("Poimit esineen"));
    }
    
    @Test
    public void maassaOlevanEsineenIlmoitusToimii() {
        peli.getKartta().etsiRuutu(0, 2).asetaEsine(new Parannusjuoma(peli));
        peli.liikutaPelaajaa(0, 1);
        assertEquals(true, peli.getViestit().contains("Maassa on "));
    }
}
