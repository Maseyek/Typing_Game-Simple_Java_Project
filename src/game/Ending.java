package game;

import java.awt.event.KeyEvent;

import java.awt.*;

/**
 * Klasa dziedzicząca po klasie GameStateABS, odpowiedzialna za ekran podsumowujący poziom
 */
public class Ending extends GameStateABS{
    /**
     * img zmienna przechowująca obraz tła.
     */
    Image img = Toolkit.getDefaultToolkit().createImage("img/Kimi.gif");
    /**
     * text zmienna przechowująca obraz tekstu "stałego".
     */
    Image text = Toolkit.getDefaultToolkit().createImage("img/EndingText.png");
    /**
     * good zmienna przechowująca liczbę poprawnie wciśniętych liter.
     */
    int good = 0;
    /**
     * bad zmienna przechowująca liczbę niepoprawnie wciśniętych lub pominiętych liter.
     */
    int bad = 0;

    /**
     * Wywołuje konstruktor klasy nadrzędnej.
     * @param GS obiekt klasy GameState
     * @param G parametr przechowujący informację o liczbie poprawnie wciśniętych liter.
     * @param B parametr przechowujący informację o liczbie niepoprawnie wciśniętych liter.
     * @see GameState
     * @see Play
     */
    public Ending(GameState GS, int G, int B) {
        super(GS);
        good = G;
        bad = B;

    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    /**
     * Metoda odpowiedzialna za wyrysowanie ekranu podsumowującego.
     */
    @Override
    public void draw(Graphics g) {

        g.drawImage(img, 0, 0, null);
        g.drawImage(text, 0, 0, null);
        g.setFont(new Font("Times New Roman",Font.BOLD, 45));

        float value = ((float)good/((float)good+(float)bad))*100;
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString((int)value), 705 + 2, 145 - 2);
        g.drawString(Integer.toString((int)value), 705 - 2, 145 + 2);
        g.drawString(Integer.toString((int)value), 705 + 2, 145 - 2);
        g.drawString(Integer.toString((int)value), 705 + 2, 145 + 2);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString((int)value),705, 145);

        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(good), 920 + 2, 196 - 2);
        g.drawString(Integer.toString(good), 920 - 2, 196 + 2);
        g.drawString(Integer.toString(good), 920 + 2, 196 - 2);
        g.drawString(Integer.toString(good), 920 + 2, 196 + 2);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(good),920, 196);

        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(bad), 895 + 2, 247 - 2);
        g.drawString(Integer.toString(bad), 895 - 2, 247 + 2);
        g.drawString(Integer.toString(bad), 895 + 2, 247 - 2);
        g.drawString(Integer.toString(bad), 895 + 2, 247 + 2);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(bad),895, 247);
    }

    /**
     * Metoda odpowiedzialna za powrót do Menu głównego po wciśnięciu przycisku Enter. Stan Menu zostaje przesunięty na szczyt stosu.
     * @param k informacja o przycisku
     */
    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            GS.states.push(new Menu(GS));
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
