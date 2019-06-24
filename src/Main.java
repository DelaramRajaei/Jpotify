import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Account account=new Account("D");
        Core.initialLoad(account.getPlayLists(),account.getAlbums(),account.getMusics());
        account.addMusic("E:\\Codes\\Java\\Project\\Jpotify\\src\\Musics\\m.mp3");//0
        account.createPlayList("Ambrose");
        account.addSongToPlayList(account.getPlayLists().get(2),account.getMusics().get(0));
        account.addMusic("E:\\Codes\\Java\\Project\\Jpotify\\src\\Musics\\01 - Rolling In The Deep.mp3");//1
        account.addMusic("E:\\Codes\\Java\\Project\\Jpotify\\src\\Musics\\Lily Allen.fuck you.mp3");//2
        account.addSongToPlayList(account.getPlayLists().get(0),account.getMusics().get(1));
        account.addSongToPlayList(account.getPlayLists().get(0),account.getMusics().get(2));
        System.out.println("Added successfully!");
        account.removePlaylist(account.getPlayLists().get(2));
        account.removeSongFromPlaylist(account.getPlayLists().get(0),account.getMusics().get(2));
        account.removeSong(account.getMusics().get(0));
        //RootPanel rootPanel = new RootPanel();

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
