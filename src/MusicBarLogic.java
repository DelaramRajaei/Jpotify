import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicBarLogic {
    Long currentFrame;
    Clip clip;
    String status;
    AudioInputStream audioInputStream;
    Music currentMusic;

    public MusicBarLogic(){

    }

    public void play(Music music)throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
        currentMusic=music;
        audioInputStream = AudioSystem.getAudioInputStream(new File(music.getDirectory()).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);//repeat forever
        clip.start();
        status = "play";
    }

    public void pause() throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
        if (status.equals("paused")) {
            return;
        }
        this.currentFrame = this.clip.getMicrosecondLength();
        clip.stop();
        status = "paused";
    }

    public void resume() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        if (status.equals("play"))
            return;
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play(currentMusic);
    }

    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play(currentMusic);
    }

    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(currentMusic.getDirectory()).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void volume() {

    }
}

