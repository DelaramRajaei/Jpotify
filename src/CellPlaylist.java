import javax.swing.*;
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

        next= new JButton("|>");
        previous= new JButton("|>");
        play= new JButton("Play");
        remove=new JButton("Remove");

        buttonsPanel.add(previous);
        buttonsPanel.add(play);
        buttonsPanel.add(remove);
        buttonsPanel.add(next);

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
            //TODO send to accountManager.musicbar playlist.getMusics and song
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
            AccountManagement.showPanels.showSongPlaylist(ac);

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
            AccountManagement.showPanels.showSongPlaylist(ac);

        }

        else if (e.getSource()==remove){

            AccountManagement.getActiveAccount().removeSongFromPlaylist(playList,song);
            ArrayList<CellPlaylist> ac=new ArrayList<>();
            for (Music m:playList.getMusic()){
                CellPlaylist c = new CellPlaylist(playList,m);
                ac.add(c);
            }
            AccountManagement.showPanels.showSongPlaylist(ac);
        }



    }
}
