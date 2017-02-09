package testing;

import java.util.Scanner;

import oneToOneConnection.*;

public class Main {

	public static void main(String[] args) {
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
			int port = Integer.parseInt(scanner.nextLine());
			System.out.print("host name :");
			String host = scanner.nextLine();
			boolean success = client.connect(port, host);
			if (success) {

				while (true) {
					byte[] input = new byte[4];
					for(int i = 0; i < 4; i++) {
						String s = scanner.nextLine();
						byte b = Byte.valueOf(s, 2);
						input[i] = b;
					}
					client.addToQueue(input);
				}

			}
		} else if (command.equals("host")) {
			Server server = new Server();
			System.out.print("port number :");
			int port = Integer.parseInt(scanner.nextLine());
			server.listen(port);
			while (true) {
				byte[] input = new byte[4];
				for(int i = 0; i < 4; i++) {
					String s = scanner.nextLine();
					byte b = Byte.valueOf(s, 2);
					input[i] = b;
				}
				server.addToQueue(input);
			}
		}
	}
}
