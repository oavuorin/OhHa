
package esineet;

import org.junit.Before;
import org.junit.Test;
import ryomija.Peli;
import static org.junit.Assert.*;

public class VoitonAmulettiTest {
    
    private VoitonAmuletti amuletti;
    private Peli peli;
    
    public VoitonAmulettiTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        peli.testiPelinAlustus();
        amuletti = new VoitonAmuletti(peli);
    }
    
    @Test
    public void amuletinKayttaminenLisaaViestin() {
        amuletti.kayta();
        assertEquals(true, peli.getViestit().contains("Laitoit Zkklunrmin pyhän amuletin kaulaasi, muutuit jumalaksi ja pääsit pois hirveästä luolastosta!"));
    }
    
    @Test
    public void amuletinKayttaminenVaihtaaPelitilan() {
        amuletti.kayta();
        assertEquals(3, peli.getPelitila());
    }
}
