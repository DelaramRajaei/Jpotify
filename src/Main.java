import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Account account=new Account("D");
        ArrayList<Music> musics = new ArrayList<Music>();
        Core.initialLoad(account.getMusics(),account.getPlayLists());
        RootPanel rootPanel = new RootPanel();


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
