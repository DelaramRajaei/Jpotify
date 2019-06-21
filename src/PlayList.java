import java.util.ArrayList;

public abstract class PlayList {
    protected String name;
    protected boolean editable;
    protected ArrayList<Music> music;

    public void addSong(ArrayList<Music> selectedMusics) {
        for (Music eachMusic : selectedMusics) {
            music.add(eachMusic);
        }
    }
    public String getName(){
        return name;
    }

}
