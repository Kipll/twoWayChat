package serverChat;

import java.io.DataInputStream;
import java.io.IOException;

public class ServerListener extends Thread {

	private DataInputStream in;

	public ServerListener(DataInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			while (true) {
				System.out.println(in.readUTF());
			}
		} catch (IOException e) {
			System.out.println("Connection to client was lost");

		}
	}

}
