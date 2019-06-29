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
            String line = "";
            int i;
            InputStream input = socket.getInputStream();
            InputStreamReader sr = new InputStreamReader(input);
//            while ((i = sr.read()) != -1) {
//                message += (char) i;
//            }
            BufferedReader br = new BufferedReader(sr);
            while ((line = br.readLine()) != null) {
                message += line;
            }
            analyzeCommand(message, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analyzeCommand(String message, Socket socket) throws Exception {
        String IP = socket.getInetAddress().getHostAddress();
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
            case "askmusic":
                sendMusic(IP, message.split(",")[1]);
                break;
            case "sendmusic":
                getMusic(message,socket);
                break;
            case "invitation":
                answerInvitation(IP);
                break;
            case "accept":
                addFriend(IP, message);
                break;
        }
    }

    public void addFriend(String IP, String message) throws Exception {
        Friend newFriend = new Friend();
        newFriend.setIP(IP);
        newFriend.setName(message.split(",")[1]);
        friendsList.add(newFriend);
        account.addFriend(newFriend);
    }

    private void getMusic( String message, Socket socket) throws Exception {
        String fileName =  message.split(",")[1];
        File file = new File("Files");
        if (!file.exists()) file.mkdir();//Make a Folder
        File newFile = new File(file, fileName+".mp3");
        FileOutputStream fos = new FileOutputStream(newFile);
        InputStream stream = socket.getInputStream();
        byte[] fileData=new byte[stream.available()];
        stream.read(fileData);
        fos.write(fileData);
        fos.flush();
        fos.close();
    }

    public void sendMusic(String IP, String nameMusic) throws Exception {
        File file;
        Long fileLength;
        byte[] bytes;
        int length;
        Socket client = new Socket(IP, 6666);
        OutputStream outputStream = client.getOutputStream();
        BufferedOutputStream writer = new BufferedOutputStream(outputStream);
        String message = "sendMusic,"+nameMusic;
        writer.write(message.getBytes());
        writer.flush();
        DataInputStream dataInputStream;


        for (Music music : account.getMusics())
            if (music.getName().trim().equals(nameMusic.trim())) {
                file = new File(music.getDirectory());
                fileLength = file.length();
                length = fileLength.intValue();
                dataInputStream = new DataInputStream(new FileInputStream(file));
                bytes = new byte[length];
                dataInputStream.readFully(bytes);
                writer.write(bytes);
                writer.flush();
                writer.close();
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
            BufferedOutputStream writer = new BufferedOutputStream(output);
            String message = "Status," + us.toString() + "," + account.getName();
            writer.write(message.getBytes());
            writer.flush();
            writer.close();
            client.close();
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
            output.flush();
            output.close();
            client.close();
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
            output.flush();
            output.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void askForMusic(String IP, String name) {
        try {
            Socket client = new Socket(IP, 6666);
            client.getRemoteSocketAddress();//TODO check
            OutputStream output = client.getOutputStream();
            String message = "askMusic" + "," + name + "," + client.getRemoteSocketAddress();
            output.write(message.getBytes());
            output.flush();
            output.close();
            client.close();
        } catch (Exception e) {
        }
    }

    public void sendInvitation(String IP) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String message = "invitation" + "," + account.getName();
            output.write(message.getBytes());
            output.flush();
            output.close();
            client.close();
        } catch (Exception e) {
        }
    }

    public void answerInvitation(String IP) {
        try {
            Socket client = new Socket(IP, 6666);
            OutputStream output = client.getOutputStream();
            String answer = null;
            String message;
            message = "accept" + "," + account.getName();
            output.write(message.getBytes());
            output.flush();
            output.close();
            client.close();
        } catch (Exception e) {
        }
    }
}
