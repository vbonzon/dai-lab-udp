import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MusicianListener implements Runnable{
    private final static int PORT = 9904;
    private final static String ADDR = "239.255.22.5";

    @Override
    public void run() {
        System.out.println("Démarrage MusicianListener");
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            var group_addr = new InetSocketAddress(ADDR, PORT);
            NetworkInterface netif=NetworkInterface.getByName("eth0");
            socket.joinGroup(group_addr, netif);
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    
        
    }
}
