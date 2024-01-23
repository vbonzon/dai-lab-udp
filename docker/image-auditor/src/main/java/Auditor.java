import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Auditor{
    public static void main(String[] args) {
        System.out.println("Démarrage Auditor");

        MusicianListener musicianListener = new MusicianListener();
        TCPListener tcpListener = new TCPListener();
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        try{
            executor.execute(tcpListener);
            executor.execute(musicianListener);
            while (true) {
                
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}