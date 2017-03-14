package letsGoAgain;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class QuoteServerThread extends Thread {

	private DatagramSocket socket = null;
	private Scanner scanner = new Scanner(System.in);

	public QuoteServerThread() throws IOException {
		this("QuoteServerThread");
	}

	public QuoteServerThread(String name) throws IOException {
		super(name);
		socket = new DatagramSocket(4445);
	}

	public void run() {
		byte[] bytes = new byte[28];
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
		try {
			socket.receive(packet);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			try {
				
				

				ByteBuffer b = ByteBuffer.wrap(bytes);
				
				

				// receive request
				
				for (int i = 0; i < bytes.length; i++) {
					System.out.println(bytes[i]);
				}
				InetAddress address = packet.getAddress();
				int port = packet.getPort();


				bytes = new byte[b.remaining()];
				b.putInt(0, scanner.nextInt());
				b.putInt(4, scanner.nextInt());
				b.putInt(8, scanner.nextInt());
				b.putInt(12, scanner.nextInt());
				b.putInt(16, scanner.nextInt());
				b.putInt(20, scanner.nextInt());
				b.putInt(24, scanner.nextInt());
				System.out.println("------------------");
					bytes = b.array();
				for (int i = 0; i < bytes.length; i++) {
					System.out.println(bytes[i]);
				}
				packet = new DatagramPacket(bytes, bytes.length, address, port);
				socket.send(packet);

			} catch (IOException e) {
				System.err.println("IOEXception occurred in server");
			}
		}
	}

}
