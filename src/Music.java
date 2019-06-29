import com.mpatric.mp3agic.ID3v2;

import com.mpatric.mp3agic.Mp3File;


import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.ArrayList;

import java.util.Map;


public class Music {
    private ImageIcon icon;
    private String name;
    private String artist;
    private String album;
    private String directory;
    private int year;
    private ArrayList<PlayList> playLists;
    private File file;
    private int lastTimePlayed;
    private String timeDuration;

    public Music(String directory) throws Exception {
        lastTimePlayed = 0;
        playLists = new ArrayList<PlayList>();
        try {
            file = new File(directory);
            FileInputStream fileStream = new FileInputStream(new File(directory));
            byte[] bytes = new byte[128];
            long i = file.length();
            fileStream.skip(file.length() - 128);
            fileStream.read(bytes);
            fileStream.close();
            String metaTag = new String(bytes);
            this.directory = directory;
            if (metaTag.substring(0, 3).equals("TAG")) {
                name = metaTag.substring(3, 33);
                artist = metaTag.substring(33, 63);
                album = metaTag.substring(63, 93);
                year = Integer.parseInt(metaTag.substring(93, 97));
            } else {
                throw new Exception("Not a ID3v1 TAG");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setImage();

        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);

        Map<?, ?> properties = (fileFormat).properties();
        String key = "duration";
        Long microseconds = (Long) properties.get(key);
        int mili = (int) (microseconds / 1000);
        int sec = (mili / 1000) % 60;
        int min = (mili / 1000) / 60;

        System.out.println("time = " + min + ":" + sec);
        timeDuration = ("time = " + min + ":" + sec);
        System.out.println(timeDuration);


    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void addPlayList(PlayList playList) {
        playLists.add(playList);
    }

    public ArrayList<PlayList> getPlaylist() {
        return playLists;
    }

    public String getDirectory() {
        return directory;
    }

    public ImageIcon getImage() {
        return icon;
    }

    public void setImage() throws Exception {
        Mp3File song = new Mp3File(directory);
        if (song.hasId3v2Tag()) {
            ID3v2 id3v2tag = song.getId3v2Tag();
            byte[] imageData = id3v2tag.getAlbumImage();
            if (imageData != null) {
                System.out.println("debug:: imageData is not null");
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageData));
                icon = new ImageIcon(img);
                icon.getImage().getScaledInstance(5, 5, Image.SCALE_DEFAULT);


            }
        }
    }

    public int getLastTimePlayed() {
        return lastTimePlayed;
    }

    public void setLastTimePlayed(int lastTimePlayed) {
        this.lastTimePlayed = lastTimePlayed;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {

        this.timeDuration = timeDuration;
    }
}

