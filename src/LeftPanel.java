import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {

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
        this.setLayout(new GridLayout(4,1));
        menuPanel=new JPanel();
        menuPanel.setBackground(Color.DARK_GRAY);
        menuPanel.setLayout(new GridLayout(3,1));
        currentMusicPanel=new JPanel();
        currentMusicPanel.setBackground(new Color(174,225,228));

        newMusic=new JButton("+ New Music ");
        newMusic.setBackground(Color.GRAY);
        createPlayList=new JButton("+ New Playlist ");
        createPlayList.setBackground(Color.GRAY);
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
