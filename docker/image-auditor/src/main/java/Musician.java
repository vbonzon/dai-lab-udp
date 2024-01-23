import java.util.Date;

public class Musician {
    private static long timeout = 5000;
    private String uuid;
    private Instrument instrument;
    private long lastTimeHeared;
    

    public Musician(Instrument i) {
        instrument = i;
        lastTimeHeared = System.currentTimeMillis();
    }

    public void update(){
        lastTimeHeared = System.currentTimeMillis();
    }

    public boolean isTimeOut(){
        return System.currentTimeMillis() - lastTimeHeared > timeout;
    }
}
