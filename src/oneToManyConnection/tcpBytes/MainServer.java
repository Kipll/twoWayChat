package oneToManyConnection.tcpBytes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class MainServer {

	private Map<String, Server> upConnections;

	public MainServer(int port) {

		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			String name = getLocalName();

			System.out.println("To connect to this server use this name : " + name);
			System.out.println("Waiting for connection...");

			while (true) {

				Socket clientSocket = serverSocket.accept();
				System.out.println("connection to " + clientSocket.getLocalAddress());
				new Server(clientSocket).start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private String getLocalName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
}
