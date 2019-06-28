import java.beans.Encoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
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
                    musicSlider(musicSlider);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void musicSlider(JSlider musicSlider) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pausedLocation = audioInputStream.available();
                    musicSlider.setValue((int) ((totalLengthSize - pausedLocation) / totalLengthSize) * 100);
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }

        }).start();

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

    public long getDuration(Music music) throws Exception {
        File file = new File(music.getDirectory());
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(file);
        Map properties = baseFileFormat.properties();
        Long duration = (Long) properties.get("duration");
        return duration;
    }

    /**
     * @param duration
     * @return
     */
    public String convertDuration(long duration) {
        String out = null;
        long hours = 0;
        try {
            hours = (duration / 3600000);
        } catch (Exception e) {
            e.printStackTrace();
            return out;
        }
        long remaining_minutes = (duration - (hours * 3600000)) / 60000;
        String minutes = String.valueOf(remaining_minutes);
        if (minutes.equals(0)) {
            minutes = "00";
        }
        long remaining_seconds = (duration - (hours * 3600000) - (remaining_minutes * 60000));
        String seconds = String.valueOf(remaining_seconds);
        if (seconds.length() < 2) {
            seconds = "00";
        } else {
            seconds = seconds.substring(0, 2);
        }

        if (hours > 0) {
            out = hours + ":" + minutes + ":" + seconds;
        } else {
            out = minutes + ":" + seconds;
        }

        return out;

    }


    /**
     * @param musicList
     * @param music
     */
    public void changeTimePlayed(ArrayList<Music> musicList, Music music) {
        music.setLastTimePlayed(0);
        for (Music eachMusic : musicList)
            if (!(music.getName().equals(eachMusic.getName()))) {
                eachMusic.setLastTimePlayed(eachMusic.getLastTimePlayed() + 1);
            }

    }

    /**
     * @param labelTimeCounter
     */
    public void setLabelTimeCounter(JLabel labelTimeCounter) {

        new Thread(new Runnable() {
            int sec = 0;
            int min = 0;
            String minutes;
            String second;

            @Override
            public void run() {
                try {
                    while (!player.isComplete()) {
                        if (min < 10) {
                            minutes = "0" + min;
                        }
                        else minutes = min + "";
                        if (sec < 10) {
                            second = "0" + sec;
                        }
                        else second = sec + "";
                    }
                    labelTimeCounter.setText(minutes+second);
                    Thread.sleep(1000);
                    sec++;
                    if (sec >= 60) min++;

                } catch (
                        Exception e) {
                }
            }
        }).start();

    }
}


