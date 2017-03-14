package

import java.awt.Color;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import game.Spritesheet;
import game.SpritesheetEnum;
import oneToManyConnection.tcpBytes.Client;

public class ClientListener extends Thread {

	private MulticastSocket socket;
	private DatagramPacket packet;
	private byte[] packetData;
	private int[] packetDataInt;

	private static int packetSize = 7;

	public ClientWindow panel;
	public Graphics2D g;

	public ClientListener(ClientWindow panel) {
		this.panel = panel;

		this.packetData = new byte[packetSize];
		this.packet = new DatagramPacket(packetData, packetSize);
		this.packetDataInt = new int[packetSize];

	}

	public void run() {
		socket = new MulticastSocket(4446);
		InetAddress group = InetAddress.getByName("203.0.113.0");
		socket.joinGroup(group);
		while (true) {
			socket.receive(packet);
			
			packetData = packet.getData();
			packetDataInt = toInts(packetData);

			if (packetDataInt[0] >= 0) {

				Spritesheet sprs = SpritesheetEnum.getSprite(packetDataInt[0]);

				g.drawImage(sprs.img, packetDataInt[1], packetDataInt[2], packetDataInt[3], packetDataInt[4],
						sprs.offsetW + sprs.spriteW * packetDataInt[5], sprs.offsetH + sprs.spriteH * packetDataInt[6],
						sprs.offsetW + sprs.spriteW * (packetDataInt[5] + 1) - 1,
						sprs.offsetH + sprs.spriteH * (packetDataInt[6] + 1) - 1, null);
			}
			else if(packetDataInt[0]==-1) panel.paintImmediately(panel.getBounds());
			else if(packetDataInt[0]==-2){
				g.setColor(new Color(packetDataInt[1], packetDataInt[2], packetDataInt[3]));
				g.fillOval(packetDataInt[4], packetDataInt[5], packetDataInt[6], packetDataInt[6]);
			}

		}
	}

	private int[] toInts(byte[] packetData) {
		Byte[] bytesObjects = new Byte[packetSize];
		int[] intValues = new int[packetSize];
		for (int i = 0; i < packetSize; i++) {
			bytesObjects[i] = new Byte(packetData[i]);
			intValues[i] = bytesObjects[i].intValue();
		}
		return intValues;

	}

}
