import java.util.ArrayList;

public class Music {

    private String directory;
    private String MusicName;
    private String artistName;
    private String AlbumName;
    private int holetime;
    private int lastPlayingTime;



    public Music(String d) {




    }

    public int getHoletime() {
        return holetime;
    }
    public void setHoletime(int t){
        holetime=t;
    }
    public void setDirectory(String directory){this.directory=directory;}
    public String getDirectory(){return directory;}
}
