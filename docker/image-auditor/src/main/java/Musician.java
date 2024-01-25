import java.util.Date;

public class Musician {
    private static long timeout = 5000;
    private String uuid;
    private String instrument;
    private long lastTimeHeared;
    

    public Musician(UDPPacket p) {
        instrument = p.getInstrument().name();
        this.uuid = p.uuid; 
        lastTimeHeared = System.currentTimeMillis();
    }

    public void update(){
        lastTimeHeared = System.currentTimeMillis();
    }

    public boolean isTimeOut(){
        return System.currentTimeMillis() - lastTimeHeared > timeout;
    }
}
