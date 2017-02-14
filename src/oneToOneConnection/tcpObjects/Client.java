package oneToOneConnection.tcpObjects;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			sender = new Sender(new ObjectOutputStream(server.getOutputStream()));
			listener = new Listener(new ObjectInputStream(server.getInputStream()));
			sender.start();
			listener.start();
			return true;
		} catch (IOException e) {
			System.out.println("couldnt connect to specified host, connection refused.");
			e.printStackTrace();
			return false;
		}
	}

	public void addToQueue(Message message) {
		if (sender != null) {
			sender.addToQueue(message);
		}
	}

}
