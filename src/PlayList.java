import java.util.ArrayList;
import java.util.Iterator;


public abstract class PlayList {
    protected String name;
    protected boolean editable;
    protected ArrayList<Music> music;

    public void addSong(ArrayList<Music> selectedMusics) {
        for (Music eachMusic : selectedMusics) {
            music.add(eachMusic);
        }
    }

    /**
     * Removing a song from a playlist.
     * Find the song in arraylist of the playlist then remove it.
     * @param selectedMusics
     * @param name
     */

    public void removeSong(ArrayList<Music> selectedMusics, String name) {
        boolean flag = false;
        while (!flag) {
            Iterator<Music> eachMusic = selectedMusics.iterator();
            while (eachMusic.hasNext()) {
                if (eachMusic.next().getName().equals(name)) {
                    eachMusic.remove();
                }
            }

        }
    }

    public String getName() {
        return name;
    }


}
