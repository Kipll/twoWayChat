package oneToOneConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Server extends Thread {

	private Sender sender;
	private Listener listener;

	public Server() {
		this.sender = null;
		this.listener = null;
	}

	public void listen(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);

			String name = getLocalName();

			System.out.println("To connect to this server use this name : " + name);
			System.out.println("Waiting for connection...");

			Socket clientSocket = serverSocket.accept();
			Sender sender = new Sender(new DataOutputStream(clientSocket.getOutputStream()), name);
			Listener listener = new Listener(new DataInputStream(clientSocket.getInputStream()));
			sender.start();
			listener.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addToQueue(byte[] input) {
		if (input.length == 4 && sender != null) {
			sender.addToQueue(input);
		}
	}

	private String getLocalName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
}