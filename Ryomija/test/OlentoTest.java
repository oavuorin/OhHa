import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import karttaelementit.Olento;

public class OlentoTest {
    
    Olento olento;
    
    @Before
    public void setUp() {
        olento = new Olento(1, 1, 'a');
    }
    
    @Test
    public void konstruktoriAsettaaXnOikein() {
        assertEquals(1, olento.getX());
    }
    
    @Test
    public void konstruktoriAsettaaYnOikein() {
        assertEquals(1, olento.getY());
    }
    
    @Test
    public void kostruktoriAsettaaSymbolinOikein() {
        assertEquals('a', olento.getSymboli());
    }
    
    @Test
    public void liikutusOikealleToimii() {
        olento.liikuta(1, 0);
        assertEquals(2, olento.getX());
    }
    
    @Test
    public void liikutusVasemmalleToimii() {
        olento.liikuta(-1, 0);
        assertEquals(0, olento.getX());
    }
    
    @Test
    public void liikutusAlasToimii() {
        olento.liikuta(0, 1);
        assertEquals(2, olento.getY());
    }
    
    @Test
    public void liikutusYlosToimii() {
        olento.liikuta(0, -1);
        assertEquals(0, olento.getY());
    }
}
