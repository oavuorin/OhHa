package esineet;

import org.junit.Before;
import org.junit.Test;
import ryomija.Peli;
import static org.junit.Assert.*;

public class VoimannostopunttiTest {
    
    private Voimannostopuntti puntti;
    private Peli peli;
    
    public VoimannostopunttiTest() {
    }
    
    @Before
    public void setUp() {
        peli = new Peli();
        peli.testiPelinAlustus();
        puntti = new Voimannostopuntti(peli);
    }
    
    @Test
    public void puntinKayttaminenLisaaViestin() {
        puntti.kayta();
        assertEquals(true, peli.getViestit().contains("Nostit puntteja"));
    }
    
    @Test
    public void puntinKayttaminenNostaaVoimaa() {
        puntti.kayta();
        assertEquals(5, peli.getPelaaja().getKyvyt().getVoima());
    }
}
