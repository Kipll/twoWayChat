package oneToOneConnection.tcpObjects;

import java.io.Serializable;

public class Message implements Serializable{
	private String message;
	public Message(String nextLine) {
		this.message = nextLine;
	}
	
	public String getMessage(){
		return this.message;
	}

	public String toString(){
		return this.message;
	}
}
