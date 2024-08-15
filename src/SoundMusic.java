import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundMusic {
    private Clip clip;
    private FloatControl volumeControl;

    public SoundMusic(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Obtenir le contrôle de volume (s'il est disponible)
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Joue en boucle
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float range = max - min;
            float newValue = min + (range * volume); // volume doit être entre 0.0 et 1.0
            volumeControl.setValue(newValue);
        }
    }
}
