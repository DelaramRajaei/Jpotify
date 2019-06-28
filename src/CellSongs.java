import javax.security.auth.login.AccountLockedException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CellSongs extends CellShowJpanel implements ActionListener {
    private JButton play;
    private  JPopupMenu addToPlaylist;
    ArrayList<JMenuItem> elementPlaylists;
    Music song;

    public  CellSongs(Music songi){
        super();
        play=new JButton("play");

        this.song=songi;


        ImageIcon u=new ImageIcon();
        u.setImage(song.getImage().getImage().getScaledInstance(400,400,Image.SCALE_DEFAULT));

        image.setIcon(u);
        l1.setText(song.getName());
        l2.setText("Artist : "+song.getArtist());
        l3.setText("Album : "+song.getAlbum());

        addToPlaylist=new JPopupMenu("Add to playlist");
        elementPlaylists=new ArrayList<>();

        for(PlayList p:AccountManagement.getActiveAccount().getPlayLists()){
            JMenuItem j =new JMenuItem(p.getName());
            elementPlaylists.add(j);
        }

        addToPlaylist.addSeparator();
        JMenuItem j=new JMenuItem("new playlist");
        elementPlaylists.add(j);

        buttonsPanel.add(play);
        play.addActionListener(this);

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
            if(eve.getSource()==p.getName()){//TODO is this line true?
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



        if(eve.getSource()==play){
            ArrayList<Music> musicArray = new ArrayList();
            musicArray.add(song);
            try {
                AccountManagement.musicBarP.updateList(musicArray);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }





}
