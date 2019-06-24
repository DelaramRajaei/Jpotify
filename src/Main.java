import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Account a=new Account("darya");
        AccountManagement.setActiveAccount(a);
        Core.initialLoadSongs(a.getMusics(),a.getAlbums());
        System.out.println(a.getMusics().get(0).getName());

        Account account=new Account("D");
        Core.initialLoad(account.getPlayLists(),account.getAlbums(),account.getMusics());

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
