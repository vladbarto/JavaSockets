import java.io.IOException;
import java.util.ArrayList;

public class MainClient {
    public static void main(String[] args) throws IOException {
        String ipAddressDevice1 = "127.0.0.1";
        String ipAddressDevice2 = "127.0.0.2";
        String ipAddressDevice3 = "127.0.0.3";
        String portDevice1 = "4444";
        String portDevice2 = "5555";
        String portDevice3 = "6666";

        ArrayList<String> argsClient = new ArrayList<>();
        String mesaj = "1";
        do {
            argsClient.add(ipAddressDevice1);
            argsClient.add(portDevice1);
            argsClient.add(mesaj);
            Client c1 = new Client(argsClient, "ON");
            Client c11 = c1;
            c11.run();

            argsClient.clear();
            argsClient.add(ipAddressDevice2);
            argsClient.add(portDevice2);

            //argsClient.remove(2);
            argsClient.add(c11.line);
//            argsClient.add("mesaj de interes");
            Client c2 = new Client(argsClient, "ON");
            c2.run();

        } while (mesaj.equals("100"));



    }
}
