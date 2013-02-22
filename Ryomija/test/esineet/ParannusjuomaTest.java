package esineet;

import org.junit.Before;
import org.junit.Test;
import ryomija.Peli;
import static org.junit.Assert.*;

public class ParannusjuomaTest {
    
    private Parannusjuoma juoma;
    private Peli peli;
    
    public ParannusjuomaTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        peli.testiPelinAlustus();
        juoma = new Parannusjuoma(peli);
    }
    
    @Test
    public void juomanKayttaminenLisaaViestin() {
        juoma.kayta();
        assertEquals(true, peli.getViestit().contains("Joit parannusjuoman."));
    }
    
    @Test
    public void juomanKayttaminenLisaaHPta() {
        peli.getPelaaja().getKyvyt().muutaHP(-3);
        juoma.kayta();
        assertEquals(10, peli.getPelaaja().getKyvyt().getHP());
    }
}
