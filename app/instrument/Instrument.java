package app.instrument;

public enum Instrument{
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
    }
}
