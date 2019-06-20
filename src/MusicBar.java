import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MusicBar extends JPanel implements ActionListener  {
    private JButton pause;
    private JButton play;
    private JButton next;
    private JButton previous;
    private JSlider musicSlider;
    private JButton shuffle;
    Thread t;

    public MusicBar(){
        play=new JButton("play");
        pause=new JButton("pause");
        previous=new JButton("previous");
        next=new JButton("next");
        shuffle=new JButton();
        musicSlider=new JSlider();
        t=new Thread();

        play.setBounds(200,25,50,25);
        pause.setBounds(250,25,50,25);
        next.setBounds(300,25,50,25);
        previous.setBounds(150,25,50,25);
        musicSlider.setBounds(200,55,150,25);
        previous.setBounds(150,25,50,25);

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
        setSize(500,70);


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

        /*public void run(){
            FileInputStream fis;
            try{
                fis=new FileInputStream("03. Smooth Criminal");
                AdvancedPlayer player=new AdvancedPlayer(fis);
                this.player();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }

        }*/


}