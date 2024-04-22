import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: BG
 * Date: Apr 18, 2005
 * Time: 12:05:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
	Socket socket = null;
	DataOutputStream out = null;
	BufferedReader in = null;
	BufferedReader c = new BufferedReader(
			new InputStreamReader(System.in));
	String line = null;
	ArrayList<String> args;
	String status;

	public Client(ArrayList<String> args, String status) {
		this.args = args;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void startClient() {
		System.out.println("\nClient " + args.get(0).charAt(args.get(0).length()-1) + " pornit!/Client online!");
		try {
			socket = new Socket(args.get(0), Integer.parseInt(args.get(1)));
			in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());

			System.out.println("Socket info from client: " + socket);
			System.out.print("\nMesajul trimis:" + args.get(2));

			Integer mesajCurent = Integer.parseInt(args.get(2));
			mesajCurent++;

			String mesajCurentAsAString = "" + mesajCurent;
			out.writeBytes(mesajCurentAsAString + "\n");
			out.flush();
			System.out.println("\nTrimis la server/Sent to the server:" + mesajCurentAsAString);
			line = in.readLine();
			TimeUnit.SECONDS.sleep(1);
			setStatus("OFF");
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

	public void run() {
		startClient();
	}
	/*


	in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
	out = new DataOutputStream(socket.getOutputStream());

			while(true) {
		System.out.print("\nScrie mesajul aici:/Write your message here:");
		line = c.readLine();
		out.writeBytes(line + "\n");
		out.flush();

		System.out.println("Trimis la server/Sent to the server:" + line);
		line= in.readLine();
		System.out.println("Receptionat de la server/Receiption from the server:" + line);
	}*/

}