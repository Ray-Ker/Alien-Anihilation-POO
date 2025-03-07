package GameObject;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musi {

    private Clip clip;

    public void playMusic(String filepath) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream(filepath);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void stopMusic() {
        clip.stop();
        clip.close();
    }

}