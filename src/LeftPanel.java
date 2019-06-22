import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private static final int WIDTH = 250, HEIGHT = 1000;

    private JPanel currentMusicPanel;//make a class for this panel. have pic of song name of artist and name of song
    private JPanel menuPanel;//make a class (album,songs,playLists)
    private JButton newMusic;
    private JButton createPlayList;
    //private JButton albums;
    //private JButton songs;
    //private JButton playlists;

    public LeftPanel(){
        super();
        newMusic=new JButton("new");
        this.add(newMusic);
        /*this.setBackground(Color.YELLOW);
        menuPanel=new JPanel();
        menuPanel.setBackground(Color.DARK_GRAY);
        currentMusicPanel=new JPanel();
        currentMusicPanel.setBackground(Color.DARK_GRAY);
        newMusic=new JButton("+ New Music ");
        newMusic.setBackground(Color.PINK);
        createPlayList=new JButton("+ New Playlist ");
        createPlayList.setBackground(Color.PINK);
        //albums=new JButton("Abums");
        //songs=new JButton("Songs");
        //playlists=new JButton("PlayLists");

       add (newMusic);
       add(createPlayList);
       add(menuPanel);
       add(currentMusicPanel);

       menuPanel.setBounds(0,50,250,500);
       newMusic.setBounds(25,600,200,50);
       createPlayList.setBounds(25,650,200,50);
       currentMusicPanel.setBounds(0,750,250,300);

       add(menuPanel);
       add(newMusic);
       add(currentMusicPanel);
       add(createPlayList);

        setVisible(true);
        setLayout(null);
        this.setSize(WIDTH, HEIGHT);

*/
    }

}
