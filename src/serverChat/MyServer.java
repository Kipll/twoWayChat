package serverChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class MyServer {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input port wanted for client connection (4444 is suggested) : ");
		int portNumber = Integer.parseInt(scanner.nextLine());
		try {
			ServerSocket serverSocket = new ServerSocket(portNumber);
			String name = getName();
			
			System.out.println("To connect to this server use this name : " + name);
			System.out.println("Waiting for connection...");
			Socket clientSocket = serverSocket.accept();

			ServerSender sender = new ServerSender(new DataOutputStream(clientSocket.getOutputStream()), name);
			ServerListener listener = new ServerListener(new DataInputStream(clientSocket.getInputStream()));
			sender.start();
			listener.start();
			while (true) {
				sender.addToQueue(scanner.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String getName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
}