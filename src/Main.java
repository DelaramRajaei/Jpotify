import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

       // Account a = new Account("Delaram");
       // Core.initialLoad(a);
        // UserOpenFrame userOpenFrame = new UserOpenFrame();
       // a.addMusic("E:\\Codes\\Java\\Project\\Jpotify\\src\\Musics\\01 - Rolling In The Deep.mp3");//0

        RootPanel rootPanel = new RootPanel();

      /*  //add music
        a.addMusic("E:\\Codes\\Java\\Project\\Jpotify\\src\\Musics\\Lily Allen.fuck you.mp3");//1
        a.addMusic("E:\\Codes\\Java\\Project\\Jpotify\\src\\Musics\\m.mp3");//2

        //add playlist
        a.createPlayList("Deli");//2
        //add song to playlist
        a.addSongToPlayList(a.getPlayLists().get(0),a.getMusics().get(0));
        a.addSongToPlayList(a.getPlayLists().get(0),a.getMusics().get(1));
        a.addSongToPlayList(a.getPlayLists().get(2),a.getMusics().get(1));
        //remove a single song
        a.removeSong(a.getMusics().get(2));
        //remove song from playlist
        a.removeSongFromPlaylist(a.getPlayLists().get(0),a.getMusics().get(1));
        //remove playlist
        a.removePlaylist(a.getPlayLists().get(2));*/

        Core.savePlaylistFileName();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //     new RootPanel().setVisible(true);
            }
        });


    }

}
