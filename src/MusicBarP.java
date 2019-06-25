/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Darya
 */
public class MusicBarP extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form MusicBarP
     */


    private ArrayList<Music> musicArray;

    Thread playThread ;
    Thread resumeThread ;

    private int cnt=0;
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    File myFile=null;
    String filename;
    String filePath;
    long totalLength;
    long pause;
    Player player;




    private JPanel buttonsPanel;
    private JPanel songDetail;
    private JPanel currentMusic;
    private JLabel labelDuration;
    private JLabel labelTimeCounter;
    private JLabel musicArtist;
    private JLabel musicImage;
    private JLabel musicName;
    private JLabel musicPublishYear;
    private JLabel musicAlbum;
    private JSlider musicSlider;
    private JButton next;
    private JButton play;
    private JPanel powerVoic;
    private JLabel powerVoicLable;
    private JSlider powerVoicSlider;
    private JButton previous;
    private JButton rePlay;
    private JButton shuffle;
    private JPanel sliderPanel;
    private JPanel vasatPanel;



    private boolean isPlaying = false;
    private boolean isReplay = false;
    private boolean isShuffle = false;

    private ImageIcon playIcon = new ImageIcon(this.getClass().getResource("images/play.png"));
    private ImageIcon pauseIcon = new ImageIcon(this.getClass().getResource("images/paus.png"));
    private ImageIcon nextIcon = new ImageIcon(this.getClass().getResource("images/next.png"));
    private ImageIcon previousIcon = new ImageIcon(this.getClass().getResource("images/previous.png"));
    private ImageIcon shuffleoff = new ImageIcon(this.getClass().getResource("images/shuffleOff.png"));
    private ImageIcon shuffleOn = new ImageIcon(this.getClass().getResource("images/shuffleOn.png"));
    private ImageIcon replayOn = new ImageIcon(this.getClass().getResource("images/replayOn.png"));
    private ImageIcon replayOff = new ImageIcon(this.getClass().getResource("images/replayOff.png"));
    private ImageIcon cover = new ImageIcon(this.getClass().getResource("images/cover.png"));
    private ImageIcon voicIcon = new ImageIcon(this.getClass().getResource("images/voice.png"));







    public MusicBarP() {
        initComponents();

        musicArray = new ArrayList<>();

        playThread =new Thread(runnablePlay);
        resumeThread =new Thread(resumeThread);





        musicSlider.setValue(0);

        play.setIcon(playIcon);
        play.setFont(new Font("Sans", Font.BOLD, 14));
        play.setBackground(new Color(51,51,51));
        play.setIcon(playIcon);
        play.setBorder(null);


        rePlay.setFont(new Font("sans",Font.BOLD,14));
        rePlay.setBackground(new Color(51,51,51));
        rePlay.setIcon(replayOff);
        rePlay.setBorder(null);



        previous.setFont(new Font("sans",Font.BOLD,14));
        previous.setBackground(new Color(51,51,51));
        previous.setIcon(previousIcon);
        previous.setBorder(null);


        next.setFont(new Font("sans",Font.BOLD,14));
        next.setBackground(new Color(51,51,51));
        next.setIcon(nextIcon);
        next.setBorder(null);


        shuffle.setFont(new Font("sans",Font.BOLD,14));
        shuffle.setBackground(new Color(51,51,51));
        shuffle.setIcon(shuffleoff);

        labelTimeCounter.setFont(new Font("Sans", Font.BOLD, 12));
        labelTimeCounter.setForeground(new Color(234, 251, 255));

        labelDuration.setFont(new Font("Sans", Font.BOLD, 12));
        labelDuration.setForeground(new Color(234, 251, 255));

        this.setBackground(new Color(51,51,51));
        powerVoic.setBackground(new Color(51,51,51));
        musicSlider.setBackground(new Color(51,51,51));

        buttonsPanel.setBackground(new Color(51,51,51));
        currentMusic.setBackground(new Color(51,51,51));
        sliderPanel.setBackground(new Color(51,51,51));
        vasatPanel.setBackground(new Color(51,51,51));

        play.addActionListener(this);
        previous.addActionListener(this);
        rePlay.addActionListener(this);
        next.addActionListener(this);
        shuffle.addActionListener(this);

        this.setVisible(true);
        songDetail=new JPanel();
        songDetail.setLayout(new GridLayout(7,1));
        songDetail.setBackground(new Color (51,51,51));
        musicName=new JLabel("Faint");
        musicImage=new JLabel("Epica");
        musicAlbum=new JLabel("nmidnm");
        musicPublishYear=new JLabel("nmidnm");
        songDetail.add(musicName);
        songDetail.add(musicArtist);
        songDetail.add(musicAlbum);
        songDetail.add(musicPublishYear);
        currentMusic.setLayout(new BorderLayout());
        currentMusic.add(musicImage,BorderLayout.WEST);

        currentMusic.add(songDetail,BorderLayout.EAST);







        musicImage.setIcon(AccountManagement.getActiveAccount().getMusics().get(0).getImage());
        musicName.setText("Faint");
        musicArtist.setText("Epica");
        musicAlbum.setText("nmidnm");
        musicPublishYear.setText("2016");
        musicName.setForeground(new Color(234, 251, 255));
        musicArtist.setForeground(new Color(234, 251, 255));
        musicAlbum.setForeground(new Color(234, 251, 255));
        musicPublishYear.setForeground(new Color(234, 251, 255));




        previous.setIcon(previousIcon);
        vasatPanel.setMinimumSize( new Dimension( 400, 50 ) );
        vasatPanel.setMaximumSize( new Dimension( 400, 50 ) );

        this.setLayout(new BorderLayout());
        this.add(currentMusic,BorderLayout.WEST);
        this.add (vasatPanel,BorderLayout.CENTER);
        this.add(powerVoic,BorderLayout.EAST);





    }


    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        currentMusic = new javax.swing.JPanel();
        musicImage = new javax.swing.JLabel();
        musicArtist = new javax.swing.JLabel();
        musicName = new javax.swing.JLabel();
        vasatPanel = new javax.swing.JPanel();
        sliderPanel = new javax.swing.JPanel();
        labelDuration = new javax.swing.JLabel();
        musicSlider = new javax.swing.JSlider();
        labelTimeCounter = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        shuffle = new javax.swing.JButton();
        previous = new javax.swing.JButton();
        play = new javax.swing.JButton();
        next = new javax.swing.JButton();
        rePlay = new javax.swing.JButton();
        powerVoic = new javax.swing.JPanel();
        powerVoicLable = new javax.swing.JLabel();
        powerVoicSlider = new javax.swing.JSlider();

        setMinimumSize(new java.awt.Dimension(719, 159));
        setPreferredSize(new java.awt.Dimension(800, 159));
        setLayout(new java.awt.GridBagLayout());

        currentMusic.setLayout(new java.awt.GridBagLayout());

        musicImage.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 79;
        gridBagConstraints.ipady = 94;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 3, 0, 0);
        currentMusic.add(musicImage, gridBagConstraints);

        musicArtist.setText("Epica,album");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 10, 0, 0);
        currentMusic.add(musicArtist, gridBagConstraints);

        musicName.setText("Faint");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(46, 10, 0, 0);
        currentMusic.add(musicName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(currentMusic, gridBagConstraints);
//=================
        sliderPanel.setLayout(new BorderLayout());

        labelDuration.setText("00:00");
        labelDuration.setForeground(new Color(234, 251, 255));
        sliderPanel.add(labelDuration,BorderLayout.EAST);
        sliderPanel.add(musicSlider, BorderLayout.CENTER);
        labelTimeCounter.setText("00:00");
        labelTimeCounter.setForeground(new Color(234, 251, 255));
        sliderPanel.add(labelTimeCounter, BorderLayout.WEST);

        buttonsPanel.setMaximumSize(new java.awt.Dimension(395, 69));
        buttonsPanel.setLayout(new java.awt.GridLayout(1, 0));

        shuffle.setToolTipText("Shuffle");
        shuffle.setBorder(null);
        shuffle.setFocusPainted(false);
        buttonsPanel.add(shuffle);

        previous.setToolTipText("previous Music");
        previous.setBorder(null);
        previous.setFocusPainted(false);
        buttonsPanel.add(previous);

        play.setToolTipText("Play / Pause");
        play.setBorder(null);
        play.setFocusPainted(false);
        buttonsPanel.add(play);

        next.setToolTipText("Next Music");
        next.setBorder(null);
        next.setFocusPainted(false);
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });
        buttonsPanel.add(next);

        rePlay.setToolTipText("RePlay");
        rePlay.setBorder(null);
        rePlay.setFocusPainted(false);
        rePlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rePlayActionPerformed(evt);
            }
        });
        buttonsPanel.add(rePlay);

        javax.swing.GroupLayout vasatPanelLayout = new javax.swing.GroupLayout(vasatPanel);
        vasatPanel.setLayout(vasatPanelLayout);
        vasatPanelLayout.setHorizontalGroup(
            vasatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sliderPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        vasatPanelLayout.setVerticalGroup(
            vasatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vasatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(sliderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 7, 0, 0);
        add(vasatPanel, gridBagConstraints);

        powerVoic.setLayout(new BorderLayout());

        powerVoicLable.setText("Power Voice");
        powerVoicLable.setForeground(new Color(171, 117, 188));
        powerVoic.add(powerVoicLable, BorderLayout.NORTH);
        powerVoic.add(powerVoicSlider,BorderLayout.SOUTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 7, 9, 92);
        add(powerVoic, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void rePlayActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }//GEN-LAST:event_rePlayActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }



    public void playSong(Music m){
        playThread.stop();
        play.setIcon(pauseIcon);
        isPlaying=true;
        playThread.start();
        cnt=1;
    }
    private int q ;
    public void playSongs(){
        while(true) {
            int numberOfmusicsArray = musicArray.size();
            if (q > numberOfmusicsArray && isReplay) q = 0;
                else if (q > numberOfmusicsArray)break;
                    else {
                        playSong(musicArray.get(q));
                        q++;
                    }

        }
            //TODO wen song playing finish

    }


    public void setMusiaArray(ArrayList<Music> musiaArray) {
        q=0;
        this.musicArray = musiaArray;
    }

//    Runnable runnableResume=new Runnable() {
//        @Override
//        public void run() {
//            try {
//                //code for resume button
//                fileInputStream=new FileInputStream(myFile);
//                bufferedInputStream=new BufferedInputStream(fileInputStream);
//                player=new Player(bufferedInputStream);
//                fileInputStream.skip(totalLength-pause);
//                player.play();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (JavaLayerException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    };
//



    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if(event.getSource()==play){
            if (!isPlaying) {
                play.setIcon(pauseIcon);
                isPlaying=true;

                if(cnt==0){playThread.start();cnt++;}
                else playThread.resume();

            } else {
                play.setIcon(playIcon);
                isPlaying=false;
//                if (cnt==1)playThread.suspend();
//                else resumeThread.suspend();
                playThread.suspend();


            }
        }

        else  if(event.getSource()==rePlay){
            if (!isReplay) {
                rePlay.setIcon(replayOn);
                isReplay=true;

            } else {
                rePlay.setIcon(replayOff);
                isReplay=false;

            }
        }else if(event.getSource()==next){
            q++;
            playSongs();
        }else if(event.getSource()==previous){
            q-=2;
            playSongs();
        }else if(event.getSource()==shuffle){
            if (!isShuffle) {
                shuffle.setIcon(shuffleOn);
                isShuffle=true;

            } else {
                shuffle.setIcon(shuffleoff);
                isShuffle=false;

            }
        }

    }




    Runnable runnablePlay = new Runnable() {
        @Override
        public void run() {
            try {
                //code for play button
                fileInputStream=new FileInputStream(AccountManagement.getActiveAccount().getMusics().get(0).getDirectory());
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                player=new Player(fileInputStream);
                totalLength=fileInputStream.available();
                player.play();//starting music
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };


}
