package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import karttaelementit.Kartta;
import ryomija.Peli;

public class GraafinenKayttoliittyma implements Runnable {

    private JFrame frame;
    
    private Kartta kartta;
    private Peli peli;
    
    public GraafinenKayttoliittyma(Kartta kartta, Peli peli) {
        this.kartta = kartta;
        this.peli = peli;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Tunkio");
        frame.setPreferredSize(new Dimension(640, 480));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.pack();;
        frame.setVisible(true);
    }
    
    private void luoKomponentit(Container container) {
        JTextArea karttapiirros = new JTextArea(this.peli.piirraPelitilanne());
        karttapiirros.setEnabled(false);
        Font fontti = new Font("Courier New", Font.BOLD, 20);
        karttapiirros.setFont(fontti);
        lisaaKuuntelijat(karttapiirros);
        container.add(karttapiirros);
    }
    
    private void lisaaKuuntelijat(JTextArea tekstikentta) {
        Tapahtumankuuntelija kuuntelija = new Tapahtumankuuntelija(tekstikentta, this.peli);
        this.frame.addKeyListener(kuuntelija);
        
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
