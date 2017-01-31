package clientChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("please input the name of the server you wish to connect to: ");
		String serverName = scanner.nextLine();
		System.out.print("please input the port number you wish to connect to: ");
		int portNumber = Integer.parseInt(scanner.nextLine());
		try {

			Socket server = new Socket(serverName, portNumber);
			ClientSender sender = new ClientSender(new DataOutputStream(server.getOutputStream()));
			ClientListener listener = new ClientListener(new DataInputStream(server.getInputStream()));
			sender.start();
			listener.start();
			while (true) {
				sender.addToQueue(scanner.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
