import java.util.UUID;
public class UDPPacket{
    
    public final String uuid;
    public final String sound;

    public UDPPacket(Instrument i) {
        uuid = UUID.randomUUID().toString();
        sound = i.sound();
    }


    public Instrument getInstrument(){
        for(Instrument i : Instrument.values()){
            if(i.sound().equals(sound)){
                return i;
            }
        }
        return null;
    }
}


