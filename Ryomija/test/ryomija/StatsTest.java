
package ryomija;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ryomija.Stats;

public class StatsTest {
    
    private Stats statsit;
    
    public StatsTest() {
    }
    
    @Before
    public void setUp() {
        statsit = new Stats(10, 5);
    }
    
    
    @Test
    public void konstruktoriAsettaaVoimanOikein() {
        assertEquals(statsit.getVoima(), 5);
    }
    
    @Test
    public void konstruktoriAsettaaHPnOikein() {
        assertEquals(statsit.getHP(), 10);
    }
    
    @Test
    public void konstruktoriAsettaaMaxHPnOikein() {
        assertEquals(statsit.getHP(), statsit.getMaxHP());
    }
    
    @Test
    public void HPnMuuttaminenToimiiAlaspain() {
        statsit.muutaHP(-4);
        assertEquals(statsit.getHP(), 6);
    }
    
    @Test
    public void HPnMuuttaminenToimiiAlleNollan() {
        statsit.muutaHP(-12);
        assertEquals(statsit.getHP(), -2);
    }
    
    @Test
    public void HPEiNouseYliRajan() {
        statsit.muutaHP(-2);
        statsit.muutaHP(6);
        assertEquals(statsit.getHP(), 10);
    }
}
