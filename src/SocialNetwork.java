import java.net.ServerSocket;
import java.net.Socket;

public class SocialNetwork {

    ServerSocket serverSocket;
    Socket socket;

    public SocialNetwork()throws Exception{
       serverSocket =new ServerSocket(6666);

    }
}
