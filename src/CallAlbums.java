import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CallAlbums extends CellShowJpanel implements ActionListener
{

    JButton show;
    Album album;

    public CallAlbums (Album album){
        super();
        this.album=album;
        show= new JButton("show songs");

        ImageIcon c=album.getAlbumCover() ;
        ImageIcon i=new ImageIcon(c.getImage().getScaledInstance(400,400, Image.SCALE_DEFAULT));
        image.setPreferredSize(new Dimension(400,400));
        image.setIcon(i);

        l1.setText(album.getAlbumName());
        l2.setText("Artist : "+album.getArtistName());
        l3.setText("Songs Number : "+album.getMusics().size());


        buttonsPanel.add(show);
        show.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

        ArrayList<CellSongs> songs=new ArrayList<>();
        for(Music m:album.getMusics())
        {
            CellSongs c = new CellSongs(m);
            songs.add(c);

        }
        AccountManagement.showPanels.showSongCellMethod(songs);

    }
}
