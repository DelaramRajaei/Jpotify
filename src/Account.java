import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.util.ArrayList;

public class Account {
    private String name;
    private int lastActivity;
    private int IP;

    private ArrayList<Music> musics;
    private ArrayList<Album> albums;
    private ArrayList<ClientPlayList> clientPlayLists;
    private FavoriteSongs favoriteSongs;
    private SharedPlayList sharedPlayList;
    private ArrayList<PlayList> playLists;


    public Account(String name) {
        this.name = name;

        favoriteSongs = new FavoriteSongs();
        sharedPlayList = new SharedPlayList();
        musics = new ArrayList<Music>();
        clientPlayLists = new ArrayList<ClientPlayList>();
        albums = new ArrayList<Album>();
        playLists = new ArrayList<PlayList>();

        playLists.add(sharedPlayList);
        playLists.add(favoriteSongs);
        if (clientPlayLists != null) {
            for (ClientPlayList eachOne : clientPlayLists) {
                playLists.add(eachOne);
            }
        }
    }

    /**
     * Add a new song.
     *
     * @param directory Path of the new song.
     */
    public void addMusic(String directory) throws InvalidDataException, IOException, UnsupportedTagException {
        Music music = new Music(directory);
        musics.add(music);
        Core.addSong(music, albums);
    }

    /**
     * Creating a new playlist.
     *
     * @param name Name of the new Playlist.
     */
    public void createPlayList(String name) {
        ClientPlayList newPlaylist = new ClientPlayList(name);
        clientPlayLists.add(newPlaylist);
        playLists.add(newPlaylist);
        Core.createPlaylist(newPlaylist, playLists);
    }

    /**
     * Add a song to a playlist.
     * At first it would find the selected playlist and then call the addSong method.
     *
     * @param selectedMusics Arraylist of musics which was selected.
     * @param playList       The playlist which was selected.
     */
    public void addSongToPlayList(PlayList playList, Music selectedMusics) {
        playList.addSong(selectedMusics);
        selectedMusics.addPlayList(playList);
    }


    public void removeSong(Music music)throws Exception {
        musics.remove(music);
        for (PlayList eachPlaylist : music.getPlaylist()) {
            eachPlaylist.removeSong(music);
        }
        Core.removeSong(music.getPlaylist(), musics, albums);
    }

    /**
     * Removing a song from a selected Playlist
     * First it would find the playlist then call the removeSongFromPlaylist method.
     *
     * @param music    musics that you selected to remove
     * @param playList Selected playlist
     */
    public void removeSongFromPlaylist(PlayList playList, Music music) {
        music.getPlaylist().remove(playList);
        playList.removeSong(music);
    }

    public void removePlaylist(PlayList playList) {
        if (playList.editable) {
            for (Music eachMusic : playList.getMusic())
                eachMusic.getPlaylist().remove(playList);
            playLists.remove(playList);
            clientPlayLists.remove(playList);
            Core.removePlaylist(playList);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public ArrayList<ClientPlayList> getClientPlayLists() {
        return clientPlayLists;
    }

    public SharedPlayList getSharedPlayLists() {
        return sharedPlayList;
    }

    public FavoriteSongs getFavoriteSongs() {
        return favoriteSongs;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
