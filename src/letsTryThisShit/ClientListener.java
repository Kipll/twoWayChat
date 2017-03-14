package letsTryThisShit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class ClientListener extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] recievedData;

	public ClientListener(DatagramSocket socket) {
		this.socket = socket;
		this.recievedData = new byte[12];
		this.packet = new DatagramPacket(recievedData, recievedData.length);
	}

	public void run() {
		while (true) {
			try {
				socket.receive(packet);
				recievedData = packet.getData();
				int[] data = new int[7];
				data = toInts(recievedData);
				System.out.println("testing");
				for (int i = 0; i < data.length; i++) {
					System.out.println(data[i]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private int[] toInts(byte[] data) { // this is currently presuming the
		// input is of size 7
		int[] ints = new int[7];
		byte[] temp = new byte[4]; // byte array for one integer;
		int count = 0;
		ByteBuffer b = ByteBuffer.wrap(temp);
		for (int i = 0; i < 4; i++) {
			temp[i] = data[count];
			count++;
		}
		count = 0;
		ints[0] = b.getInt();
		b.rewind();

		for (int i = 4; i < 8; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[1] = b.getInt();
		b.rewind();

		for (int i = 8; i < 12; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();

		for (int i = 12; i < 16; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();

		for (int i = 16; i < 20; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();

		for (int i = 20; i < 24; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();
		for (int i = 24; i < 28; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();
		return ints;
	}
}
