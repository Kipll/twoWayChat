package oneToOneConnection.tcpObjects;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sender extends Thread {
	private ObjectOutputStream out;
	private BlockingQueue<Message> queue;

	public Sender(ObjectOutputStream out) {
		this.out = out;
		this.queue = new LinkedBlockingQueue<Message>();
	}

	public void run() {

		try {
			while (true) {
				out.writeObject(queue.take());
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addToQueue(Message message) {

		queue.add(message);

	}
}
