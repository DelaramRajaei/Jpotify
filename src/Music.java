import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Base64;


public class Music {
    private ImageIcon image;
    private String name;
    private String artist;
    private String album;
    private String directory;
    private int year;
    private ArrayList<PlayList> playLists;


    public Music(String directory) {
        playLists = new ArrayList<PlayList>();
        try {
            File file = new File(directory);
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
        return image;
    }
}

