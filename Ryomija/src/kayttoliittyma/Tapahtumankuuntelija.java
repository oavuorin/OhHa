package kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import karttaelementit.Kartta;
import ryomija.Peli;

public class Tapahtumankuuntelija implements KeyListener {
    
    private JTextArea piirtaja;
    private Kartta kartta;
    private Peli peli;
    
    public Tapahtumankuuntelija(JTextArea piirtaja, Kartta kartta, Peli peli) {
        this.piirtaja = piirtaja;
        this.kartta = kartta;
        this.peli = peli;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_UP:
                this.peli.liikutaPelaajaa(0, -1);
                System.out.println("Ylos");
                break;
            case KeyEvent.VK_DOWN:
                this.peli.liikutaPelaajaa(0, 1);
                System.out.println("Alas");
                break;
            case KeyEvent.VK_LEFT:
                this.peli.liikutaPelaajaa(-1, 0);
                System.out.println("Vasen");
                break;
            case KeyEvent.VK_RIGHT:
                this.peli.liikutaPelaajaa(1, 0);
                System.out.println("Ylos");
                break;
        }
        loppuvuoro();
    }
    
    public void loppuvuoro() {
        this.piirtaja.setText(this.kartta.palautaKarttaTekstina());
        this.peli.tuhoaHirviot();
        this.peli.liikutaHirviot();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
