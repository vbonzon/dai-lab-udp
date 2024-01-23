import java.io.*;
import java.net.*;
import static java.nio.charset.StandardCharsets.*;
import com.google.gson.Gson;

public class TCPListener implements Runnable {

    private MusicianListener musicianListener; 
    final static int PORT = 2205;
    private Gson gson;
    public TCPListener(MusicianListener listener) {
        musicianListener = listener;
        gson = new Gson();
    }


    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true){
                System.out.println("Waiting connection...");
                try (Socket socket = serverSocket.accept();
                    var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), UTF_8))) {
                        var musicians = musicianListener.getMusicians();
                        for(Musician m : musicians){
                            if(m.isTimeOut()){
                                musicians.remove(m);
                            }
                        }

                        String output = gson.toJson(musicians);

                        out.write(output + "\n");
                        out.flush();

                    
                } catch (Exception e) {
                    System.out.println("Problem during TCP connection : " + e);
                }
            }
        } catch (Exception e) {
            System.out.println("Problem during serverSocket creation : " + e);
        }

        
    }
    
}
