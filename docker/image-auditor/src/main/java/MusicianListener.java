import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.LinkedList;
import java.util.List;
import static java.nio.charset.StandardCharsets.*;

public class MusicianListener implements Runnable{
    private final static int PORT = 9904;
    private final static String ADDR = "239.255.22.5";
    private LinkedList<Musician> musicians = new LinkedList<>();

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

                String sound = new String(packet.getData(), 0, packet.getLength(), UTF_8);

                for(Instrument i : Instrument.values()){
                    if(i.doSound() == sound){
                        musicians.add(new Musician(i));
                    }
                }
            }

            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public List<Musician> getMuscians(){
        return musicians;
    }
}
