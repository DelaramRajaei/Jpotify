/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Darya
 */
public class LeftPanel extends javax.swing.JPanel implements ActionListener{




    public static String audioFilePath;
    private String lastOpenPath;


    public LeftPanel() {

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
        playlistMenu= new JList<String>();
        playlistMenu.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

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
        songsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songsButtonActionPerformed(evt);
            }
        });
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


        addPlaylist.addActionListener((ActionListener) this);
        addMusic.addActionListener(this);












    }

    private void songsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songsButtonActionPerformed
        // TODO add your handling code here:
    }


    private javax.swing.JButton addMusic;
    private javax.swing.JButton albumsButton;
    private javax.swing.JButton addPlaylist;
    private JList playlistMenu;

    private javax.swing.JButton songsButton;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==addPlaylist){
            DefaultListModel DLM = new DefaultListModel();
            NewPlayList newPlaylistPanel=new NewPlayList();
            newPlaylistPanel.setVisible(true);
            AccountManagement.getActiveAccount().createPlayList(newPlaylistPanel.getPlaylistName());
            System.out.println("new Playlist: "+newPlaylistPanel.getPlaylistName());
            DefaultListModel defaultListModel = new DefaultListModel();
            /*for(PlayList p:AccountManagement.getActiveAccount().getPlayLists()){
                defaultListModel.addElement(p.getName());
            }*/

            int len=AccountManagement.getActiveAccount().getPlayLists().size();
            String[] playlistsName=new String[len];
            int i=0;
            for (PlayList p:AccountManagement.getActiveAccount().getPlayLists()) {
                playlistsName[i] = p.getName();
                i++;
            }

        }else
            if(source==addMusic){
                openFile();
        }

    }

    public void openFile(){
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

        AccountManagement.getActiveAccount().addMusic(audioFilePath);

    }






}
