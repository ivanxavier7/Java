package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream out = null;
	
	
	private Client(String ip, int port) {
		this.establishConnection(ip, port);
		this.readMessage();
	}
	
	public void establishConnection(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected");
 
            // takes input from terminal
            input = new DataInputStream(System.in);
 
            // sends output to the socket
            out = new DataOutputStream(
                socket.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
	}
	
	private void readMessage() {
		String line = "";
		
		while(!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);
                
            }
            catch (IOException i) {
                System.out.println(i);
            }
		}
	}
	
	public void closeConnection() {
		try {
			input.close();
			out.close();
			socket.close();
		}
		catch (IOException i) {
			System.out.println(i);
		}
	}

	public void changeSocket(String ip, int port) {
		this.establishConnection(ip, port);
		this.readMessage();
	}
	

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 5000);
		client.closeConnection();
	}

}
