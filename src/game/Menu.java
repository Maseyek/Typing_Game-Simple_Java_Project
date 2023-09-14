
package game;

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Klasa dziedzicząca po klasie GameStateABS, odpowiedzialna za wygląd Menu gry.
 * @see GameStateABS
 */
public class Menu extends GameStateABS {
    /**
     * options tablica zawierająca dostępne opcje.
     */
    private final String[] options = {"Play", "Help", "Exit"};
    /**
     * Selected aktualnie wybrana opcja.
     */
    private int Selected = 0;
    /**
     * img zmienna przechowująca obraz tła.
     */
    Image img = Toolkit.getDefaultToolkit().createImage("img/Kimi.gif");

    /**
     * Wywołuje konstruktor klasy nadrzędnej
     * @see GameStateABS
     * @param GS obiekt klasy GameState
     */
    public Menu(GameState GS){

        super(GS);
    }

    public void init() { }

    public void tick() { }

    /**
     * Metoda odpowiedzlna za wyrysowanie tła oraz poszczególnych opcji wyboru. Wyrysowanie opcji wyboru następuje
     * za pośrednictwem pętli. Dodatkowo zostaje wygenerowana 2px czarna ramka dookoła każdej litery.
     * Opcja wybrana podświetlana jest kolorem zielonym, niewybrana białym.
     */
    public void draw(Graphics g) {

        g.drawImage(img, 0, 0, null);

        for(int i = 0; i < options.length; i++){


            g.setFont(new Font("Impact", Font.PLAIN, 75));

            g.setColor(Color.BLACK);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 100 + 2, 150 + i * 200 - 2);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 100 - 2, 150 + i * 200 + 2);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 100 + 2, 150 + i * 200 - 2);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 100 + 2, 150 + i * 200 + 2);
            if(i == Selected){
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], GamePanel.WIDTH / 2 - 100, 150 + i * 200);
        }
    }

    /**
     * Metoda realizująca wybór opcji. Użycie strzałki w góre lub w dół zmienia aktualnie podświetlaną opcje Menu.
     * Klawisz Enter zatwierdza wybór tej opcji. Wybór opcji realizowany jest poprzez przesunięcie wybranego stanu
     * na szczyt stosu stanów.
     * @see GameState
     * @param k wartość liczbowa przycisku
     */

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_DOWN) {
            Selected++;
            if(Selected >= options.length){
                Selected = 0;
            }
        }else if(k == KeyEvent.VK_UP){
            Selected--;
            if(Selected < 0){
                Selected = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_ENTER){
            if(Selected == 0){
                GS.states.push(new Difficulty(GS));
            }else if(Selected == 1){
                GS.states.push(new Help(GS));
            }else if(Selected == 2){
                System.exit(0);
            }
        }
    }


    public void keyReleased(int k) {

    }

}
