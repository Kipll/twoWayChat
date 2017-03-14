package oneToOneConnectionUDP;

import java.io.*;
import java.net.*;



/**
 * 
 * @author Sam Dowell
 *
 */
public class UDPClient {

	private Sender sender; // the sender thread for the client
	private ClientListener listener; // the listener thread for the client
	private DatagramSocket socket; // the socket connection for the client
	private ClientWindow window; // the window displayed to the client

	/**
	 * constructor for client only initialises the instance variables for the
	 * class
	 */
	public UDPClient() {
		this.sender = null;
		this.listener = null;
		this.socket = null;
		this.window = null;
	}

	/**
	 * 
	 * @param name :the name of the server you wish to connect to
	 * @param port :the port number you wish to connect to
	 * @return true if a connection is successfully established. false otherwise;
	 */
	public boolean connect(String name, int port){
		InetAddress address = InetAddress.getByName(name);
		this.socket = new DatagramSocket(port, address);
		sender = new Sender(socket);
		window = new ClientWindow(sender);
		listener = new ClientListener(window);
		window.cl = listener;
		sender.start();
		listener.start();
		return true;
	}

	
}