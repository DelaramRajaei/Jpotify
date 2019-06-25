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

    public FriendPanel(Account friend){

        friendNameButton=new JButton(friend.getName());
        songName=new JLabel(friend.getMusics().get(0).getName());
        artistName=new JLabel(friend.getMusics().get(0).getArtist());
        albumName=new JLabel(friend.getMusics().get(0).getAlbum());

        sharedPlayList=new SharedPlayList();
        sharedPlayList=friend.getSharedPlayLists();
        musicPanels=new ArrayList<>(sharedPlayList.musics.size());
        musicsArray=new ArrayList<>(sharedPlayList.musics.size());


        int i=0;

       for(Music m:musicsArray){
           musicPanels.get(i).displayMusic(m);
           i++;
       }

        friendNameButton.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ShowPanels show =new ShowPanels();
                show.ShowCellsMethod(musicPanels);
    }
}
