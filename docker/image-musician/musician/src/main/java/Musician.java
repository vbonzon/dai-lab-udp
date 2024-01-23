import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import static java.nio.charset.StandardCharsets.*;

public class Musician{
    static final int PORT = 9904;
    static final String multicastAddress = "239.255.22.5";
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Instrument argument is missing !");
            return;
        }
        Instrument instrument = readArg(args[0]);
        
        startSending(instrument);
               

    }

    private static Instrument readArg(String arg){
        switch (arg) {
            case "piano":   return Instrument.piano;
            case "flute":   return Instrument.flute;
            case "trumpet": return Instrument.trumpet;
            case "violin":  return Instrument.violin;
            case "drum":    return Instrument.drum;
            default:        return null;
        }
    }

    private static void startSending(Instrument i){

        try(DatagramSocket socket= new DatagramSocket()) {
            
            String message = i.doSound();
            byte[] payload = message.getBytes(UTF_8);


            var dest_address = new InetSocketAddress(multicastAddress, PORT);
            var packet = new DatagramPacket(payload,payload.length,dest_address);

            long lastSec = 0;
            while(true){
                long sec = System.currentTimeMillis() / 1000;
                if (sec != lastSec) {
                    socket.send(packet);
                    System.out.println(i.doSound());
                    lastSec = sec;
                }
            }

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}