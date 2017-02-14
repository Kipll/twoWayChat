package oneToManyConnection.tcpBytes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import oneToOneConnection.*;

public class Client {

	private Sender sender;
	private Listener listener;
	private boolean connected;

	public Client() {
		this.sender = null;
		this.listener = null;
		this.connected = false;
	}

	public boolean connect(int port, String name) {
		try {

			Socket server = new Socket(name, port);
			sender = new Sender(new DataOutputStream(server.getOutputStream()));
			listener = new Listener(new DataInputStream(server.getInputStream()));
			sender.start();
			listener.start();
			return true;
		} catch (IOException e) {
			System.out.println("couldnt connect to specified host, connection refused.");
			e.printStackTrace();
			return false;
		}
	}

	

}
