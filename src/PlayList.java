import java.util.ArrayList;


public abstract class PlayList {
    protected String name;
    protected String name;
    protected boolean editable;
    protected ArrayList<Music> musics;

    public void addSong(ArrayList<Music> selectedMusics) {
        for (Music eachMusic : selectedMusics) {
            musics.add(eachMusic);
        }
    }

    /**
     * Removing a song from a playlist.
     * Find the song in arraylist of the playlist then remove it.
     * @param music Selected music
     */

    public void removeSong(Music music) {
        musics.remove(music);
    }

    public String getName() {
        return name;
    }


}
