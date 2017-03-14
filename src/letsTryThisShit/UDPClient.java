package letsTryThisShit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UDPClient {

	private DatagramSocket socket;
	private int port;
	private InetAddress inetAddress;
	private ClientSender sender;
	private ClientListener listener;

	public UDPClient(DatagramSocket socket, int serverPort, InetAddress serverAddress) {
		this.socket = socket;
		this.port = serverPort;
		this.inetAddress = serverAddress;
		this.sender = new ClientSender(serverPort, inetAddress, socket);
		this.listener = new ClientListener(socket);
		sender.start();
		listener.start();
	}

	public void addToQueue(int a, int b, int c) {
		sender.addToQueue(a, b, c);
	}

}
