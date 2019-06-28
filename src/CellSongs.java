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


//        elementPlaylists=new ArrayList<>();
//        addToPlaylist = new JPopupMenu();
//        ActionListener menuListener = new ActionListener() {
//            public void actionPerformed(ActionEvent event) {
//                System.out.println("Popup menu item ["
//                        + event.getActionCommand() + "] was pressed.");
//            }
//        };

//        JMenuItem item;
//        for(PlayList p :AccountManagement.getActiveAccount().getPlayLists()) {
//            addToPlaylist.add(item = new JMenuItem();
//            item.setHorizontalTextPosition(JMenuItem.RIGHT);
//            item.addActionListener(menuListener);
//        }

        addToPlaylist=new JPopupMenu("Add to playlist");
        for(PlayList p : AccountManagement.getActiveAccount().getPlayLists()){
            p.addsongActionListenerItemOfMenuHelloWorld(song);
            addToPlaylist.add(p.getjMenuItem());


        }

        addToPlaylist.addSeparator();
        JMenuItem j=new JMenuItem("new playlist");
//        elementPlaylists.add(j);
        play.addActionListener(this);

        buttonsPanel.add(play);
        addToPlaylist.setPopupSize(new Dimension(100,100));
        addToPlaylist.setBackground(new Color(122,133,200));
        buttonsPanel.add(addToPlaylist);


    }




    @Override
    public void actionPerformed(ActionEvent eve)
    {
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
