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
            case KeyEvent.VK_COMMA:
                this.piirtaja.setText(this.peli.peliKierros(","));
                break;
            case KeyEvent.VK_I:
                this.piirtaja.setText(this.peli.peliKierros("i"));
                break;
            case KeyEvent.VK_1:
                this.piirtaja.setText(this.peli.peliKierros("1"));
                break;
            case KeyEvent.VK_2:
                this.piirtaja.setText(this.peli.peliKierros("2"));
                break;
            case KeyEvent.VK_3:
                this.piirtaja.setText(this.peli.peliKierros("3"));
                break;
            case KeyEvent.VK_4:
                this.piirtaja.setText(this.peli.peliKierros("4"));
                break;
            case KeyEvent.VK_5:
                this.piirtaja.setText(this.peli.peliKierros("5"));
                break;
            case KeyEvent.VK_6:
                this.piirtaja.setText(this.peli.peliKierros("6"));
                break;
            case KeyEvent.VK_7:
                this.piirtaja.setText(this.peli.peliKierros("7"));
                break;
            case KeyEvent.VK_8:
                this.piirtaja.setText(this.peli.peliKierros("8"));
                break;
            case KeyEvent.VK_9:
                this.piirtaja.setText(this.peli.peliKierros("9"));
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
