package ryomija;

public class Peli {
    private Kartta kartta;
    private Pelaaja pelaaja;
    
    public void aloita() {
        System.out.println("Seikkailu alkaa!");
        alustaPeli();
        testikierros();
    }
    
    public void alustaPeli() {
        this.kartta = new Kartta(10, 10);
        this.pelaaja = new Pelaaja(3, 3, '@');
    }
    
    public void piirraKartta() {
        for (int y = 0; y < this.kartta.getKorkeus(); y++) {
            for (int x = 0; x < this.kartta.getLeveys(); x++) {
                Ruutu naytettavaRuutu = this.kartta.etsiRuutu(x, y);
                System.out.print(naytettavaRuutu.naytaSisalto());
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void peliKierros() {
        while (true) {
            piirraKartta();
            //otaKomento();
        }
    }
    
    public void liikutaHahmoa(int dX, int dY) {
        // hahmo ei saa liikkua yli ruudun + jonkinlainen virheilmoitus kun ei voi liikkua + mita kay kun tormaa hirvioon
        if (!this.kartta.etsiRuutu(this.pelaaja.getX() + dX, this.pelaaja.getY() + dY).onkoSeina() 
            && this.kartta.etsiRuutu(this.pelaaja.getX() + dX, this.pelaaja.getY() + dY).getOlento() == null) {
            this.kartta.etsiRuutu(this.pelaaja.getX() + dX, this.pelaaja.getY() + dY).asetaOlento(this.pelaaja);
            this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY()).asetaOlento(null);
            this.pelaaja.liikuta(dX, dY);
        }
    }
    
    public void testikierros() {
        Ruutu alkuRuutu = this.kartta.etsiRuutu(this.pelaaja.getX(), this.pelaaja.getY());
        alkuRuutu.asetaOlento(this.pelaaja);
        this.kartta.etsiRuutu(this.pelaaja.getX() + 1, this.pelaaja.getY()).muutaSeinaksi(true);
        piirraKartta();
        liikutaHahmoa(0, 1);
        piirraKartta();
        liikutaHahmoa(0, -1);
        piirraKartta();
        liikutaHahmoa(1, 0);
        piirraKartta();
        liikutaHahmoa(-1, 0);
        piirraKartta();
    }
}
