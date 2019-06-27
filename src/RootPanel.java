


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RootPanel extends JFrame {
    private Account account;
    private ArrayList<Account> accounts;

    private JPanel upPanel=new JPanel();


    private MusicBarP musicBar;
    private LeftPanel leftPanel;
    private FriendsActivityPanel friendsPanel;
    private ToolBarPanel toolBarPanel;
   // private ShowPanels showPanels;




    private static final int WIDTH = 1000, HEIGHT = 700;


    public RootPanel(){
        super();
        this.account=account;//````````
        this.accounts=accounts;//````
       // setMinimumSize(new Dimension(1000,1000));
      //  toolBarPanel=new ToolBarPanel(account.getName());
      //  friendsPanel=new FriendsPanel(accounts);
        musicBar=new MusicBarP();
        AccountManagement.musicBarP=musicBar;
        leftPanel=new LeftPanel();
        AccountManagement.leftPanel=leftPanel;
        friendsPanel=new FriendsActivityPanel();
        AccountManagement.friendsActivityPanel=friendsPanel;
        toolBarPanel=new ToolBarPanel();
     //   showPanels=new ShowPanels();
       // AccountManagement.showPanels=showPanels;
        AccountManagement.rootPanel=this;

       // JScrollPane scrollPane=new JScrollPane(showPanels,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        //scrollPane.setPreferredSize(new Dimension(1000,1000));
        //scrollPane.setVisible(true);
       // this.add(scrollPane);


        setLayout(new BorderLayout());
        upPanel.setLayout(new BorderLayout());
        upPanel.setVisible(true);
        this.add(upPanel,BorderLayout.CENTER);







        this.add(musicBar,BorderLayout.SOUTH);
        upPanel.add(leftPanel,BorderLayout.WEST);
        upPanel.add(friendsPanel,BorderLayout.EAST);
        upPanel.add(toolBarPanel,BorderLayout.NORTH);
       // upPanel.add(scrollPane,BorderLayout.CENTER);



       // this.setContentPane(leftPanel); // <<<<====>>>>>     // age ino az commenti dar biary hichi neshon nmide :(
        //this.setContentPane(friendsPanel);
        this.pack();
        //setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        //this.setExtendedState(this.getState()| JFrame.MAXIMIZED_BOTH);
    }



}
