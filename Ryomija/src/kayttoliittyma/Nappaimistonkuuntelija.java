package kayttoliittyma;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import ryomija.Peli;

public class Nappaimistonkuuntelija implements KeyListener {
    
    private JTextArea piirtaja;
    private Peli peli;
    
    public Nappaimistonkuuntelija(JTextArea piirtaja, Peli peli) {
        this.piirtaja = piirtaja;
        this.peli = peli;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        this.piirtaja.setText(this.peli.piirraPelitilanne());
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_UP:
                this.piirtaja.setText(this.peli.peliKierros("w"));
                break;
            case KeyEvent.VK_DOWN:
                this.piirtaja.setText(this.peli.peliKierros("s"));
                break;
            case KeyEvent.VK_LEFT:
                this.piirtaja.setText(this.peli.peliKierros("a"));
                break;
            case KeyEvent.VK_RIGHT:
                this.piirtaja.setText(this.peli.peliKierros("d"));
                break;
            case KeyEvent.VK_PERIOD:
                this.piirtaja.setText(this.peli.peliKierros("."));
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
