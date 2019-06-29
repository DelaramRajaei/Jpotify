import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {


        Core.initialLoad();

    }

    public static void showPanel() {
        RootPanel rootPanel = new RootPanel();
    }

    public static void networkInitial()throws Exception {

        SocialNetwork socialNetwork = new SocialNetwork(AccountManagement.getActiveAccount().getFriends(), AccountManagement.getActiveAccount());
        socialNetwork.startServer();
        socialNetwork.sendStatusToAll(SocialNetwork.UserStatus.Online);
        //socialNetwork.sendInvitation("192.168.1.7");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        for (Friend friend : AccountManagement.getActiveAccount().getFriends())
                            socialNetwork.askStatus(friend.getIP());
                        Thread.sleep(30000);
                    }
                } catch (Exception err) {
                }
            }
        }).start();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
