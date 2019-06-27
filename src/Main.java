import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.util.calendar.BaseCalendar;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {


        // UserOpenFrame userOpenFrame = new UserOpenFrame();
        Account a = new Account("Delaram");
        // a.addMusic("E:\\Codes\\Java\\Project\\Jpotify\\src\\Musics\\01 - Rolling In The Deep.mp3");//0
       // RootPanel rootPanel = new RootPanel();

        Core.initialLoad(a);
        SocialNetwork socialNetwork = new SocialNetwork(a.getFriends(),a);
        socialNetwork.startServer();
        socialNetwork.sendStatusToAll(SocialNetwork.UserStatus.Online);
        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    while (true) {
                        for (Friend friend : a.getFriends())
                            //Period.between(LocalDate.now(),friend.getLastCommunicationDate())
                            if ((new BaseCalendar.Date()) - friend.getLastCommunicationDate() >)
                                socialNetwork.askStatus(friend.getIP());
                    }
                    Thread.sleep(30000);
                }catch(Exception err)
                {}
            }
        }).start();


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
