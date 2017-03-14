package letsTryThisShit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TCPMainServer extends Thread{

	private int port; // port to listen for new connections
	private ConcurrentLinkedQueue<UDPServer> connections; // list of all connections

	public TCPMainServer(int port) {
		this.connections = new ConcurrentLinkedQueue<UDPServer>();
		this.port = port;

	}

	public void run(){
		ServerSocket serverSocket;
		while (true) {

			try {
				serverSocket = new ServerSocket(port);

				String name = getLocalName();

				System.out.println("To connect to this server use this name : " + name);
				System.out.println("Waiting for connection...");

				Socket clientSocket = serverSocket.accept(); // accept a new
																// connection,
																// allocates
																// ports for
																// connection

				int p = clientSocket.getLocalPort(); // gets local port of
														// connection

				int p2 = clientSocket.getPort(); // gets remote port of the
													// connection
				InetAddress ip = clientSocket.getInetAddress(); // gets the Inet
																// address of
																// client
																// connection

				clientSocket.close(); // closes the socket so can be used for as
										// Datagram Socket
				DatagramSocket socket = new DatagramSocket(p); // create a
																// Datagram
																// socket using
																// the port
																// selected from
																// serversocket.accept()
				connections.add(new UDPServer(socket, p2, ip)); // creates a new
																// UDPServer
																// with the
																// Datagram
																// Server object
																// while adding
																// it to
																// connections
			} catch (IOException e) {

			}
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

	public void broadcastMessage(int a, int b, int c, int d, int e, int f, int g) {
		for (UDPServer temp : connections) {
			System.out.println("testing broadcastMessage");
			temp.addToQueue(a, b, c, d, e, f, g);
		}
	}
}
