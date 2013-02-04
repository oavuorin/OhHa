package Kayttoliittyma;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import karttaelementit.Pelaaja;
import ryomija.Peli;

public class Kuuntelija implements KeyListener {
    
    private Pelaaja pelaaja;
    private Peli peli;

    private Component component;

    public Kuuntelija(Peli peli, Component component) {
        this.peli = peli;
        this.pelaaja = this.peli.getPelaaja();
        this.component = component;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.peli.liikutaHahmoa(-1, 0, this.pelaaja);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.peli.liikutaHahmoa(1, 0, this.pelaaja);
        }

        component.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }
}