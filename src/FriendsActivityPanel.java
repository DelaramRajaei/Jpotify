import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FriendsActivityPanel extends JPanel {
    private JButton invite;
    private JTextArea IPCode;
    private JPanel invitePanel;
    private JPanel friendsPanel;
    private ArrayList<Friend> friends;



    public FriendsActivityPanel(){
        this.setBackground(new Color(174,225,228));
        this.setBackground(Color.YELLOW);

        invite=new JButton("+ Add Friend" );
        IPCode=new JTextArea();
        invitePanel=new JPanel();
        friendsPanel=new JPanel();
        friends=new ArrayList<Friend>();


        invitePanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        invitePanel.add(IPCode);
        invitePanel.add(invite);

        add(invitePanel,BorderLayout.SOUTH);
        add(friendsPanel,BorderLayout.CENTER);


    }
    public ArrayList<Friend> getFriends(){
        return friends;
    }
    public void setFriends(ArrayList<Friend> friends){
        this.friends=friends;
    }


}
