import java.util.ArrayList;

public class Account {
    private String name;
    private int lastActivity;
    private int IP;

    private ArrayList<Music> musics;
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

        playLists.add(sharedPlayList);
        playLists.add(favoriteSongs);
        if (clientPlayLists != null) {
            for (ClientPlayList eachOne : clientPlayLists) {
                playLists.add(eachOne);
            }
        }
    }

    public void addMusic(String directory) {
        Core.addSong(directory, musics);
    }

    public void addPlayList() {
        Core.createPlaylist(playLists);
    }

    public void addSongToPlayList(ArrayList<Music> selectedMusics, String name) {
        boolean flag = false;
        PlayList selectedList = null;
        if (sharedPlayList.getName().equals(name))
            selectedList = sharedPlayList;
        else if (favoriteSongs.getName().equals(name)) {
            selectedList = favoriteSongs;
        } else {
            for (ClientPlayList eachPlayList : clientPlayLists) {
                while (!flag) {
                    if (eachPlayList.getName().equals(name)) {
                        selectedList = eachPlayList;
                        flag = true;
                    }
                }
            }
        }
        selectedList.addSong(selectedMusics);
    }

    /**
     * Removing a song from a selected Playlist
     * First it would find the playlist then call the removeSong method.
     * @param music music that you selected to remove
     * @param name name of the playlist
     */

    public void removeSong(Music music,String name){
        for (PlayList eachPlayList:playLists) {
            if (eachPlayList.getName().equals(name)){
                eachPlayList.removeSong(eachPlayList.music,music.getName());
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }
}
