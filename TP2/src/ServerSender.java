import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class ServerSender implements Runnable{
    private AnonGWServerCloud cloud;
    private UDPConnection connection;
    private int sessionId;
    private InetAddress overlayPeer;

    public ServerSender(AnonGWServerCloud cloud, UDPConnection connection, int sessionId, InetAddress overlayPeer) {
        this.cloud = cloud;
        this.connection = connection;
        this.sessionId = sessionId;
        this.overlayPeer = overlayPeer;
    }

    @Override
    public void run() {
        Packet packet;
        while (true){
            packet = this.cloud.getReplyPacket(this.sessionId);
            if(packet != null) {
                try {
                    DatagramPacket datagramPacket = new DatagramPacket(packet.toBytes(), packet.toBytes().length, overlayPeer, Constants.UDPPort);
                    this.connection.getDatagramSocket().send(datagramPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(packet.isLast()){
                    break;
                }
            }
        }
        try{
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}