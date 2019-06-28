/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Darya
 */
public class LeftPanel extends javax.swing.JPanel implements ActionListener{

    private DefaultListModel<PlayList> model = new DefaultListModel<>();

    private JButton addMusic;
    private JButton albumsButton;
    private JButton addPlaylist;

    private JScrollPane jScrollPanePlayListList;
    private JButton songsButton;
    private JList<PlayList> jplaylist;




    public static String audioFilePath;
    private String lastOpenPath;


    public LeftPanel() {
        jplaylist=new JList<>();
        setJlistPlayList();
        jplaylist.setForeground(new Color(171, 117, 188));
        jplaylist.setBackground(new Color(51, 51, 51));

        initComponents();
        this.setPreferredSize(new Dimension(150,500));

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        songsButton = new javax.swing.JButton();
        albumsButton = new javax.swing.JButton();
        addMusic = new javax.swing.JButton();
        addPlaylist = new javax.swing.JButton();
        jScrollPanePlayListList = new javax.swing.JScrollPane();
        //jListPlayListList = new javax.swing.JList<>();

        setBackground(new java.awt.Color(51, 51, 51));
        setMinimumSize(new java.awt.Dimension(120, 500));
        setLayout(new java.awt.GridBagLayout());

        songsButton.setBackground(new java.awt.Color(51, 51, 51));
        songsButton.setForeground(new java.awt.Color(255, 255, 255));

        ImageIcon songIcon = new ImageIcon(this.getClass().getResource("images/song.png"));
        songsButton.setIcon(songIcon); // NOI18N
        songsButton.setText("Songs");
        songsButton.setToolTipText("Show songs");
        songsButton.setBorder(null);
        songsButton.setFocusable(false);
        songsButton.addActionListener(this);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(65, 11, 0, 0);
        add(songsButton, gridBagConstraints);

        albumsButton.setBackground(new java.awt.Color(51, 51, 51));
        albumsButton.setForeground(new java.awt.Color(255, 255, 255));
        ImageIcon albumIcon = new ImageIcon(this.getClass().getResource("images/album.png"));
        albumsButton.setIcon(albumIcon); // NOI18N
        albumsButton.setText("Albums");
        albumsButton.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = -12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 13, 0, 25);
        add(albumsButton, gridBagConstraints);

        addMusic.setBackground(new java.awt.Color(51, 51, 51));
        addMusic.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        addMusic.setForeground(new java.awt.Color(255, 255, 255));
        addMusic.setText("+ MUSIC");
        addMusic.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(61, 22, 0, 0);
        add(addMusic, gridBagConstraints);

        addPlaylist.setBackground(new java.awt.Color(51, 51, 51));
        addPlaylist.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        addPlaylist.setForeground(new java.awt.Color(255, 255, 255));
        addPlaylist.setText(" + PLAYLIST");
        addPlaylist.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 22, 0, 0);
        add(addPlaylist, gridBagConstraints);

        jplaylist.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        //jListPlayListList.setModel(new javax.swing.AbstractListModel<String>() {
        //    String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
        //    public int getSize() { return strings.length; }
        //    public String getElementAt(int i) { return strings[i]; }
        //});
        //jListPlayListList.setFocusable(false);
        jScrollPanePlayListList.setViewportView(jplaylist);
        //jListPlayListList.setBackground(new Color(51,51,51));
        //jListPlayListList.setForeground(new Color(241, 254, 255));


        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 119;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(7, 22, 13, 0);
        add(jScrollPanePlayListList, gridBagConstraints);


        addPlaylist.addActionListener( this);
        addMusic.addActionListener(this);
        albumsButton.addActionListener(this);












    }





    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==addPlaylist){

          //  NewPlayList newPlaylistPanel=new NewPlayList();
//
            String n=JOptionPane.showInputDialog("playlist name : ");
            AccountManagement.getActiveAccount().createPlayList(n);
          //  for(int i=0;i<1000000;i++){
          //      System.out.println(i);
          //  }
//while(!newPlaylistPanel.isFinish()){}

                System.out.println("new Playlist: ");

                 int x= AccountManagement.getActiveAccount().getPlayLists().size();
                model.addElement(AccountManagement.getActiveAccount().getPlayLists().get(x - 1));
                System.out.println(AccountManagement.getActiveAccount().getPlayLists().get(x - 1).getName());
                jplaylist.setVisible(false);
                jplaylist.setVisible(true);










        }else




            if(source==addMusic){
                try {
                    openFile();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                ArrayList<CellSongs> acs=new ArrayList<>();
                for(Music m:AccountManagement.getActiveAccount().getMusics()){
                    CellSongs cs =new CellSongs(m);
                    acs.add(cs);
                }
                AccountManagement.showPanels.showSongCellMethod(acs);




            }
            else if(source==songsButton){
                ArrayList<CellSongs> acs=new ArrayList<>();
                for(Music m:AccountManagement.getActiveAccount().getMusics()){
                    CellSongs cs =new CellSongs(m);
                    acs.add(cs);
                }
                AccountManagement.showPanels.showSongCellMethod(acs);
            }else
                if(source==albumsButton){

                    ArrayList<CallAlbums> aca = new ArrayList<>();
                    for(Album a:AccountManagement.getActiveAccount().getAlbums()){
                        CallAlbums ca = new CallAlbums(a);
                        aca.add(ca);
                    }
                    AccountManagement.showPanels.showAlbumCellMethod(aca);
                }

    }

    public void openFile()throws Exception{
        System.out.println("open pressed");
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("MP3 File","mp3");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Open Audio File");
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            audioFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            lastOpenPath = fileChooser.getSelectedFile().getParent();
        }
        System.out.println(audioFilePath);
        //Music m=new Music(audioFilePath);
        //Account a=new Account("darya");
        //a.addMusic(m.getDirectory());
        AccountManagement.getActiveAccount().addMusic(audioFilePath);

    }

    private void setJlistPlayList(){


        //DefaultListModel<PlayList> model = new DefaultListModel<>();
        jplaylist.setModel(model);

        for(PlayList p:AccountManagement.getActiveAccount().getPlayLists()){
            model.addElement(p);
        }


        jplaylist.getSelectionModel().addListSelectionListener(e -> {
            System.out.println("darya action");
            PlayList p = jplaylist.getSelectedValue();
            AccountManagement.showPanels.showSongPlaylist(actionJlistPlaylist(p),p);


        });



    }


    private ArrayList<CellPlaylist> actionJlistPlaylist(PlayList p){
        ArrayList<CellPlaylist>cshp=new ArrayList<>();
        for (Music m:p.getMusic()){
            CellPlaylist cp=new CellPlaylist(p,m);
            cshp.add(cp);
        }
        return cshp;
    }




}
