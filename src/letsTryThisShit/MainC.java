package letsTryThisShit;

public class MainC {

	public static void main(String[] args) {
		TCPClientMain c = new TCPClientMain(4444, "localhost");
		c.addToQueue(11, 12, 13);
		System.out.println("got here");
	}

}
