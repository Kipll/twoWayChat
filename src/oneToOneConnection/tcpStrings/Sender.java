package oneToOneConnection.tcpStrings;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sender extends Thread {
	private DataOutputStream out;
	private BlockingQueue<String> queue;

	public Sender(DataOutputStream out) {
		this.out = out;
		this.queue = new LinkedBlockingQueue<String>();
	}

	public void run() {

		try {
			while (true) {
				out.writeUTF(queue.take());
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addToQueue(String message) {

		queue.add(message);

	}
}
