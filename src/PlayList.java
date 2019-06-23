import java.util.ArrayList;


public abstract class PlayList {
    protected String name;
    protected boolean editable;
    protected ArrayList<Music> musics;

    public void addSong(Music selectedMusic) {
        musics.add(selectedMusic);
    }

    /**
     * Removing a song from a playlist.
     * Find the song in arraylist of the playlist then remove it.
     *
     * @param music Selected music
     */

    public void removeSong(Music music) {
        musics.remove(music);
    }

    public String getName() {

        return name;
    }

}
