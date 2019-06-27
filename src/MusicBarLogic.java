import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MusicBarLogic {
    private FileInputStream audioInputStream;
    private Music currentMusic;
    private Player player;
    private long pausedLocation = 0;
    private long totalLengthSize = 0;
    private boolean repeat;
    private boolean paused;
    private ChangeListener listener;
    private long duration;

    public void play(Music music, JSlider musicSlider) throws Exception {
        currentMusic = music;
        audioInputStream = new FileInputStream(music.getDirectory());
        duration = Objects.requireNonNull(audioInputStream).getChannel().size();
        player = new Player(audioInputStream);
        totalLengthSize = audioInputStream.available();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    player.play();
                    musicSlider.setValue(0);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void musicSlider(int length) {

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void repeat(JSlider musicSlider) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).join();
        if (player.isComplete() && repeat) {
            play(currentMusic, musicSlider);
            musicSlider.setValue(0);
        }
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

    public Long getDuration() {
        return duration;
    }
        public void run() {
            try {
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }


