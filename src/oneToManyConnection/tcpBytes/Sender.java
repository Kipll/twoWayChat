package oneToManyConnection.tcpBytes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sender extends Thread {
	private Scanner s;
	private DataOutputStream out;
	private BlockingQueue<byte[]> queue;

	public Sender(DataOutputStream out) {
		this.out = out;
		this.queue = new LinkedBlockingQueue<byte[]>();
		this.s = new Scanner(System.in);
	}

	public void run() {

		try {
			while (true) {
				addToQueue();
				out.write(queue.take());
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	
	public void addToQueue() {
		byte[] input = new byte[4];
		for (int i = 0; i < 4; i++) {
			String str = s.nextLine();
			byte b = Byte.valueOf(str, 2);
			input[i] = b;
		}
		try {
			queue.put(input);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
