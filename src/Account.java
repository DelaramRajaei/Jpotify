import java.util.ArrayList;

public class Account {
    private String name;
    private int lastActivity;
    private int IP;

    private ArrayList<Music> musics;
    private ArrayList<PlayList> playLists;
    private PlayList favoritePlayList;
    private PlayList sharedPlayList;


    public Account(String name) {
        this.name = name;


        favoritePlayList = new PlayList();
        sharedPlayList = new PlayList();
        musics = new ArrayList<>();
        playLists = new ArrayList<PlayList>();


    }

    public Void addMusic() {
        Core.addSong();
    }

    public Void addPlayList(PlayList p) {
        Core.createPlaylist();
    }

    public String getName() {
        return name;
    }

    public PlayList getSharedPlayList() {
        return sharedPlayList;
    }

    public int getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(int lastActivity) {
        this.lastActivity = lastActivity;
    }

    public void setSharedPlayList(PlayList sharedPlayList) {
        this.sharedPlayList = sharedPlayList;
    }


    public PlayList getFavoritePlayList() {
        return favoritePlayList;
    }

    public void setFavoritePlayList(PlayList favoritePlayList) {
        this.favoritePlayList = favoritePlayList;
    }

    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(ArrayList<PlayList> playLists) {
        this.playLists = playLists;
    }
}
