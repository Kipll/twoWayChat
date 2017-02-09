package oneToOneConnection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import sun.security.util.Length;

public class Sender extends Thread {
	private DataOutputStream out;
	private BlockingQueue<byte[]> queue;
	private Scanner scanner;

	public Sender(DataOutputStream out, String serverName) {

		this.out = out;
		this.queue = new LinkedBlockingQueue<byte[]>();
		this.scanner = new Scanner(System.in);

	}
	public void run() {
		
		try {
			byte[] connectSignal = new byte[] {00000000,00000000,00000000,00000000};
			out.write(connectSignal);
			while (true) {
				//addToQueue(scanner.nextLine());
				out.write(queue.take());
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addToQueue(byte[] input) {
		if(input.length == 4)
		{
		queue.add(input);
		}
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
