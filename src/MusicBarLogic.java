import java.beans.Encoder;
import java.io.*;
import java.util.ArrayList;
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

    public float getDuration(Music music) {
        Header h= null;
        FileInputStream file = null;
        Bitstream bitstream;
        try {
            file = new FileInputStream(music.getDirectory());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        }
        bitstream = new Bitstream(file);
        try {
            h = bitstream.readFrame();

        } catch (BitstreamException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        }
        int size = h.calculate_framesize();
        float ms_per_frame = h.ms_per_frame();
        int maxSize = h.max_number_of_frames(10000);
        float t = h.total_ms(size);
        long tn = 0;
        try {
            tn = file.getChannel().size();
        } catch (IOException ex) {
            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Chanel: " + file.getChannel().size());
        int min = h.min_number_of_frames(500);
        return h.total_ms((int) tn)/1000;
    }

    public void run() {
        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void changeTimePlayed(ArrayList<Music> musicList, Music music) {
        music.setLastTimePlayed(0);
        for (Music eachMusic : musicList)
            if (!(music.getName().equals(eachMusic.getName()))) {
                eachMusic.setLastTimePlayed(eachMusic.getLastTimePlayed() + 1);
            }

    }
}


