import java.util.ArrayList;

public class Album {
    private String albumName;
    private String artistName;
    private ArrayList<Music> musics;

    public Album(Music music) {
        musics = new ArrayList<Music>();
        albumName = music.getAlbum();
        artistName = music.getArtist();
    }

    /**
     * This method will add the song to an album.
     *
     * @param music Music of the album.
     */

    public void addSong(Music music) {
        musics.add(music);
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }
}
