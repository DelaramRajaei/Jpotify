


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MusicBarPanel extends JPanel implements ActionListener {






        private Music playingSong;



        private AudioPlayer player = new AudioPlayer();
        private Thread playbackThread;
        private PlayingTimer timer;

        private boolean isPlaying = false;
        private boolean isPause = false;

        private String audioFilePath;
        private String lastOpenPath;

        private JLabel labelFileName = new JLabel("Playing File:");
        private JLabel labelTimeCounter = new JLabel("00:00:00");
        private JLabel labelDuration = new JLabel("00:00:00");



        private JSlider musicSlider;

        private JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        private JButton pause;
        private JButton play;
        private JButton next;
        private JButton previous;
        private JButton shuffle;
        private JButton buttonOpen;


         private Thread t;


        private ImageIcon playIcon = new ImageIcon(this.getClass().getResource("images/play2.png"));
        private ImageIcon pauseIcon = new ImageIcon(this.getClass().getResource("images/paus.png"));

        public MusicBarPanel() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 5, 5, 5);
            constraints.anchor = GridBagConstraints.WEST;

            //play=new JButton("temp");

            this.setBackground(Color.gray);

            musicSlider=new JSlider();
            musicSlider.setPreferredSize(new Dimension(400, 20));
            musicSlider.setEnabled(false);
            musicSlider.setValue(0);

            play=new JButton(playIcon);
            play.setFont(new Font("Sans", Font.BOLD, 14));
            //play.setIcon(playIcon);
            //play.setEnabled(false);

            pause=new JButton(pauseIcon);
            pause.setFont(new Font("sans",Font.BOLD,14));
            //pause.setIcon(pauseIcon);
            //pause.setEnabled(false);

            previous=new JButton("previous");
            previous.setFont(new Font("sans",Font.BOLD,14));

            next=new JButton("next");
            next.setFont(new Font("sans",Font.BOLD,14));

            shuffle=new JButton("shuffle");
            shuffle.setFont(new Font("sans",Font.BOLD,14));

            buttonOpen = new JButton("Open");
            buttonOpen.setFont(new Font("sans",Font.BOLD,14));


            labelTimeCounter.setFont(new Font("Sans", Font.BOLD, 12));

            labelDuration.setFont(new Font("Sans", Font.BOLD, 12));


//            play = new JButton(playIcon);
//            pause = new JButton(pauseIcon);
//            previous = new JButton("previous");
//            next = new JButton("next");
//            shuffle = new JButton();
//            musicSlider = new JSlider();

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 3;
            add(labelFileName, constraints);


            constraints.anchor = GridBagConstraints.CENTER;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            add(labelTimeCounter, constraints);

            constraints.gridx = 1;
            add(musicSlider, constraints);

            constraints.gridx = 2;
            add(labelDuration, constraints);


            this.add(buttonOpen);
            buttonsPanel.add(previous);
            buttonsPanel.add(pause);
            buttonsPanel.add(play);
            buttonsPanel.add(next);
            this.add(buttonsPanel);
            this.add(shuffle);
            this.add(musicSlider);


            constraints.gridwidth = 3;
            constraints.gridx = 0;
            constraints.gridy = 2;
            add(buttonsPanel, constraints);

            play.addActionListener(this);
            previous.addActionListener(this);
            pause.addActionListener(this);
            next.addActionListener(this);
            shuffle.addActionListener(this);
            buttonOpen.addActionListener(this);

            this.setVisible(true);

            t=new Thread(this::run);

//            play.setBounds(200,700,50,50);
//            pause.setBounds(250,700,50,50);
//            next.setBounds(300,700,50,50);
//            previous.setBounds(150,700,50,50);
//            musicSlider.setBounds(200,755,200,25);
//            previous.setBounds(150,700,50,50);
//            //buttonsPanel.setBounds(200,200,50,50);
//            buttonOpen.setBounds(100,100,100,100);
//            play.setBounds(200, 700, 50, 50);
//            pause.setBounds(250, 700, 50, 50);
//            next.setBounds(300, 700, 50, 50);
//            previous.setBounds(150, 700, 50, 50);
//            musicSlider.setBounds(200, 755, 200, 25);
//            previous.setBounds(150, 700, 50, 50);

        }


        @Override
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();

            if(event.getSource()==play){
                if (!isPlaying) {
                    t.start();
                } else {
                    t.suspend();
                }
            }

            else  if(event.getSource()==pause){
                if (!isPause) {
                    pausePlaying();
                } else {
                    resumePlaying();
                }
            }else if(event.getSource()==next){
                //t.stop();
            }else if(event.getSource()==previous){
                //t.stop();
            }else if(event.getSource()==shuffle){

            }else if(event.getSource()==buttonOpen){
                openFile();

            }
        }


        //handling click on open file:

        public void openFile(){
            System.out.println("open pressed");
            JFileChooser fileChooser = null;

            if (lastOpenPath != null && !lastOpenPath.equals("")) {
                fileChooser = new JFileChooser(lastOpenPath);
            } else {
                fileChooser = new JFileChooser();
            }

//            FileFilter wavFilter = new FileFilter() {
//                @Override
//                public String getDescription() {
//                    return "Sound file (*.WAV)";
//                }
//
//                @Override
//                public boolean accept(File file) {
//                    if (file.isDirectory()) {
//                        return true;
//                    } else {
//                        return file.getName().toLowerCase().endsWith(".AWV");
//                    }
//                }
//            };
            FileFilter filter = new FileNameExtensionFilter("MP3 File","mp3");
            fileChooser.setFileFilter(filter);
            //fileChooser.showOpenDialog(frame);
            File file = fileChooser.getSelectedFile();

            //fileChooser.setFileFilter(wavFilter);
            //fileChooser.setFileFilter(mp3Filter);
            fileChooser.setDialogTitle("Open Audio File");
            fileChooser.setAcceptAllFileFilterUsed(false);

            int userChoice = fileChooser.showOpenDialog(this);
            if (userChoice == JFileChooser.APPROVE_OPTION) {
                audioFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                lastOpenPath = fileChooser.getSelectedFile().getParent();
                if (isPlaying || isPause) {
                    stopPlaying();
                    while (player.getAudioClip().isRunning()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                //playBack();
            }

            System.out.println(audioFilePath);
            AccountManagement.getActiveAccount().addMusic(audioFilePath);

        }




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







        public void run() {
            playMusic();

            FileInputStream fis;
            try{
                fis=new FileInputStream(audioFilePath);
                AdvancedPlayer player=new AdvancedPlayer(fis);
                player.play();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }



        public void playMusic() {
            System.out.println("music playing");
            try {
                FileInputStream fileInputStream = new FileInputStream("G:\\uni\\project\\New folder\\Jpotify\\src\\m.mp3");
                Player player = new Player(fileInputStream);
                System.out.println("Song is playing...");
                player.play();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JavaLayerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        private void stopPlaying() {
            isPause = false;
            // pause.setText("Pause");
            //pause.setEnabled(false);
            timer.reset();
            timer.interrupt();
            player.stop();
            playbackThread.interrupt();
        }

        private void pausePlaying() {
            // pause.setText("Resume");
            isPause = true;
            player.pause();
            timer.pauseTimer();
            playbackThread.interrupt();
        }

        private void resumePlaying() {
            // pause.setText("Pause");
            isPause = false;
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
