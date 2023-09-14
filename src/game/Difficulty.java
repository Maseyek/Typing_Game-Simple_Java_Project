package game;



import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Klasa dziedzicząca po klasie GameStateABS, odpowiedzialna za wygląd i mechanikę wyboru poziomu trudności.
 * @see GameStateABS
 */
public class Difficulty extends GameStateABS{
    /**
     * options tablica zawierająca dostępne opcje.
     */
    private final String[] options = {"Level 1", "Level 2", "Level 3", "Back"};
    /**
     * Selected aktualnie wybrana opcja.
     */
    private int Selected = 0;
    /**
     * img zmienna przechowująca obraz tła.
     */
    Image img = Toolkit.getDefaultToolkit().createImage("img/Kimi.gif");
    /**
     * Diff zmienna przechowująca wartość definiującą poziom trudności.
     */
    int Diff;

    /**
     * Wywołuje konstruktor klasy nadrzędnej
     * @see GameStateABS
     * @param GS obiekt klasy GameState
     */
    public Difficulty(GameState GS) {
        super(GS);

    }

    @Override
    public void init() { }

    @Override
    public void tick() { }

    /**
     * Metoda odpowiedzlna za wyrysowanie tła oraz poszczególnych opcji wyboru. Wyrysowanie opcji wyboru następuje
     * za pośrednictwem pętli. Dodatkowo zostaje wygenerowana 2px czarna ramka dookoła każdej litery.
     * Opcja wybrana podświetlana jest kolorem zielonym, niewybrana białym.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(img, 0, 0, null);
        for(int i = 0; i < options.length; i++){
            g.setFont(new Font("Impact", Font.PLAIN, 75));

            g.setColor(Color.BLACK);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 150 + 2, 150 + i * 150 - 2);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 150 - 2, 150 + i * 150 + 2);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 150 + 2, 150 + i * 150 - 2);
            g.drawString(options[i], GamePanel.WIDTH / 2 - 150 + 2, 150 + i * 150 + 2);
            if(i == Selected){
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], GamePanel.WIDTH / 2 - 150, 150 + i * 150);
        }
    }

    /**
     * Metoda realizująca wybór opcji. Użycie strzałki w góre lub w dół zmienia aktualnie podświetlaną opcje Menu.
     * Klawisz Enter zatwierdza wybór tej opcji. Wybór opcji realizowany jest poprzez przesunięcie wybranego stanu
     * na szczyt stosu stanów, z przekazaniem zmiennej Diff.
     * @see GameState
     * @param k wartość liczbowa przycisku
     */
    @Override
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
                Diff = 2000;
                GS.states.push(new Play(GS, Diff));
            }else if(Selected == 1){
                Diff = 1000;
                GS.states.push(new Play(GS, Diff));
            }else if(Selected == 2){
                Diff = 500;
                GS.states.push(new Play(GS, Diff));
            }else if(Selected == 3){
                GS.states.push(new Menu(GS));
            }

        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
