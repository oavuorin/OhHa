/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ryomija;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ryomija.Odotusaika;

/**
 *
 * @author oavuorin
 */
public class OdotusaikaTest {
    
    private Odotusaika odottaja;
    
    public OdotusaikaTest() {
    }
    @Before
    public void setUp() {
        odottaja = new Odotusaika();
    }
    
    @Test
    public void VahanOdottaminenPalauttaaFalse() {
        odottaja.lepaa();
        odottaja.lepaa();
        assertEquals(false, odottaja.lepaa());
    }
    
    @Test
    public void TarpeeksiOdottaminenPalauttaaTrue() {
        odottaja.lepaa();
        odottaja.lepaa();
        odottaja.lepaa();
        odottaja.lepaa();
        assertEquals(true, odottaja.lepaa());
    }
}
