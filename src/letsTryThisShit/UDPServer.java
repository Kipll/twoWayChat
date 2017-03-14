package letsTryThisShit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//import game.Player;

public class UDPServer {

	
	private ServerSender sender;
	private ServerListener listener;
	
	public UDPServer(DatagramSocket socket, int p, InetAddress inetAddress) {

	
		this.sender = new ServerSender(p, inetAddress, socket);
		this.listener = new ServerListener(socket);
		sender.start();
		listener.start();
	}
	
	
	public void addToQueue(int a, int b, int c, int d, int e, int f, int g) {
		sender.addToQueue(a, b, c, d, e, f, g);
	}

	

	
}
