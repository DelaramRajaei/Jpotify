import java.util.ArrayList;

public class Account {
    private String name;
    private int lastActivity;

    private ArrayList<Music>musics;
    private PlayList favoritePlayList;
    private PlayList sharedPlayList;
    private ArrayList<PlayList> playLists;


    public Account(String name){
        this.name=name;


        favoritePlayList=new PlayList();
        sharedPlayList=new PlayList();
        musics=new ArrayList<>();
        playLists=new ArrayList<PlayList>();


    }
    public Void addMusic(Music m){
        musics.add(m);
    }
    public Void addPlayList(PlayList p){
        playLists.add(p);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Music> getSharedPlayList() {
        return sharedPlayList;
    }

    public int getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(int lastActivity) {
        this.lastActivity = lastActivity;
    }

    public void setSharedPlayList(ArrayList<Music> sharedPlayList) {
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
