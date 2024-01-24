import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import com.google.gson.Gson;


import static java.nio.charset.StandardCharsets.*;

public class Musician{
    static final int PORT = 9904;
    static final String multicastAddress = "239.255.22.5";
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Instrument argument is missing !");
            return;
        }
        
        startSending(readArg(args[0]));

    }

    private static Instrument.Sound readArg(String arg){
        switch (arg) {
            case "piano":   return Instrument.Sound.piano;
            case "flute":   return Instrument.Sound.flute;
            case "trumpet": return Instrument.Sound.trumpet;
            case "violin":  return Instrument.Sound.violin;
            case "drum":    return Instrument.Sound.drum;
            default:        return null;
        }
    }

    private static void startSending(Instrument.Sound s){

        try(DatagramSocket socket= new DatagramSocket()) {
            
            //Récupérer le message selon l'instrument
            Instrument instr = new Instrument(s);
            Gson gson = new Gson();
            String message = gson.toJson(instr);
            byte[] payload = message.getBytes(UTF_8);

            //Créer l'adresse de destination
            var dest_address = new InetSocketAddress(multicastAddress, PORT);
            //Créer le packet
            var packet = new DatagramPacket(payload,payload.length,dest_address);

            long lastSec = 0;
            while(true){
                long sec = System.currentTimeMillis() / 1000;
                //Toutes les secondes envoyer le même packet
                if (sec != lastSec) {
                    socket.send(packet);
                    
                    System.out.println(new String(packet.getData(), UTF_8));
                    lastSec = sec;
                }
            }

        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}