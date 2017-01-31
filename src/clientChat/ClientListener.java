package clientChat;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientListener extends Thread {
	private DataInputStream in;

	public ClientListener(DataInputStream in) {
		this.in = in;
	}

	public void run() {

		try {
			while (true) {
				System.out.println(in.readUTF());
			}
		} catch (IOException e) {
			System.out.println("Connection to server was lost, exiting.");
		}
	}

}
