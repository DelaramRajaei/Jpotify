import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CellSongs extends CellShowJpanel {
    private JButton play;
    private  JMenu addToPlaylist;
    ArrayList<JMenuItem> elementPlaylists;
    Music song;

    public  CellSongs(Music song){
        super();

        this.song=song;

        addToPlaylist=new JMenu("Add to playlist");
        elementPlaylists=new ArrayList<>();

        for(PlayList p:AccountManagement.getActiveAccount().getPlayLists()){
            JMenuItem j =new JMenuItem(p.getName());
            elementPlaylists.add(j);
        }

        addToPlaylist.addSeparator();
        JMenuItem j=new JMenuItem("new playlist");
        elementPlaylists.add(j);

        buttonsPanel.add(addToPlaylist);

        setAction();

    }

    private void setAction (){
        for(JMenuItem j : elementPlaylists){
            j.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent eve)
    {

        for(PlayList p : AccountManagement.getActiveAccount().getPlayLists()){
            if(eve.getSource()==p.getName()){
                p.addSong(song);
                break;
            }
        }
        if(eve.getSource()=="new playlist"){
            NewPlayList npl=new NewPlayList();

            AccountManagement.getActiveAccount().createPlayList(npl.getPlaylistName());

            for(PlayList p : AccountManagement.getActiveAccount().getPlayLists()){
                if(npl.getPlaylistName()== p.getName()){
                    AccountManagement.getActiveAccount().addSongToPlayList(p,song);
                    break;
                }
            }


        }

    }





}
