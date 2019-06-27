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

//TODO give ip of devic
        UserOpenFrame UOF = new UserOpenFrame(123);

        while (!UOF.finish){

        }
        String accountName=UOF.getUserNAme();

        Account a = new Account(accountName);
        a.setIP(UOF.getIP());

        AccountManagement accountManagement=new AccountManagement(a);
        Core.initialLoad(a);




        SocialNetwork socialNetwork = new SocialNetwork(a.getFriends(),a);
        socialNetwork.startServer();
        socialNetwork.sendStatusToAll(SocialNetwork.UserStatus.Online);
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

        AccountManagement.toolBarPanel.setUserUame();


        //ArrayList<Music> am=new ArrayList<>();
        //am.add(AccountManagement.getActiveAccount().getMusics().get(0));
        //AccountManagement.musicBarP.updateList(am);
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
