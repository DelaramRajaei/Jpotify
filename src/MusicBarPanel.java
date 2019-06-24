


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class MusicBarPanel extends JPanel {

//TODO debbuging




        private Music playingSong;



        private AudioPlayer player = new AudioPlayer();
        private Thread playbackThread;
        private PlayingTimer timer;

        private boolean isPlaying = false;
        private boolean isReplay = false;
        private boolean isShuffle = false;


        private String audioFilePath;
        private String lastOpenPath;

        private JLabel labelFileName = new JLabel("Playing File:");
        private JLabel labelTimeCounter = new JLabel("00:00:00");
        private JLabel labelDuration = new JLabel("00:00:00");



        private JSlider musicSlider;

        private JPanel buttonsPanel = new JPanel(new GridBagLayout());
        private JPanel currentMusic = new JPanel(new GridBagLayout());
        private JPanel vasatPanel ;
        private JPanel powerVoic ;



        //private JButton pause;
        private JButton play;
        private JButton next;
        private JButton previous;
        private JButton shuffle;
        private JButton buttonOpen;
        private JButton rePlay;


         //private Thread t;


        private ImageIcon playIcon = new ImageIcon(this.getClass().getResource("images/play.png"));
        private ImageIcon pauseIcon = new ImageIcon(this.getClass().getResource("images/paus.png"));
        private ImageIcon nextIcon = new ImageIcon(this.getClass().getResource("images/next.png"));
        private ImageIcon previousIcon = new ImageIcon(this.getClass().getResource("images/previous.png"));
        private ImageIcon shuffleoff = new ImageIcon(this.getClass().getResource("images/shuffleOff.png"));
        private ImageIcon shuffleOn = new ImageIcon(this.getClass().getResource("images/shuffleOn.png"));
        private ImageIcon replayOn = new ImageIcon(this.getClass().getResource("images/replayOn.png"));
        private ImageIcon replayOff = new ImageIcon(this.getClass().getResource("images/replayOff.png"));



        public MusicBarPanel() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.anchor = GridBagConstraints.WEST;




            buttonsPanel.setBackground(new Color(51, 51, 51));
            currentMusic.setBackground(new Color(132, 255, 248));
            this.setBackground(Color.gray);


            JButton temp=new JButton("temp");
            temp.setBackground(new Color(216, 222, 174));
            currentMusic.add(temp,constraints);

            musicSlider=new JSlider();
            musicSlider.setPreferredSize(new Dimension(400, 20));
            musicSlider.setEnabled(false);
            musicSlider.setValue(0);

            play=new JButton(playIcon);
            play.setFont(new Font("Sans", Font.BOLD, 14));
            play.setBackground(new Color(51,51,51));
            play.setIcon(playIcon);
            play.setBorder(null);


            rePlay=new JButton();
            rePlay.setFont(new Font("sans",Font.BOLD,14));
            rePlay.setBackground(new Color(51,51,51));
            rePlay.setIcon(replayOff);
            rePlay.setBorder(null);


            previous=new JButton();
            previous.setFont(new Font("sans",Font.BOLD,14));
            previous.setBackground(new Color(51,51,51));
            previous.setIcon(previousIcon);
            previous.setBorder(null);

            next=new JButton();
            next.setFont(new Font("sans",Font.BOLD,14));
            next.setBackground(new Color(51,51,51));
            next.setIcon(nextIcon);
            next.setBorder(null);

            shuffle=new JButton();
            shuffle.setFont(new Font("sans",Font.BOLD,14));
            shuffle.setBackground(new Color(51,51,51));
            shuffle.setIcon(shuffleoff);

            buttonOpen = new JButton("Open");
            buttonOpen.setFont(new Font("sans",Font.BOLD,14));



            labelTimeCounter.setFont(new Font("Sans", Font.BOLD, 12));

            labelDuration.setFont(new Font("Sans", Font.BOLD, 12));


            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(0, 0, 0, 10);

            currentMusic.setVisible(true);
            constraints.gridx=0;
            constraints.gridy=0;
            this.add(currentMusic,constraints);


            constraints.insets = new Insets(5, 5, 5, 5);


            constraints.gridx=0;
            buttonsPanel.add(shuffle,constraints);
            shuffle.setFocusPainted(false);
            shuffle.setBorder(null);

            constraints.gridx=1;
            buttonsPanel.add(previous,constraints);
            //previous.setFocusPainted(false);
            previous.setBorder(null);

            constraints.gridx=2;
            buttonsPanel.add(play,constraints);

            constraints.gridx=3;
            buttonsPanel.add(next,constraints);
            constraints.gridx=4;
            buttonsPanel.add(rePlay,constraints);

            constraints.anchor = GridBagConstraints.CENTER;
            constraints.gridx = 1;
            constraints.gridy = 0;
            add(buttonsPanel);

            labelFileName.setForeground(new Color(234, 251, 255));
            constraints.gridx = 4;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            add(labelFileName, constraints);


            constraints.anchor = GridBagConstraints.CENTER;
            constraints.gridx=3;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            add(labelTimeCounter, constraints);
            labelTimeCounter.setForeground(new Color(234, 251, 255));

            constraints.gridx = 1;
            constraints.gridy = 1;
            add(musicSlider, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            add(labelDuration, constraints);
            labelDuration.setForeground(new Color(234, 251, 255));


            this.setBackground(new Color(51,51,51));
            musicSlider.setBackground(new Color(51,51,51));

            //*
            //**action listeners:
//            play.addActionListener(this);
//            previous.addActionListener(this);
//            rePlay.addActionListener(this);
//            next.addActionListener(this);
//            shuffle.addActionListener(this);
//            buttonOpen.addActionListener(this);

            this.setVisible(true);

           // t=new Thread(this::run);



//            play.setSize(50,50);
//            Image img = playIcon.getImage() ;
//            Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
//            playIcon = new ImageIcon( newimg );


        }

//
//        @Override
//        public void actionPerformed(ActionEvent event) {
//            Object source = event.getSource();
//
//            if(event.getSource()==play){
//                if (!isPlaying) {
//                   play.setIcon(pauseIcon);
//                    isPlaying=true;
//                } else {
//                    play.setIcon(playIcon);
//                    isPlaying=false;
//                }
//            }
//
//            else  if(event.getSource()==rePlay){
//                if (!isReplay) {
//                    rePlay.setIcon(replayOn);
//                    isReplay=true;
//
//                } else {
//                    rePlay.setIcon(replayOff);
//                    isReplay=false;
//
//                }
//            }else if(event.getSource()==next){
//                //t.stop();
//            }else if(event.getSource()==previous){
//                //t.stop();
//            }else if(event.getSource()==shuffle){
//                if (!isShuffle) {
//                    shuffle.setIcon(shuffleOn);
//                    isShuffle=true;
//
//                } else {
//                    shuffle.setIcon(shuffleoff);
//                    isShuffle=false;
//
//                }
//
//            }
//        }


        //handling click on open file:






        private void playBack() {
            timer = new PlayingTimer(labelTimeCounter, musicSlider);
            timer.start();
            isPlaying = true;
            playbackThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        //play.setText("Stop");
                         play.setIcon(pauseIcon);
                        // play.setEnabled(true);

                        // pause.setText("Pause");
                        // pause.setEnabled(true);

                        player.load(audioFilePath);
                        timer.setAudioClip(player.getAudioClip());
                        labelFileName.setText("Playing File: " + audioFilePath);
                        musicSlider.setMaximum((int) player.getClipSecondLength());

                        labelDuration.setText(player.getClipLengthString());
                        player.play();

                        resetControls();

                    } catch (UnsupportedAudioFileException ex) {
                       //  JOptionPane.showMessageDialog(SwingAudioPlayer.this,
                       //          "The audio format is unsupported!", "Error", JOptionPane.ERROR_MESSAGE);
                        resetControls();
                        ex.printStackTrace();
                    } catch (LineUnavailableException ex) {
                       //   JOptionPane.showMessageDialog(SwingAudioPlayer.this,
                       //           "Could not play the audio file because line is unavailable!", "Error", JOptionPane.ERROR_MESSAGE);
                        resetControls();
                        ex.printStackTrace();
                    } catch (IOException ex) {
                      //    JOptionPane.showMessageDialog(SwingAudioPlayer.this,
                      //            "I/O error while playing the audio file!", "Error", JOptionPane.ERROR_MESSAGE);
                        resetControls();
                        ex.printStackTrace();
                    }

                }
            });

            playbackThread.start();
        }







//        public void run() {
//            playMusic();
//
//            FileInputStream fis;
//            try{
//                fis=new FileInputStream(audioFilePath);
//                AdvancedPlayer player=new AdvancedPlayer(fis);
//                player.play();
//            }catch (FileNotFoundException e){
//                e.printStackTrace();
//            } catch (JavaLayerException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//
//        public void playMusic() {
//            System.out.println("music playing");
//            try {
//                FileInputStream fileInputStream = new FileInputStream("G:\\uni\\project\\New folder\\Jpotify\\src\\m.mp3");
//                Player player = new Player(fileInputStream);
//                System.out.println("Song is playing...");
//                player.play();
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (JavaLayerException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        private void stopPlaying() {
            isReplay = false;
            // pause.setText("Pause");
            //pause.setEnabled(false);
            timer.reset();
            timer.interrupt();
            player.stop();
            playbackThread.interrupt();
        }

        private void pausePlaying() {
            // pause.setText("Resume");
            isReplay = true;
            player.pause();
            timer.pauseTimer();
            playbackThread.interrupt();
        }

        private void resumePlaying() {
            // pause.setText("Pause");
            isReplay = false;
            player.resume();
            timer.resumeTimer();
            playbackThread.interrupt();
        }

        private void resetControls() {
            timer.reset();
            timer.interrupt();

            //play.setText("Play");
            // play.setIcon(playIcon);

            // pause.setEnabled(false);

            isPlaying = false;
        }



}
