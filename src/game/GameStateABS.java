
package game;

import java.awt.Graphics;

/**
 * Klasa abstrakcyjna, wzór dla poszczególnych stanów.
 * @see GameState
 */
public abstract class GameStateABS {

    protected GameState GS;

    /**
     * Konstruktor tworzy obiekt klasy GS (GameState).
     * @param GS obiekt klasy GameState
     */
    public GameStateABS(GameState GS){
        this.GS = GS;
        init();
    }

    public abstract void init();
    public abstract void tick();
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);


}
