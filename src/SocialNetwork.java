import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocialNetwork {
    private ArrayList<Friend> friendsList;
    private ArrayList<Music> newMusics;
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
            case "askMusic":
                sendMusic(IP);
                break;
            case "sendMusic":
                getMusic(message);
                break;
            case "invitation":
                answerInvitation(IP);
                break;
            case "accept":
                addFriend(IP, message);
                break;
        }
    }

    private void addFriend(String IP, String message) throws Exception {
        Friend newFriend = new Friend();
        newFriend.setIP(IP);
        newFriend.setName(message.split(",")[1]);
        friendsList.add(newFriend);
        account.addFriend(newFriend);
    }

    private void getMusic(String message) throws Exception {
        newMusics = new ArrayList<Music>();
        byte[] newMusic = message.getBytes();
        File outputFile = File.createTempFile("file", "mp3");
        outputFile.deleteOnExit();
        FileOutputStream fileoutputstream = new FileOutputStream(outputFile);
        fileoutputstream.write(newMusic);
        Music music = new Music(outputFile.getAbsoluteFile().toString());
        newMusics.add(music);
        fileoutputstream.close();
    }

    public ArrayList<Music> getNewMusics() {
        return newMusics;
    }

    private void sendMusic(String IP) throws Exception {
        String sendMessage = "sendMusic";
        Socket client = new Socket(IP, 6666);
        OutputStream output = client.getOutputStream();
        output.write(sendMessage.getBytes());
        for (Music music : account.getSharedPlayLists().getMusic()) {
            File songFile = null;
            FileInputStream input = null;
            int count;

            songFile = new File(music.getDirectory());
            input = new FileInputStream(songFile);
            byte[] buffer = new byte[2048];
            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
            output.write(-1);
            input.close();
            input.close();
            output.write(sendMessage.getBytes());
            output.flush();
        }
    }

    private void updateFriendMusic(String ip, String message) throws Exception {
        for (Friend friend : friendsList) {
            if (friend.getIP().equals(ip)) {
                friend.setMusic(message.split(",")[1]);
                friend.setArtist(message.split(",")[2]);
                friend.setAlbum(message.split(",")[3]);
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
                if (friend.getIP().equals(IP))
                    friend.setStatus(UserStatus.Offline);
            }
        }

    }

    public void listenInto(String IP, Music music) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String message = "ListenInto" + music.getName() + "," + music.getArtist() + "," + music.getAlbum();
            output.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askForMusic(String IP, String name) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String message = "askMusic";
            output.write(message.getBytes());
        } catch (Exception e) {
        }
    }

    public void sendInvitation(String IP) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String message = "invitation" + "," + account.getName();
            output.write(message.getBytes());
        } catch (Exception e) {
        }
    }

    public void answerInvitation(String IP) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String answer = null;
            String message;
            //TODO get answer
            if (answer.equals("accept")) {
                message = "accept" + "," + account.getName();
            } else message = "reject";
            output.write(message.getBytes());
        } catch (Exception e) {
        }
    }
}
