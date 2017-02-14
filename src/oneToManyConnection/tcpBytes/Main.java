package oneToManyConnection.tcpBytes;

import java.util.Scanner;

public class Main {
	private Scanner scanner = new Scanner(System.in);

	public Main() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("would you like to 'host' or 'connect' to a game? :");
		String command = scanner.nextLine();
		while (command.equals("connect") && command.equals("host")) {
			System.out.println("please enter either 'connect' or 'host' ");
			command = scanner.nextLine();
		}
		if (command.equals("connect")) {
			Client client = new Client();
			System.out.print("port number :");
			int port = scanner.nextInt();
			scanner.nextLine();
			System.out.print("host name :");
			String host = scanner.nextLine();
			boolean success = client.connect(port, host);
		} else if (command.equals("host")) {
			
			System.out.print("port number :");
			int port = scanner.nextInt();
			MainServer server = new MainServer(port);
		}
	}
	

	public static void main(String[] args) {
		Main main = new Main();
	}

}
