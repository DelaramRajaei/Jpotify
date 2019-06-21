import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            ArrayList<Music> musics = new ArrayList<Music>();
            Core.initialLoad(musics);
            RootPanel rootPanel = new RootPanel();
            Music m = new Music("E:\\Codes\\Java\\Project\\Jpotify\\src\\m.mp3");
        } catch(Exception e){
        }
    }
}
