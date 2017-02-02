package clientChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientSender extends Thread {
	private DataOutputStream out;
	private BlockingQueue<String> queue;

	public ClientSender(DataOutputStream out) {
		this.queue = new LinkedBlockingQueue<String>();
		this.out = out;
		try {
			out.writeUTF(getClientName() + " just connected!");
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void addToQueue(String input) {
		queue.add(input);
	}

	private String getClientName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
}
