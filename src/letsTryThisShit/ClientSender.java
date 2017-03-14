package letsTryThisShit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientSender extends Thread {

	private BlockingQueue<byte[]> queue;
	private byte[] data;
	private DatagramPacket packet;
	private DatagramSocket socket;
	private int remotePort;
	private InetAddress inetAddress;

	public ClientSender(int serverPort, InetAddress inetAddress, DatagramSocket socket) {
		this.queue = new LinkedBlockingQueue<>(); // queue for sending
		// packets;
		this.data = new byte[12]; // byte array of length 12 with is the
		// same as 3 ints
		this.packet = new DatagramPacket(data, data.length); // datagram
		// packet
		// which
		// sends
		// whatever
		// bytes are
		// in data
		this.socket = socket; // the socket data is being send on
		this.remotePort = serverPort;
		this.inetAddress = inetAddress;
	}

	public void run() {
		while (true) {
			try {
				this.data = queue.take();
				packet = new DatagramPacket(data, data.length, inetAddress, remotePort);
				socket.send(packet);
			} catch (InterruptedException | IOException e) {

				e.printStackTrace();
			}
		}
	}

	public void addToQueue(int a, int b, int c) {
		byte[] temp = new byte[12];
		ByteBuffer buf = ByteBuffer.wrap(temp);
		buf.putInt(0, a);
		buf.putInt(4, b);
		buf.putInt(8, c);

		buf.clear();
		try {
			queue.put(temp);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

}

