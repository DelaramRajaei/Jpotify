
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.TreeMap;

public class RootPanel extends JFrame {

    private MusicBar musicBar;
    private LeftPanel leftPanel;
    private static final int WIDTH = 1000, HEIGHT = 1050;

    public RootPanel(){
        super();

        musicBar=new MusicBar();
        leftPanel=new LeftPanel();
        setLayout(new BorderLayout());


        this.add(musicBar,BorderLayout.SOUTH);
        this.add(leftPanel,BorderLayout.EAST);

        this.setContentPane(musicBar);
        //this.setContentPane(leftPanel);  <<<<====>>>>>     // age ino az commenti dar biary hichi neshon nmide :(
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
    }



}
