import java.util.Date;

public class Musician {
    private static long timeout = 5000;
    private Instrument instrument;
    private long lastTimeHeared;
    private String uuid;

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
