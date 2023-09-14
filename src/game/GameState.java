
package game;

import java.awt.Graphics;
import java.util.Stack;

/**
 * Klasa definiująca aktualny stan gry.
 * @see GamePanel
 */

public class GameState {
    /**
     * states stos przechowujący stany gry, wartość na szczycie definiuje aktualny stan gry.
     */
    public Stack<GameStateABS> states;

    /**
     * Konstruktor inicjalizujący nowy stos, domyślnie stan Menu jest na szczycie.
     */
    public GameState(){
        states = new Stack<>();
        states.push(new Menu(this));
    }

    /**
     * Metoda tick() wykonywana dla stanu znajdującego się na szczycie stosu.
     */
    public void tick(){
        states.peek().tick();
    }

    /**
     * Metoda draw() wykonywana dla stanu znajdującego się na szczycie stosu.
     */
    public void draw(Graphics g){
        states.peek().draw(g);
    }

    /**
     * Metoda keyPressed() wykonywana dla stanu znajdującego się na szczycie stosu.
     * @param k wartość numeryczna przycisku.
     */
    public void keyPressed(int k){
        states.peek().keyPressed(k);
    }

    /**
     * Metoda keyReleased() wykonywana dla stanu znajdującego się na szczycie stosu.
     * @param k wartość numeryczna przycisku.
     */
    public void keyReleased(int k){
        states.peek().keyReleased(k);
    }
}
