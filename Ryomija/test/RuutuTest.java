import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ryomija.Ruutu;

public class RuutuTest {
    
    Ruutu tyhja;
    Ruutu seina;
    
    @Before
    public void setUp() {
        tyhja = new Ruutu(false);
        seina = new Ruutu(true);
    }
    
    @Test
    public void konstruktoriLuoTyhjanRuudunOikein() {
        assertEquals(false, tyhja.onkoSeina());
    }
    
    @Test
    public void konstruktoriLuoSeinanOikein() {
        assertEquals(true, seina.onkoSeina());
    }
    
    @Test
    public void tyhjaMuuttuuSeinaksi() {
        tyhja.muutaSeinaksi(true);
        assertEquals(true, tyhja.onkoSeina());
    }
    
    @Test
    public void seinaMuuttuuTyhjaksi() {
        seina.muutaSeinaksi(false);
        assertEquals(false, seina.onkoSeina());
    }
}
