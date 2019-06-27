import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.LocalDate;

public class Friend {
    private String name;
    private String IP;
    private String music;
    private String artist;
    private String album;
    private SocialNetwork.UserStatus status;
    private LocalDate lastCommunicationDate;


    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SocialNetwork.UserStatus getStatus() {
        return status;
    }

    public void setStatus(SocialNetwork.UserStatus status) {
        this.status = status;
    }

    public LocalDate getLastCommunicationDate() {
        return lastCommunicationDate;
    }

    public void setLastCommunicationDate(LocalDate lastCommunicationDate) {
        this.lastCommunicationDate = lastCommunicationDate;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    //TODO write
}
