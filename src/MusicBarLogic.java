import java.io.FileInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;


public class MusicBarLogic {
    private FileInputStream audioInputStream;
    private Music currentMusic;
    private Player player;
    private long pausedLocation = 0;
    private long totalLengthSize = 0;
    private boolean repeat;
    private boolean paused;


    public void play(Music music) throws Exception {
        currentMusic = music;
        audioInputStream = new FileInputStream(music.getDirectory());
        player = new Player(audioInputStream);
        totalLengthSize = audioInputStream.available();
        player.play();
        if (player.isComplete() && repeat)
            play(currentMusic);
    }

    public void pause() throws Exception {
        paused = true;
        if (player != null) {
            pausedLocation = audioInputStream.available();
            player.close();
        }
    }

    public void resume() throws Exception {
        paused = false;
        audioInputStream = new FileInputStream(currentMusic.getDirectory());
        audioInputStream.skip(totalLengthSize - pausedLocation);
        player = new Player(audioInputStream);
        player.play();
    }


    public void stop() throws Exception {
        paused = false;
        if (player != null) {
            player.close();
            totalLengthSize = 0;
            pausedLocation = 0;
        }
    }

    public boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public void volume() {

    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}

