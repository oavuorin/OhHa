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
    public void ParametritonKonstruktoriAsettaaRajaksi5() {
        assertEquals(5, odottaja.getRaja());
    }
    
    @Test
    public void KonstruktoriAsettaaAloitusArvoksi0() {
        assertEquals(0, odottaja.getOdotettuAika());
    }
    
    @Test
    public void VahanOdottaminenPalauttaaFalse() {
        odottaja.odota();
        odottaja.odota();
        assertEquals(false, odottaja.odota());
    }
    
    @Test
    public void TarpeeksiOdottaminenPalauttaaTrue() {
        odottaja.odota();
        odottaja.odota();
        odottaja.odota();
        odottaja.odota();
        assertEquals(true, odottaja.odota());
    }
}
