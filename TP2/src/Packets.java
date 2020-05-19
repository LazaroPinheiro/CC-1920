import java.util.LinkedList;
import java.util.Queue;

public class Packets {
    private Queue<Packet> packets;
    private boolean complete;
    private int sequenceNum;

    public Packets() {
        this.packets = new LinkedList<>();
        this.complete = false;
        this.sequenceNum = 0;
    }

    public void addPacket(Packet packet) {
        if(packet != null && !this.complete) {
            this.packets.add(packet);
            this.sequenceNum++;
        }
    }

    public Packet pollPacket(){
        Packet packet = null;
        if(!this.packets.isEmpty()){
            packet = this.packets.poll();
        }
        return packet;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public void complete() {
        this.complete = true;
    }

    public int getSequenceNum() {
        return this.sequenceNum;
    }
}
