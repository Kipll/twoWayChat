package oneToOneConnectionUDP;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class UDPServerMain extends Thread {

	
	private int port;
	private Game game;
	
	
	
	private InetAddress address;
	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	private byte[] receiveData = new byte[256];
	private byte[] sendData = new byte[256];
	private ArrayList<InetAddress> connections;
	
	
	public UDPServerMain(int port, Game game){
		this.port = port;
		this.game = game;
		this.connections = new ArrayList<InetAddress>();
	}
	
	
	public void run(){
		socket = new DatagramSocket(port);
		address = InetAddress.getLocalHost();
		
		System.out.println("To connect to this server use this address : " + address);
		System.out.println("Waiting for connection...");
		
		while(true){
			InetAddress group = InetAddress.getByName("203.0.113.0");
			packet = new DatagramPacket(sendData, sendData.length, group, 4446);
			DatagramSocket g = new DatagramSocket(port);
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
