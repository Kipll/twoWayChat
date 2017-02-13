package oneToOneConnection.tcpBytes;

import java.io.DataInputStream;
import java.io.IOException;

public class Listener extends Thread {

	private DataInputStream in;

	public Listener(DataInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			while (true) {
				System.out.println(in.read());
			}
		} catch (IOException e) {
			System.out.println(e + " : Connection was lost");

		}
	}

}
