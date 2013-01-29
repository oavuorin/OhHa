
package karttaelementit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import karttaelementit.Kartta;

public class KarttaTest {
    
    Kartta kartta;
    
    @Before
    public void setUp() {
        kartta = new Kartta(10, 10);
    }
    
    @Test
    public void konstruktoriAsettaaRuudutTyhjiksi() {
        for (int x = 0; x < kartta.getLeveys() ; x++) {
            for (int y = 0; y < kartta.getKorkeus() ; y++) {
                assertEquals(false, kartta.etsiRuutu(x, y).onkoSeina());
            }
        }
    }
    
    @Test
    public void konstruktoriAsettaaOikeanLeveyden() {
        assertEquals(kartta.getLeveys(), 10);
    }
    
    @Test
    public void konstruktoriAsettaaOikeanKorkeuden() {
        assertEquals(kartta.getKorkeus(), 10);
    }
}
