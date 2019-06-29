import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public abstract class PlayList {
    protected String name;
    private JMenuItem jMenuItem;
    protected boolean editable;
    protected ArrayList<Music> musics;
    protected String fileName;
    public PlayList(){

        musics=new ArrayList<Music>();
    }

    public void addSong(Music selectedMusic) {
        musics.add(selectedMusic);
         Core.addSongToPlayList(this,selectedMusic);
    }

    /**
     * Removing a song from a playlist.
     * Find the song in arraylist of the playlist then remove it.
     *
     * @param selectedMusic Selected music
     */

    public void removeSong(Music selectedMusic) {
        musics.remove(selectedMusic);
        Core.removeSongFromPlaylist(this);
    }

    public String getName() {

        return name;
    }
    public String getFileName() {

        return fileName;
    }
    public ArrayList<Music> getMusic(){
        return musics;
    }

    @Override
    public String toString() {
        return name;
    }

    public JMenuItem getjMenuItem() {
        return jMenuItem;
    }

    public void setjMenuItem(JMenuItem jMenuItem) {
        this.jMenuItem = jMenuItem;
    }
}
