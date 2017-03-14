package letsTryThisShit;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClientMain {
	
	private int port;
	private String name;
	private UDPClient udpC;
	
	public TCPClientMain(int port, String name) {
		this.port = port;
		this.name = name;
		
		try {
			Socket s = new Socket(name, port); //create new socket with given port and name;
			int serverPort = s.getPort(); // gets the port number of the server;
			int localPort = s.getLocalPort();
			InetAddress serverAddress = s.getInetAddress(); // gets the Inet address of the server
			s.close();
			
			DatagramSocket socket = new DatagramSocket(localPort);
			this.udpC = new UDPClient(socket, serverPort, serverAddress);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	public void addToQueue(int a, int b, int c) {
		udpC.addToQueue(a, b, c);
	}
}
