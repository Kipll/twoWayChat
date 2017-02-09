package serverChat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerSender extends Thread {
	private DataOutputStream out;
	private BlockingQueue<String> queue;
	private Scanner scanner;

	public ServerSender(DataOutputStream out, String serverName) {

		this.out = out;
		this.queue = new LinkedBlockingQueue<String>();
		this.scanner = new Scanner(System.in);

	}

	public void run() {
		try {
			out.writeUTF("you have connected to server: " + getServerName());
			while (true) {
				addToQueue(scanner.nextLine());
				out.writeUTF(queue.take());
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
