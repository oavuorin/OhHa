package kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import ryomija.Peli;

public class Tapahtumankuuntelija implements KeyListener {
    
    private JTextArea piirtaja;
    private Peli peli;
    
    public Tapahtumankuuntelija(JTextArea piirtaja, Peli peli) {
        this.piirtaja = piirtaja;
        this.peli = peli;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        this.piirtaja.setText(this.peli.piirraKartta());
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_UP:
                this.peli.liikutaPelaajaa(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                this.peli.liikutaPelaajaa(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                this.peli.liikutaPelaajaa(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                this.peli.liikutaPelaajaa(1, 0);
                break;
        }
        loppuvuoro();
    }
    
    public void loppuvuoro() {
        this.peli.tuhoaHirviot();
        this.peli.liikutaHirviot();
        this.piirtaja.setText(this.peli.piirraKartta());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
