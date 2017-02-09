package oneToOneConnectionTCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sender extends Thread {
	private DataOutputStream out;
	private BlockingQueue<byte[]> queue;

	public Sender(DataOutputStream out) {
		this.out = out;
		this.queue = new LinkedBlockingQueue<byte[]>();
	}

	public void run() {

		try {
			while (true) {
				out.write(queue.take());
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addToQueue(byte[] input) {
		if (input.length == 4) {
			queue.add(input);
		}
	}
}
