import java.util.Date;

public class Musician {
    private static long timeout = 5000;
    private String uuid;
    private Instrument.Sound instrument;
    private long lastTimeHeared;
    

    public Musician(Instrument i) {
        instrument = i.getInstrument();
        this.uuid = i.uuid; 
        lastTimeHeared = System.currentTimeMillis();
    }

    public void update(){
        lastTimeHeared = System.currentTimeMillis();
    }

    public boolean isTimeOut(){
        return System.currentTimeMillis() - lastTimeHeared > timeout;
    }
}
