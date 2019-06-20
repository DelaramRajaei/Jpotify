import javax.swing.*;
import java.awt.*;

public class RootPanel extends JFrame {

    private MusicBar musicBar;

    public RootPanel(){
        super();
        musicBar=new MusicBar();
        setLayout(new BorderLayout());
        rootPane.add(musicBar,BorderLayout.SOUTH);

    }

}
