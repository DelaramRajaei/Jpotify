import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FriendPanel extends JPanel implements ActionListener {

    private JButton friendNameButton;
    private JLabel songName;
    private JLabel artistName;
    private JLabel albumName;
    private SharedPlayList sharedPlayList;
    private ArrayList<CellShowJpanel> musicPanels;
    private ArrayList<Music> musicsArray;

    public FriendPanel(Friend friend){

        friendNameButton=new JButton(friend.getName());
        songName=new JLabel(friend.getMusic());
        artistName=new JLabel(friend.getArtist());
        albumName=new JLabel(friend.getAlbum());

        musicPanels=new ArrayList<>(sharedPlayList.musics.size());
        musicsArray=new ArrayList<>(sharedPlayList.musics.size());


        int i=0;

       for(Music m:musicsArray){
           //TODO handle error
          // musicPanels.get(i).showCellsMethod(m);
           i++;
       }

        friendNameButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ShowPanels show =new ShowPanels();
                show.showCellsMethod(musicPanels);
    }
}
