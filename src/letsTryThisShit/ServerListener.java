package letsTryThisShit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class ServerListener extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] recievedData;

	public ServerListener(DatagramSocket socket /* Player p */) {
		this.socket = socket;
		this.recievedData = new byte[12];
		this.packet = new DatagramPacket(recievedData, recievedData.length);
	}

	public void run() {
		while (true) {
			try {
				socket.receive(packet);
				recievedData = packet.getData();
				int[] data = new int[7];
				data = toInts(recievedData);
				for (int i = 0; i < data.length; i++) {
					System.out.println(data[i]);
				}
				
				int flag = data[0];
				int keycodeOrCoordX = data[1];
				int coordY = data[2];
				
				/*switch(flag){
				case 0: //key released
					p.keyboard.keyCodeReleased(keycodeOrCoordX);
					break;
				case 1: //key pressed
					p.keyboard.keyCodePressed(keycodeOrCoordX);
					break;
				case 2: //lmb released
					p.mouse.mouseCodeReleased(0);
					break;
				case 3: //lmb pressed
					p.mouse.mouseCodePressed(0);
					break;
				case 4: //rmb released
					p.mouse.mouseCodeReleased(1);
					break;
				case 5: //rmb pressed
					p.mouse.mouseCodePressed(1);
					break;
				case 6: //mouse moved
					p.mouse.setPos(keycodeOrCoordX, coordY);
					break;
				case 7: //window resized
					p.viewport.screenW = keycodeOrCoordX;
					p.viewport.screenH = coordY;
					break;
				default:
				}
*/
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private int[] toInts(byte[] data) { // this is currently presuming the
										// input is of size 3
		int[] ints = new int[3];
		byte[] temp = new byte[4]; // byte array for one integer;
		int count = 0;
		ByteBuffer b = ByteBuffer.wrap(temp);
		for (int i = 0; i < 4; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[0] = b.getInt();
		b.rewind();

		for (int i = 4; i < 8; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[1] = b.getInt();
		b.rewind();

		for (int i = 8; i < 12; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();
		return ints;
	}
}