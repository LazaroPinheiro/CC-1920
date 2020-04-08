import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AnonGwMain {
    public static void main(String[] args) throws IOException {
        if (args.length < 6){
            throw new IllegalArgumentException("insuficient arguments");
        }

        String serverIP = args[1];
        int serverPort = Integer.parseInt(args[3]);

        /*List<String> peers = new ArrayList<>();
        for(int i = 6 ; i < args.length ; i++){
            peers.add(args[i]);
        }*/

        AnonGwCloud cloud = new AnonGwCloud();

        InetAddress localAddress = InetAddress.getByName(serverIP);
        ServerSocket s = new ServerSocket(serverPort);

        Socket socketServer = new Socket(localAddress, serverPort);
        Thread readerServer =  new Thread(new AnonGwReader(cloud,socketServer));
        Thread writerServer = new Thread(new AnonGwWriter(cloud, socketServer));
        readerServer.start();
        writerServer.start();

        while(true){
            Socket socketClient = s.accept();
            Thread readerClient = new Thread(new AnonGwReader(cloud, socketClient));
            Thread writerClient = new Thread(new AnonGwWriter(cloud, socketClient));
            readerClient.start();
            writerClient.start();
        }
    }

}