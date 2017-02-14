package oneToOneConnection.tcpObjects;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Listener extends Thread {

	private ObjectInputStream in;

	public Listener(ObjectInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			while (true) {
				try {
					System.out.println(in.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println(e + " : Connection was lost");

		}
	}

}
