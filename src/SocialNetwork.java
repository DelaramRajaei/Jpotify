import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocialNetwork {
    private ArrayList<Friend> friendsList;
    ServerSocket serverSocket;
    Socket socket;
    private Account account;

    public SocialNetwork(ArrayList<Friend> friendsList, Account account) {
        this.friendsList = friendsList;
        this.account = account;
    }

    public void startServer() throws Exception {
        serverSocket = new ServerSocket(6666);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        socket = serverSocket.accept();
                        communicate(socket);
                        socket.close();
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }


    private void communicate(Socket socket) throws Exception {
        try {
            String message = "";
            int i;
            InputStream input = socket.getInputStream();
            InputStreamReader sr = new InputStreamReader(input);
            while ((i = sr.read()) != -1) {
                message += (char) i;
            }
            analyzeCommand(message, socket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analyzeCommand(String message, String IP) throws Exception {
        switch (message.split(",")[0].toLowerCase()) {
            case "status":
                updateFriendStatus(IP, message);
                break;
            case "askstatus":
                sendStatus(UserStatus.Online, IP);
                break;
            case "listeninto":
                updateFriendMusic(IP, message);
                break;
            case "getmusic":
                break;
            case "invitation":
                break;
        }
    }

    private void updateFriendMusic(String ip, String message) throws Exception {
        FriendsActivityPanel friendsActivityPanel = new FriendsActivityPanel();
        if (friendsActivityPanel.getFriends().size() != 0)
            friendsActivityPanel.getFriends();
        ArrayList<FriendPanel> friendPanels = new ArrayList<FriendPanel>();
        for (Friend friend : friendsList) {
            if (ip.equals(friend.getIP())) {
                FriendPanel friendPanel = new FriendPanel(account);
                friendPanels.add(friendPanel);
            }
        }

    }

    public void updateFriendStatus(String IP, String message) {
        for (Friend friend : friendsList) {
            if (IP.equals(friend.getIP())) {
                if (message.split(",")[1].equals("Online"))
                    friend.setStatus(UserStatus.Online);
                else if (message.split(",")[1].equals("Offline"))
                    friend.setStatus(UserStatus.Offline);
                System.out.println(friend.getIP() + "->" + message.split(",")[2] + " is " + message.split(",")[1]);
            }
        }
    }

    public enum UserStatus {
        Online,
        Offline,
        Pending
    }

    public void sendStatusToAll(UserStatus us) {
        for (Friend friend : friendsList) {
            sendStatus(us, friend.getIP());
        }
    }

    public void sendStatus(UserStatus us, String IP) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String message = "Status," + us.toString() + "," + account.getName();
            output.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void askStatus(String IP) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String message = "AskStatus";
            output.write(message.getBytes());
        } catch (Exception e) {
            for (Friend friend : friendsList) {
                if (friend.equals(IP))
                    friend.setStatus(UserStatus.Offline);
            }
        }

    }

    public void listenInto(String IP, Music music) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String message = "ListenInto" + music.getName() + "," + music.getAlbum();
            output.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMusic() {

    }

    public void invitation() {

    }
}
