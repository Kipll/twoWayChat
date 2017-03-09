package oneToOneConnectionUDP;

import java.io.*;
import java.net.*;

public class UDPClient {

	public UDPClient(String name, String port) {

	}

	public static void main(String args[]) throws Exception {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[3];
		byte[] receiveData = new byte[3];
		String sentence = inFromUser.readLine();
		sendData = sentence.getBytes();
		for (int i = 0; i < sendData.length; i++) {

			System.out.println(Byte.toUnsignedInt(sendData[i]));
		}
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		byte[] data = new byte[3];
		data = receivePacket.getData();
		for (int i = 0; i < data.length; i++) {

			System.out.println(Byte.toUnsignedInt(data[i]));
		}
		// System.out.println("FROM SERVER:" + modifiedSentence);
		System.out.println(receivePacket.getLength());
		clientSocket.close();
	}
}