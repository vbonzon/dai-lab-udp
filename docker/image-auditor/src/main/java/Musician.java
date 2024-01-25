import java.util.Date;

public class Musician {
    private static long timeout = 5000;
    private String uuid;
    private String instrument;
    private long lastTimeHeard;
    

    public Musician(UDPPacket p) {
        instrument = p.getInstrument().name();
        this.uuid = p.uuid; 
        lastTimeHeard = System.currentTimeMillis();
    }

    public void update(){
        lastTimeHeard = System.currentTimeMillis();
    }

    public boolean isTimeOut(){
        return System.currentTimeMillis() - lastTimeHeard > timeout;
    }
}
