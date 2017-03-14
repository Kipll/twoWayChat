package letsTryThisShit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerSender extends Thread {

	private BlockingQueue<byte[]> queue;
	private byte[] data;
	private DatagramPacket packet;
	private DatagramSocket socket;
	private int remotePort;
	private InetAddress inetAddress;

	public ServerSender(int p, InetAddress inetAddress, DatagramSocket socket) {
		this.queue = new LinkedBlockingQueue<>(); // queue for sending
													// packets;
		this.data = new byte[28]; // byte array of length 28 with is the
									// same as 7 ints
		this.packet = new DatagramPacket(data, data.length); // datagram
																// packet
																// which
																// sends
																// whatever
																// bytes are
																// in data
		this.socket = socket; // the socket data is being send on
		this.remotePort = p;
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

	public void addToQueue(int a, int b, int c, int d, int e, int f, int g) {
		byte[] temp = new byte[28];
		ByteBuffer buf = ByteBuffer.wrap(temp);
		buf.putInt(0, a);
		buf.putInt(4, b);
		buf.putInt(8, c);
		buf.putInt(12, d);
		buf.putInt(16, e);
		buf.putInt(20, f);
		buf.putInt(24, g);
		buf.clear();
		try {
			queue.put(temp);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

}