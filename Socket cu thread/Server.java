import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.util.ArrayList;
/*
 * Created by IntelliJ IDEA.
 * User: BG
 * Date: Apr 18, 2005
 * Time: 12:23:20 PM
 * To change this template use File | Settings | File Templates.
 */

public class Server {
    ServerSocket serverSocket = null;
    boolean listening = true;
    ArrayList<String> args = new ArrayList<>();

    public Server(ArrayList<String> args) {
        this.args = args;
    }

    private void startServer() throws IOException {

        try {
            serverSocket = new ServerSocket(Integer.parseInt(args.getFirst()));
        } catch (IOException e) {
            System.err.println("Eroare pe port:/Error on the port:" + Integer.parseInt(args.getFirst()));
            System.exit(-1);
        }

        while (listening) {
            System.out.println("waiting...");
            Worker w = new Worker(serverSocket.accept());
            w.start();
            listening = w.listening;
            if(!listening) {
                w.interrupt();
            }
        }

        serverSocket.close();

    }

    public void run() {
        try {
            startServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
class Worker extends Thread {
    private Socket socket = null;

    DataOutputStream out = null;
    BufferedReader in = null;
    boolean flag;
    String line;
    boolean listening = true;

    public Worker(Socket socket) {
        super("Worker");
        this.socket = socket;
        System.out.println("\nSocket info from server: " + socket);
    }

    public void run() {

        try {
            in = new BufferedReader(
                                  new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Client acceptat!/Accepted client!\n");



               line = in.readLine();
               System.out.println("Primit de la client/Received from the client:" + line + "\n");
               Integer mesajCurent = Integer.parseInt(line);
               mesajCurent++;
               out.writeBytes("" + mesajCurent + "\n");



            out.close();
            in.close();
            socket.close();
            listening = false;

        } catch (IOException e) { e.printStackTrace(); }
    }
}