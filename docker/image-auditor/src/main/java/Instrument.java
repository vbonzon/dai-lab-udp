import java.util.UUID;

public class Instrument{
    public enum Sound{
        piano{
            public String doSound(){
                return "ti-ta-ti";
            }
        },
        trumpet{
            public String doSound(){
                return "pouet";
            }
        },
        flute{
            public String doSound(){
                return "trulu";
            }
        },
        violin{
            public String doSound(){
                return "gzi-gzi";
            }
        },
        drum{
            public String doSound(){
                return "boum-boum";
            }
        };
        abstract String doSound();
    }
    public final String uuid;
    public final String sound;

    public Instrument(Sound s) {
        uuid = UUID.randomUUID().toString();
        sound = s.doSound();
    }


    public Instrument.Sound getInstrument(){
        for(Instrument.Sound s : Instrument.Sound.values()){
            if(s.doSound() == sound){
                return s;
            }
        }
        return null;
    }
}


