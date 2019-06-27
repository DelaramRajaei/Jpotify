import javax.security.auth.login.AccountLockedException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CellSongs extends CellShowJpanel implements ActionListener {
    private JButton play;
    private  JMenu addToPlaylist;
    ArrayList<JMenuItem> elementPlaylists;
    Music song;

    public  CellSongs(Music songi){
        super();
        play=new JButton("play");

        this.song=songi;


 //       ImageIcon c=new ImageIcon();
//        c.setImage(song.getImage().getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT));
        //ImageIcon i=new ImageIcon(song.getImage().getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT));
       // song.getImage().setImage(song.getImage().getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT));

//        image.setPreferredSize(new Dimension(150,150));

        image.setIcon(song.getImage());
        l1.setText(song.getName());
        l2.setText("Artist : "+song.getArtist());
        l3.setText("Album : "+song.getAlbum());

        addToPlaylist=new JMenu("Add to playlist");
        elementPlaylists=new ArrayList<>();

        for(PlayList p:AccountManagement.getActiveAccount().getPlayLists()){
            JMenuItem j =new JMenuItem(p.getName());
            elementPlaylists.add(j);
        }

        addToPlaylist.addSeparator();
        JMenuItem j=new JMenuItem("new playlist");
        elementPlaylists.add(j);

        buttonsPanel.add(play);
        buttonsPanel.add(addToPlaylist);

        setAction();

    }



    private void setAction (){
        for(JMenuItem j : elementPlaylists){
            j.addActionListener(this);
        }
        play.addActionListener(this);
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



        if(eve.getSource()==playButton){
            //TODO
            //send songs array lis and song.
            //AccountManagement.musicBarP.


    }

    }





}
