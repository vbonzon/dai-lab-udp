import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;

import static java.nio.charset.StandardCharsets.*;

public class MusicianListener implements Runnable{
    private final static int PORT = 9904;
    private final static String ADDR = "239.255.22.5";
    private LinkedList<Musician> musicians = new LinkedList<>();
    public ConcurrentHashMap<String, Musician> musicians_map = new ConcurrentHashMap<>();
  

    @Override
    public void run() {
        System.out.println("Démarrage MusicianListener");
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            Gson gson = new Gson();
            var group_addr = new InetSocketAddress(ADDR, PORT);
            NetworkInterface netif=NetworkInterface.getByName("eth0");
            socket.joinGroup(group_addr, netif);

            while (true) {
                byte[] buffer = new byte[1024];
                var packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength(), UTF_8);
                System.out.println(msg + "\n");
                UDPPacket p = gson.fromJson(msg, UDPPacket.class);

                if(!musicians_map.containsKey(p.uuid)){
                    musicians_map.put(p.uuid, new Musician(p));
                }
                else{
                    musicians_map.get(p.uuid).update();
                }
                
            }

            
        } catch (Exception e) {
            System.out.println("Problem during multicast socket setup : " + e);
        }
    }

    public Map<String,Musician> getMusicians(){
        return musicians_map;
    }
}
