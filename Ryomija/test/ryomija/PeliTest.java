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
    public void kartanYlapuolellaOlevaRuutuHuomataan() {
        assertEquals(true, peli.kartanUlkopuolella(3, -1));
    }
    
    @Test
    public void kartanAlapuolellaOlevaRuutuHuomataan() {
        assertEquals(true, peli.kartanUlkopuolella(3, 10));
    }
    
    @Test
    public void kartanVasemmallaPuolellaOlevaRuutuHuomataan() {
        assertEquals(true, peli.kartanUlkopuolella(-1, 3));
    }
    
    @Test
    public void kartanOikeallaPuolellaOlevaRuutuHuomataan() {
        assertEquals(true, peli.kartanUlkopuolella(10, 3));
    }
}
