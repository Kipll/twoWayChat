package oneToOneConnectionUDP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;

public class UDPServer {

	
	private int port;
	private InetAddress address;
	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	byte[] sendBuf = new byte[256];
	
	public UDPServer(int port){
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
			BufferedInputStream in = new BufferedInputStream(System.in);

				byte[] buf = new byte[256];
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				try {
					socket.receive(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
}
