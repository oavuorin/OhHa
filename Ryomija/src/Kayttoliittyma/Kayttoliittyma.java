package Kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ryomija.Peli;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Peli peli;

    public Kayttoliittyma(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("Peli");
        frame.setPreferredSize(new Dimension(800, 800));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(new Piirtoalusta(this.peli.getKartta()));
    }
    
    private void lisaaKuuntelijat() {
        this.frame.addKeyListener(new Kuuntelija(this.peli, this.frame));
    }

    public JFrame getFrame() {
        return frame;
    }
}