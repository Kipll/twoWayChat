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
	private byte[] receiveData = new byte[256];
	private byte[] sendData = new byte[256];
	
	
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
	
	public static void main(String args[]) throws Exception
    {
       DatagramSocket serverSocket = new DatagramSocket(9876);
          byte[] receiveData = new byte[3];
          byte[] sendData = new byte[3];
          while(true)
             {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String( receivePacket.getData());
                System.out.println("RECEIVED: " + sentence);
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
             }
    }
}
