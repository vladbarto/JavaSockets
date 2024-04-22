import java.util.ArrayList;

public class MainServer {
    public static void main(String[] args) {
        String portDevice1 = "4444";
        String portDevice2 = "5555";
        String portDevice3 = "6666";

        ArrayList<String> argsServer = new ArrayList<>();
        do {
            argsServer.add(portDevice1);
            Server s1 = new Server(argsServer);
            s1.run();

            argsServer.clear();
//            argsServer.add(portDevice2);
//            Server s2 = new Server(argsServer);
//            s2.run();

        } while (true);
    }
}
