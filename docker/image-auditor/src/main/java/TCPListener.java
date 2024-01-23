public class TCPListener implements Runnable {

    @Override
    public void run() {
        while (true) {
            
        
        System.out.println("tcp");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    }
    
}
