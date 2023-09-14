package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Klasa odpowiedzialna za dźwięk błędu.
 */

public class Error {
    /**
     * Metoda odpowiada za odczytanie i jednorazowe odtworzenie dźwięku.
     * @param ErrorLoc ścieżka do dźwięku.
     */
    public void ErrorSound(String ErrorLoc){
        try
        {
            File Music = new File(ErrorLoc);
            AudioInputStream AudioInput = AudioSystem.getAudioInputStream(Music);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioInput);
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
