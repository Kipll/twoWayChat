package serverChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerSender extends Thread {
	private DataOutputStream out;
	private BlockingQueue<String> queue;

	public ServerSender(DataOutputStream out, String serverName) {
		this.out = out;
		this.queue = new LinkedBlockingQueue<String>();
		
	}

	public void run() {
		try {
			while (true) {
				out.writeUTF(queue.take());
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();

		}
	}
	public void addToQueue(String input) {
		queue.add(input);
	}
	
	private String getServerName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
}


