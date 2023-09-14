
package game;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa odpowiedzialna za okno gry oraz podstawowe mechaniki
 */

public class GamePanel extends JPanel implements Runnable, KeyListener {
    /**
     * WIDTH parametr rozdzielczości.
     */
    public static int WIDTH = 1280;
    /**
     * HEIGHT parametr rozdzielczości.
     */
    public static int HEIGHT = 720;

    private Thread thread;
    /**
     * IsON boolean sprawdzający stan gry ON/OFF.
     */
    private boolean IsON = false;
    private GameState GS;

    /**
     * Konstruktor odpowiedzialny za wyświetlenie okna o zadanej rozdzielczości.
     */
    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        start();
    }

    /**
     * Metoda startowa, IsON przyjmuję wartość true oraz zostaję utworzony nowy wątek.
     */
    private void start(){

        IsON = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Metoda będąca podstawowym napędem gry, zawiera pętle dla IsON = true wywołującą metodę tick() oraz repaint().
     */
    public void run(){
        GS = new GameState();
        while(IsON){

            tick();
            repaint();


        }
    }

    /**
     * Metoda wykonująca polecenie co tick w oparciu o aktualny GameState.
     */
    public void tick(){
        GS.tick();
    }

    /**
     * Metoda czyszcząca okno gry, następnie wyświetla obraz w oparciu o aktualny GameState.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.clearRect(0, 0, WIDTH, HEIGHT);
        GS.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metoda odpowiedzialna za wciśnięty przycisk w aktualnym GameState.
     * @param e informacja o przycisku
     */
    @Override
    public void keyPressed(KeyEvent e) {
        GS.keyPressed(e.getKeyCode());
    }

    /**
     * Metoda odpowiedzialna za zwolniony przycisk w aktualnym GameState.
     * @param e informacja o przycisku.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        GS.keyReleased(e.getKeyCode());
    }

}
