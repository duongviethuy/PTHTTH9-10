import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket SS = new ServerSocket(5050);
            System.out.println("Server was created !!!");

            while (true) {
                Socket socket = SS.accept();
                Processing processing = new Processing(socket);
                processing.start();

                if (socket == null) {
                    SS.close();
                    break;
                }

            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
