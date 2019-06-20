import javax.swing.*;
import java.awt.*;

public class RootPanel extends JFrame {
    private MusicBar musicBar;
    public RootPanel(){
        super();
        musicBar=new MusicBar();
        rootPane.add(musicBar.setLayout(BorderLayout.SOUTH));



    }

}
