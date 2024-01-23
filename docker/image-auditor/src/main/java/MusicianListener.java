import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.LinkedList;
import java.util.List;

public class MusicianListener implements Runnable{
    private final static int PORT = 9904;
    private final static String ADDR = "239.255.22.5";
    private LinkedList<Instrument> musicians = new LinkedList<>();

    @Override
    public void run() {
        System.out.println("Démarrage MusicianListener");
        try (MulticastSocket socket = new MulticastSocket(PORT)) {

            var group_addr = new InetSocketAddress(ADDR, PORT);
            NetworkInterface netif=NetworkInterface.getByName("eth0");
            socket.joinGroup(group_addr, netif);

            while (true) {
                byte[] buffer = new byte[1024];
                var packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

            }

            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public List<Instrument> getMuscians(){
        return musicians;
    }
}
