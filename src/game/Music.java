package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Klasa odpowiedzialna za główną muzykę.
 */
public class Music {

    /**
     * Metoda odpowiada za odczytanie i odtworzenie utworu. Plik muzyczny zostaję zapętlony.
     * @param MusicLoc ścieżka do utworu.
     */
    public void playMusic(String MusicLoc){
        try
        {
            File Music = new File(MusicLoc);
            AudioInputStream AudioInput = AudioSystem.getAudioInputStream(Music);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
