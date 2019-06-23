
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RootPanel extends JFrame {
    private Account account;
    private ArrayList<Account> accounts;

    private JPanel upPanel=new JPanel();


    private MusicBarPanel musicBar;
    private LeftPanel leftPanel;
    private FriendsPanel friendsPanel;
    private ToolBarPanel toolBarPanel;
    private ShowPanels showPanels;




    private static final int WIDTH = 1000, HEIGHT = 700;

   // public RootPanel(Account account,ArrayList<Account> accounts){
    public RootPanel(){
        super();
        this.account=account;//````````
        this.accounts=accounts;//````

      //  toolBarPanel=new ToolBarPanel(account.getName());
      //  friendsPanel=new FriendsPanel(accounts);
        musicBar=new MusicBarPanel();
        leftPanel=new LeftPanel();
        friendsPanel=new FriendsPanel();
        toolBarPanel=new ToolBarPanel();
        showPanels=new ShowPanels();
        setLayout(new BorderLayout());
        upPanel.setLayout(new BorderLayout());
        upPanel.setVisible(true);
        this.add(upPanel);



        this.add(musicBar,BorderLayout.SOUTH);
        upPanel.add(leftPanel,BorderLayout.WEST);
        upPanel.add(friendsPanel,BorderLayout.EAST);
        upPanel.add(toolBarPanel,BorderLayout.NORTH);
        upPanel.add(showPanels,BorderLayout.CENTER);



       // this.setContentPane(leftPanel); // <<<<====>>>>>     // age ino az commenti dar biary hichi neshon nmide :(
        //this.setContentPane(friendsPanel);
        this.pack();
        //setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
    }



}
