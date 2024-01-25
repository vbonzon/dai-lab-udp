public enum Instrument{
    piano{
        public String sound(){
            return "ti-ta-ti";
        }
    },
    trumpet{
        public String sound(){
            return "pouet";
        }
    },
    flute{
        public String sound(){
            return "trulu";
        }
    },
    violin{
        public String sound(){
            return "gzi-gzi";
        }
    },
    drum{
        public String sound(){
            return "boum-boum";
        }
    };
    abstract String sound();
}