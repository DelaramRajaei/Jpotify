

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
    private CellSongs c1;
    private CellShowJpanel c2;
    private CellShowJpanel c3;
    private CellShowJpanel c4;
    private CellShowJpanel c5;
    private CellShowJpanel c6;
    private CellShowJpanel c7;
    private CellShowJpanel c8;
    private CellShowJpanel c9;
    private CellShowJpanel c0;
    private CellShowJpanel c11;
    private CellShowJpanel c22;
    private CellShowJpanel c33;
    private CellShowJpanel c44;
    private CellShowJpanel c55;
    private PlayList playList;

    private JButton rename;
    private JButton delete;
    private JTextField jTextField;





    public ShowPanels()  {


        contentPanel = new ContentPanel();
        buttonsPanel=new JPanel();
        //panels=new ArrayList<>();
        panels=new ArrayList<CellShowJpanel>();


        rename=new JButton("RENAME");
        delete=new JButton("DELETE");
        jTextField=new JTextField();
        delete.addActionListener(this);
        rename.addActionListener(this);

        contentPanel.setBackground(new Color(0,0,0));
        contentPanel.setLayout(new FlowLayout());
        buttonsPanel.setBackground(new Color(22,5,33));
        this.setLayout(new GridLayout(2,1));
        this.add(contentPanel);
        this.add(buttonsPanel);




      // c1=new CellSongs(AccountManagement.getActiveAccount().getMusics().get(0));
      // c2=new CellShowJpanel();
      // c3=new CellShowJpanel();
      // c4=new CellShowJpanel();
      // c5=new CellShowJpanel();
      // c6=new CellShowJpanel();
      // c7=new CellShowJpanel();
      // c8=new CellShowJpanel();
      // c9=new CellShowJpanel();
      // c0=new CellShowJpanel();
      // c11=new CellShowJpanel();
      // c22=new CellShowJpanel();
      // c33=new CellShowJpanel();
      // c44=new CellShowJpanel();
      // c55=new CellShowJpanel();

      // panels.add(c1);
      // panels.add(c2);
      // panels.add(c3);
      // panels.add(c4);
      // panels.add(c5);
      // panels.add(c6);
      // panels.add(c7);
      // panels.add(c8);
      // panels.add(c0);
      // panels.add(c11);
      // panels.add(c22);
      // panels.add(c33);
      // panels.add(c44);
      // panels.add(c55);
      // showCellsMethod(panels);



    }


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

       // JPanel jp=new JPanel();

        for(CellSongs cell : cells){
            contentPanel.add(cell);
        }
        this.setVisible(false);
        this.setVisible(true);
    }

    public void showAlbumCellMethod (ArrayList<CallAlbums> cells){
        this.removeAll();
        for(CallAlbums cell : cells){
            contentPanel.add(cell);
        }}

        public void showSongPlaylist (ArrayList<CellPlaylist> cells,PlayList playList){
        this.playList=playList;
            this.removeAll();
            for(CellPlaylist cell : cells){
                contentPanel.add(cell);
            }
            this.add(delete);
            this.add(rename);

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
    }
}

