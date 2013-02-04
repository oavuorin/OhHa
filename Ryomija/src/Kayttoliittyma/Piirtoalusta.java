package Kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import karttaelementit.*;

public class Piirtoalusta extends JPanel {
    
    private Kartta kartta;
    
    public Piirtoalusta(Kartta kartta) {
        super.setBackground(Color.white);
        this.kartta = kartta;
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int y = 0; y < this.kartta.getKorkeus(); y++) {
            for (int x = 0; x < this.kartta.getLeveys(); x++) {
                Ruutu naytettavaRuutu = this.kartta.etsiRuutu(x, y);
                if (naytettavaRuutu.getOlento() != null) {
                    graphics.setColor(Color.green);
                }
                else if (naytettavaRuutu.onkoSeina()) {
                    graphics.setColor(Color.BLACK);
                }
                else {
                    graphics.setColor(Color.WHITE);
                }
                graphics.fillRect(x * 20, y * 20, 20, 20);
            }
        }
    }
}
