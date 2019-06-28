

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowPanels extends JPanel implements ActionListener {

    private ArrayList<CellShowJpanel>panels;

    //songs add to play list /play

    //albums /show songs of album/

    //songs of play list /rename playlist /show musics /order musics /play
    //TODO a child of this class that has a button to rename playlist name




    protected ContentPanel contentPanel;
    protected JPanel buttonsPanel;

    private PlayList playList;

    private JButton rename;
    private JButton delete;
    private JButton playTotalArrayButton;
    private JTextField jTextField;





    public ShowPanels()  {
this.setBackground(new Color(22,5,33));

        contentPanel = new ContentPanel();
        buttonsPanel=new JPanel();
        //panels=new ArrayList<>();
        panels=new ArrayList<CellShowJpanel>();


        rename=new JButton("RENAME");
        delete=new JButton("DELETE");
        playTotalArrayButton=new JButton("play all");
        jTextField=new JTextField();
        delete.addActionListener(this);
        rename.addActionListener(this);
        playTotalArrayButton.addActionListener(this);

        contentPanel.setBackground(new Color(0,0,0));
        contentPanel.setLayout(new FlowLayout());
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setBackground(new Color(22,5,33));
        this.setLayout(new BorderLayout());
        this.add(contentPanel,BorderLayout.NORTH);
        this.add(buttonsPanel,BorderLayout.SOUTH);








    }


    private ArrayList<Music> musicArray=new ArrayList<>();

    public void showCellsMethod (ArrayList<CellShowJpanel> cells){
        System.out.println("showCellsMethod");
        //ContentPanel cp=new ContentPanel();

        //this.removeAll();
        for(CellShowJpanel cell : cells){
            contentPanel.add(cell);
        }
        //this.add(contentPanel);

    }

    public void showSongCellMethod (ArrayList<CellSongs> cells){
        System.out.println("showSongCellMethod");
        contentPanel.removeAll();
        musicArray.removeAll(musicArray);


        for(CellSongs cell : cells){
            contentPanel.add(cell);
            musicArray.add(cell.song);
        }

        this.add(contentPanel,BorderLayout.NORTH);
        buttonsPanel.add(playTotalArrayButton);
        this.setVisible(false);
        this.setVisible(true);
    }

    public void showAlbumCellMethod (ArrayList<CallAlbums> cells){
        contentPanel.removeAll();
        for(CallAlbums cell : cells){
            contentPanel.add(cell);
        }
        this.setVisible(false);
        this.setVisible(true);
        }

        public void showSongPlaylist (ArrayList<CellPlaylist> cells,PlayList playList){
        this.playList=playList;
            contentPanel.removeAll();
            musicArray.removeAll(musicArray);
            for(CellPlaylist cell : cells){
                contentPanel.add(cell);
                musicArray.add(cell.song);
            }
            buttonsPanel.add(delete);
            buttonsPanel.add(rename);
            buttonsPanel.add(jTextField);

            buttonsPanel.add(playTotalArrayButton);

            this.setVisible(false);
            this.setVisible(true);

    }

    // TODO 3 ta boolean fals yekish true ke to action li


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==delete){
            AccountManagement.getActiveAccount().removePlaylist(playList);

        }
        else if(e.getSource()==rename){
            String name =jTextField.getText();

            for (ClientPlayList cpl:AccountManagement.getActiveAccount().getClientPlayLists()){
                if (cpl==playList){cpl.editName(name);break;}
            }
        }

        else if(e.getSource()==playTotalArrayButton){
            try {
                AccountManagement.musicBarP.updateList(musicArray);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

