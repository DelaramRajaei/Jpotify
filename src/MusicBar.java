
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MusicBar extends JPanel implements ActionListener   {

    private AudioPlayer player=new AudioPlayer();
    private Thread playbackThread;
    private PlayingTimer timer;

    private boolean isPlaying = false;
    private boolean isPause = false;

    private String audioFilePath;
    private String lastOpenPath;




    private static final int WIDTH = 750, HEIGHT = 300;

    private JButton pause;
    private JButton play;
    private JButton next;
    private JButton previous;
    private JSlider musicSlider;
    private JButton shuffle;

    Thread t;


    ImageIcon playIcon = new ImageIcon(this.getClass().getResource("images/play2.png"));
    ImageIcon pauseIcon = new ImageIcon(this.getClass().getResource("images/paus.png"));

    public MusicBar(){
        super();
        this.setBackground(Color.WHITE);
        play=new JButton(playIcon);
        pause=new JButton(pauseIcon);
        previous=new JButton("previous");
        next=new JButton("next");
        shuffle=new JButton();
        musicSlider=new JSlider();




                t=new Thread(this::run);

        play.setBounds(200,700,50,50);
        pause.setBounds(250,700,50,50);
        next.setBounds(300,700,50,50);
        previous.setBounds(150,700,50,50);
        musicSlider.setBounds(200,755,200,25);
        previous.setBounds(150,700,50,50);

        play.addActionListener(this);
        previous.addActionListener(this);
        pause.addActionListener(this);
        next.addActionListener(this);
        shuffle.addActionListener(this);

        add(play);
        add(pause);
        add(previous);
        add(next);
        add(shuffle);
        add(musicSlider);



        setVisible(true);
        setLayout(null);
        this.setSize(WIDTH, HEIGHT);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==play){
            t.start();

        }
        else  if(e.getSource()==pause){
            t.suspend();
        }else if(e.getSource()==next){
                t.stop();
        }else if(e.getSource()==previous){
                t.stop();
        }else if(e.getSource()==shuffle){

         }
    }

        public void run(){
        playMusic();

//            FileInputStream fis;
//            try{
//                fis=new FileInputStream("m.mp3");
//                AdvancedPlayer player=new AdvancedPlayer(fis);
//                player.play();
//            }catch (FileNotFoundException e){
//                e.printStackTrace();
//            } catch (JavaLayerException e) {
//                e.printStackTrace();
//            }
        }


        public void playMusic(){
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




}