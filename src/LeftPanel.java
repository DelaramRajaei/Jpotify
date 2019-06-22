import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private static final int WIDTH = 250, HEIGHT = 1000;

    private JPanel currentMusicPanel;//make a class for this panel. have pic of song name of artist and name of song
    private JPanel menuPanel;
    private JButton newMusic;
    private JButton createPlayList;
    private JButton albums;
    private JButton songs;
    private JButton playlists;

    public LeftPanel(){
        super();
//        newMusic=new JButton("new");
//        this.add(newMusic);
        this.setBackground(Color.YELLOW);
        menuPanel=new JPanel();
        menuPanel.setBackground(Color.DARK_GRAY);
        currentMusicPanel=new JPanel();
        currentMusicPanel.setBackground(Color.DARK_GRAY);
        newMusic=new JButton("+ New Music ");
        newMusic.setBackground(Color.PINK);
        createPlayList=new JButton("+ New Playlist ");
        createPlayList.setBackground(Color.PINK);
        albums=new JButton("Abums");
        songs=new JButton("Songs");
        playlists=new JButton("PlayLists");



        menuPanel.add(songs);
        menuPanel.add(albums);
        menuPanel.add(playlists);
        this.add(menuPanel);
        this.add (newMusic);
        this.add(createPlayList);
        this.add(currentMusicPanel);


        setVisible(true);




    }

}
