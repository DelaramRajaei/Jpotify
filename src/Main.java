import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //UserOpenFrame u=new UserOpenFrame();
        Account a=new Account("Darya");
        AccountManagement.setActiveAccount(a);
        System.out.println(a.getName()+"  hear");
        Core.initialLoadSongs(a.getMusics(),a.getAlbums());


        Account account=new Account("D");
        Core.initialLoad(account.getPlayLists(),account.getAlbums(),account.getMusics());


        RootPanel rootPanel =new RootPanel();


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
