import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CellPlaylist extends CellShowJpanel implements ActionListener {

    JButton next;
    JButton previous;
    JButton play;
    JButton remove;

    PlayList playList;
    Music song;

    public CellPlaylist(PlayList p,Music song)
    {
        super();

        playList=p;
        this.song=song;


        ImageIcon c=song.getImage() ;
        ImageIcon i=new ImageIcon(c.getImage().getScaledInstance(400,400, Image.SCALE_DEFAULT));
        image.setPreferredSize(new Dimension(400,400));

        image.setIcon(i);
        l1.setText(song.getName());
        l2.setText("Artist : "+song.getArtist());
        l3.setText("Album : "+song.getAlbum());


        next= new JButton("|>");
        previous= new JButton("<|");
        play= new JButton("Playi");
        remove=new JButton("Remove");

        if(!(playList.getMusic().get(0)==song))buttonsPanel.add(previous);
        buttonsPanel.add(play);
        buttonsPanel.add(remove);
        if(!(playList.getMusic().get((playList.getMusic().size())-1)==song))buttonsPanel.add(next);


        setAction();





    }


    private  void setAction (){
        play.addActionListener(this);
        previous.addActionListener(this);
        next.addActionListener(this);
        remove.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==play){
            ArrayList<Music> musicArray = new ArrayList();
            musicArray.add(song);
            try {
                AccountManagement.musicBarP.updateList(musicArray);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        else if(e.getSource()==next){
            int i=0;
             for(Music m:playList.getMusic()){
                 if(m==song){ break; }
                 i++;
             }
             Music temp=playList.getMusic().get(i);
             playList.getMusic().set(i,playList.getMusic().get(i+1));
             playList.getMusic().set(i+1,temp);


            ArrayList<CellPlaylist> ac=new ArrayList<>();
            for (Music m:playList.getMusic()){
                CellPlaylist c = new CellPlaylist(playList,m);
                ac.add(c);
            }
            AccountManagement.showPanels.showSongPlaylist(ac,playList);

        }
        else if(e.getSource()==previous){
            int i=0;
            for(Music m:playList.getMusic()){
                if(m==song){ break; }
                i++;
            }
            Music temp=playList.getMusic().get(i);
            playList.getMusic().set(i,playList.getMusic().get(i-1));
            playList.getMusic().set(i-1,temp);


            ArrayList<CellPlaylist> ac=new ArrayList<>();
            for (Music m:playList.getMusic()){
                CellPlaylist c = new CellPlaylist(playList,m);
                ac.add(c);
            }
            AccountManagement.showPanels.showSongPlaylist(ac,playList);

        }

        else if (e.getSource()==remove){

            AccountManagement.getActiveAccount().removeSongFromPlaylist(playList,song);
            ArrayList<CellPlaylist> ac=new ArrayList<>();
            for (Music m:playList.getMusic()){
                CellPlaylist c = new CellPlaylist(playList,m);
                ac.add(c);
            }
            AccountManagement.showPanels.showSongPlaylist(ac,playList);
        }



    }
}
