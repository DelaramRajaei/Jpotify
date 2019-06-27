import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.LocalDate;

public class Friend {
    private String name;
    private String IP;
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

    //TODO write
}
