
package game;

import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Game definiuje wstępne parametry okna gry
 *
 * @author Jakub Król
 *
 */
public class Game {
    /**
     * Główna metoda gry
     *
     */

    public static void main(String[] args) {
        /**
         * @Path ścieżka do głównej muzyki
         */
        JFrame frame = new JFrame("Type Master");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new GamePanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String Path ="dodatkowe/muzyczka.wav";
        Music musicObj = new Music();
        musicObj.playMusic(Path);

    }

}
