package letsTryThisShit;

public class MainS {

	public static void main(String[] args){
		TCPMainServer s = new TCPMainServer(4444);
		System.out.println("after constructor");
		s.run();
		System.out.println("after run");

		s.broadcastMessage(1, 2, 3, 4, 5, 6, 7);
		
		
	}
}
