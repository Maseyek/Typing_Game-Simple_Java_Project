package game;

import java.awt.event.KeyEvent;


import java.awt.*;

/**
 * Klasa dziedzicząca po klasie GameStateABS, odpowiada za realizację okna pomocy.
 */
public class Help extends GameStateABS {
    /**
     * img zmienna przechowująca obraz tła.
     */
    Image img = Toolkit.getDefaultToolkit().createImage("img/HelpBG.gif");
    /**
     * text zmienna przechowująca obraz tekstu "stałego".
     */
    Image text = Toolkit.getDefaultToolkit().createImage("img/Help.png");

    /**
     * Wywołuje konstruktor klasy nadrzędnej.
     * @param GS obiekt klasy GameState
     */
    public Help(GameState GS) {
        super(GS);
    }

    @Override
    public void init() { }

    @Override
    public void tick() { }

    /**
     * Metoda odpowiedzialna za wyrysowanie tła oraz instrukcji gry.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, null);
        g.drawImage(text, 0, 0, null);
        String option = "Back";
        g.setFont(new Font("Impact", Font.PLAIN, 75));
        g.setColor(Color.BLACK);
        g.drawString(option, GamePanel.WIDTH / 2 - 125 + 2, 700);
        g.drawString(option, GamePanel.WIDTH / 2 - 125 - 2, 700);
        g.drawString(option, GamePanel.WIDTH / 2 - 125 + 2, 700);
        g.drawString(option, GamePanel.WIDTH / 2 - 125 + 2, 700);
        g.setColor(Color.GREEN);
        g.drawString(option, GamePanel.WIDTH / 2 - 125,  700);
    }
    /**
     * Metoda odpowiedzialna za powrót do Menu głównego po wciśnięciu przycisku Enter. Stan Menu zostaje przesunięty na szczyt stosu.
     * @param k informacja o przycisku
     */
    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER) {
            GS.states.push(new Menu(GS));
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
