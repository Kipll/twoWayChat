package oneToOneConnectionUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sender extends Thread {

	private DatagramSocket socket;
	private BlockingQueue<DatagramPacket> queue;

	public Sender(DatagramSocket socket) {
		this.socket = socket;
		this.queue = new LinkedBlockingQueue<DatagramPacket>();
	}

	public void run() {
		while (true) {
			DatagramPacket tmp;
			try {
				tmp = queue.take();
				socket.send(tmp);
			} catch (IOException | InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public void addToQueue(DatagramPacket packet) {
		queue.add(packet);
	}
}
