import com.sun.security.ntlm.Client;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sun.util.calendar.BaseCalendar;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {


        Account account = Core.initialLoad();

     /*   Friend friend = new Friend();
        friend.setIP("127.0.0.1");
        friend.setName("Delaram");
        AccountManagement accountManagement = new AccountManagement(account);
        account.addFriend(friend);
        SocialNetwork socialNetwork = new SocialNetwork(account.getFriends(), account);
        socialNetwork.startServer();
        socialNetwork.sendStatusToAll(SocialNetwork.UserStatus.Online);
        socialNetwork.askForMusic(friend.getIP(), "Rolling In The Deep");
        account.createPlayList("FriendsPlaylist");
        account.addSongToPlayList(account.getPlayLists().get(2),socialNetwork.getNewMusics().get(0));

*/

       /* new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    while (true) {
                        for (Friend friend : a.getFriends())
                            //Period.between(LocalDate.now(),friend.getLastCommunicationDate())
                           // if ((new BaseCalendar.Date()) - friend.getLastCommunicationDate() >)
                                socialNetwork.askStatus(friend.getIP());
                    }
                    Thread.sleep(30000);
                }catch(Exception err)
                {}
            }
        }).start();*/


        RootPanel rootPanel = new RootPanel();
        //AccountManagement.toolBarPanel.setUserUame();


        //ArrayList<Music> am=new ArrayList<>();
        //am.add(AccountManagement.getActiveAccount().getMusics().get(0));
        //AccountManagement.musicBarP.updateList(am);
        Core.saveAll(account);


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
