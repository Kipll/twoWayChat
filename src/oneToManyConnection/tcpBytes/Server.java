package oneToManyConnection.tcpBytes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Server extends Thread {

	private Sender sender;
	private Listener listener;
	private Socket clientSocket;

	public Server(Socket clientSocket) {
		this.sender = null;
		this.listener = null;
		this.clientSocket = clientSocket;
	}

	public void run() {
		try {

			this.sender = new Sender(new DataOutputStream(clientSocket.getOutputStream()));
			this.listener = new Listener(new DataInputStream(clientSocket.getInputStream()));
			sender.start();
			listener.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}