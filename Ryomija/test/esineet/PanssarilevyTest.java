
package esineet;

import org.junit.Before;
import org.junit.Test;
import ryomija.Peli;
import static org.junit.Assert.*;


public class PanssarilevyTest {
    
    private Panssarilevy levy;
    private Peli peli;
    
    public PanssarilevyTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        peli.testiPelinAlustus();
        levy = new Panssarilevy(peli);
    }
    
    @Test
    public void levynKayttaminenLisaaViestin() {
        levy.kayta();
        assertEquals(true, peli.getViestit().contains("Asetit panssarilevyn"));
    }
    
    @Test
    public void levynKayttaminenNostaaMaxHPta() {
        levy.kayta();
        assertEquals(15, peli.getPelaaja().getKyvyt().getMaxHP());
    }
}
