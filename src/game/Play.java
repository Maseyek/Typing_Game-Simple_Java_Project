package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa dziedzicząca po klasie GameStateABS, realizuję główną mechanikę gry oraz wygląd okna w trakcie gry.
 */
public class Play extends GameStateABS{
    /**
     * img zmienna przechowująca obraz tła.
     */
    Image img = Toolkit.getDefaultToolkit().createImage("img/PlayBG.gif");
    /**
     * text zmienna przechowująca obraz tekstu "stałego", elementy tj. Time left, Precision.
     */
    Image text = Toolkit.getDefaultToolkit().createImage("img/PlayText.png");
    /**
     * Kolekcja przechowująca literki.
     */
    ArrayList<Letter> Letters = new ArrayList<>();
    /**
     * start zmienna przechowująca informację o czasie startu.
     */
    long start = System.currentTimeMillis();
    /**
     * good zmienna przechowująca liczbę poprawnie wciśniętych liter
     */
    int good = 0;
    /**
     * bad zmienna przechowująca liczbę niepoprawnie wciśniętych lub pominiętych liter..
     */
    int bad = 0;
    /**
     * gameStart druga zmienna na potrzebę przechowania informacji o czasie startu.
     */
    long gameStart = System.currentTimeMillis();
    /**
     * timeLeft zmienna przechowująca informację o pozostałym czasie gry.
     */
    long timeLeft;
    /**
     * Diff zmienna definiująca poziom trudności.
     */
    int Diff;
    /**
     * Path ścieżka dźwięku błędu.
     */
    String Path ="dodatkowe/Error.wav";
    /**
     * musicError utworzeniu obiektu klasy Error
     * @see Error
     */
    Error musicError = new Error();

    /**
     * Wywołuje konstruktor klasy nadrzędnej
     * @param GS obiekt klasy GameState
     * @param D parametr poziomu trudności
     * @see GameStateABS
     */
    public Play(GameState GS, int D) {
        super(GS);
        Diff = D;
    }

    /**
     * Metoda realizowana przy starcie poziomu. Uruchomienie zegara.
     */
    @Override
    public void init() {

        gameStart = System.currentTimeMillis();
    }

    /**
     * Metoda realizująca mechanikę gry co tick. Zostaję utworzony obiekt klasy Letter.
     * Dla określonego poziomu trudności, co czas Diff generowana jest nowa literka.
     * Zostaję ona dodana do kolekcji Letters. Wygenerowana literka posiada współrzędne x oraz y, w których zostanie
     * wyświetlona na ekranie gracza. Literka jest definiowana przez wylosowany numer z zakresu 0:26 dodany do wartości ASCII
     * litery A. Pętla sprawdza czy dana litera została wciśnięta (zniknęła z kolekcji), jeżeli tak się nie stało zostaje
     * ona usunięta i zostaje naliczony punkt karny oraz odtworzony dźwięk błędu. Czas na wciśnięcie przycisku wynosi 2*Diff.
     * Czas gry został ustawiony na 60 sekund, bo upłynięciu tego czasu zostaje ustawiony stan Ending.
     * @see Letter
     */
    @Override
    public void tick() {
        if(System.currentTimeMillis()-start >= Diff){
            start = System.currentTimeMillis();
            Letter Let = new Letter();
            Random rand = new Random();
            Let.x = rand.nextInt(1150)+75;
            Let.y = rand.nextInt(520)+100;
            Let.time = start;
            Let.Let = (char)(rand.nextInt(26)+'A');
            Letters.add(Let);
        }
        for(Letter Let:new ArrayList<Letter>(Letters)){
            if(System.currentTimeMillis()-Let.time >= Diff*2){
                Letters.remove(Let);
                musicError.ErrorSound(Path);
                bad++;
            }
        }
        if(System.currentTimeMillis()-gameStart >= 60000){
            GS.states.push(new Ending(GS,good, bad));
        }
        timeLeft = (60000-(System.currentTimeMillis()-gameStart))/1000;
    }

    /**
     * Metoda odpowiedzialna za wyświetlanie poszczególnych liter zawartych w kolekcji, wskaźnika dokładności oraz czasu
     * jaki pozostał do końca rundy.
     */
    @Override
    public void draw(Graphics g) {

        g.drawImage(img, 0, 0, null);
        g.drawImage(text, 0, 0, null);
        g.setFont(new Font("Times New Roman",Font.BOLD, 30));
        float value = ((float)good/((float)good+(float)bad))*100;
        g.drawString(Integer.toString((int)value),135, 28);
        g.drawString(Long.toString(timeLeft),1230, 28);
        g.setFont(new Font("Impact",Font.PLAIN, 75));
        for(Letter Let:Letters){

            g.drawString(String.valueOf(Let.Let), Let.x, Let.y);
        }


    }

    /**
     * Metoda odpowiedzialna za wykrycię czy została wciśnięta poprawna litera. Jeżeli tak zostaje ona usunięta z kolekcji
     * i naliczany jest punkt dodatni, jeżeli nie litera zostaje usunięta z kolekcji, naliczony jest punkt ujemny oraz
     * odtworzony dźwięk błędu.
     * Wciśnięcie przycisku ESCAPE powoduje przerwanie rozgrywki i powrót do Menu głównego gry.
     * @param k informacja o przycisku
     */
    @Override
    public void keyPressed(int k) {
        for(Letter Let:new ArrayList<Letter>(Letters)){
            if(k == Let.Let){
                Letters.remove(Let);
                good++;
                break;
            } else if (k == KeyEvent.VK_ESCAPE) {
                GS.states.push(new Menu(GS));
            } else{
                Letters.remove(Let);
                bad++;
                musicError.ErrorSound(Path);
                break;
            }
        }

    }

    @Override
    public void keyReleased(int k) {

    }
}
