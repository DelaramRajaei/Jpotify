import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class RootPanel extends JFrame {

    private MusicBar musicBar;
    private static final int WIDTH = 500, HEIGHT = 500;

    public RootPanel(){
        super();
        musicBar=new MusicBar();
        setLayout(new BorderLayout());
        rootPane.add(musicBar,BorderLayout.EAST);
        this.setContentPane(musicBar);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
    }



}
