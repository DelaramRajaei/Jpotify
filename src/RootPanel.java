
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RootPanel extends JFrame {
    private Account account;
    private ArrayList<Account> accounts;


    private MusicBarPanel musicBar;
    private LeftPanel leftPanel;
    private FriendsPanel friendsPanel;
    private ToolBarPanel toolBarPanel;
    private ShowPanels showPanels;



    private static final int WIDTH = 1000, HEIGHT = 1050;

    public RootPanel(Account account,ArrayList<Account> accounts){
        super();
        this.account=account;//````````
        this.accounts=accounts;//````

        toolBarPanel=new ToolBarPanel(account.getName());
        friendsPanel=new FriendsPanel(accounts);
        musicBar=new MusicBarPanel();
        leftPanel=new LeftPanel();
        setLayout(new BorderLayout());


        this.add(musicBar,BorderLayout.SOUTH);
        this.add(leftPanel,BorderLayout.EAST);

        this.setContentPane(musicBar);

       // this.setContentPane(leftPanel); // <<<<====>>>>>     // age ino az commenti dar biary hichi neshon nmide :(
        this.pack();
        //setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
    }



}
