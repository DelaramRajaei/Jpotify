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

        //Core.initialLoad();
        ServerSocket serverSocket = new ServerSocket(6666);
        Socket socket = new Socket("192.168.1.8", 6666);

        socket.getLocalPort();
        socket.getPort();
        SocialNetwork socialNetwork = new SocialNetwork(AccountManagement.getActiveAccount().getFriends(), AccountManagement.getActiveAccount());
        socialNetwork.startServer();
        socialNetwork.sendStatusToAll(SocialNetwork.UserStatus.Online);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        for (Friend friend : AccountManagement.getActiveAccount().getFriends())
                            //Period.between(LocalDate.now(),friend.getLastCommunicationDate())
                            // if ((new BaseCalendar.Date()) - friend.getLastCommunicationDate() >)
                            socialNetwork.askStatus(friend.getIP());
                        Thread.sleep(30000);
                    }
                } catch (Exception err) {
                }
            }
        }).start();
        //ArrayList<Music> am=new ArrayList<>();
        //am.add(AccountManagement.getActiveAccount().getMusics().get(0));
        //AccountManagement.musicBarP.updateList(am);


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

    public static void showPanel() {
        RootPanel rootPanel = new RootPanel();
    }

}
