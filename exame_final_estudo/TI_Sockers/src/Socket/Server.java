package Socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
	
	public Server(int port) {
		startServer(port);
		readMessages();
	}

	private void startServer(int port) {
		try {
			server = new ServerSocket(port);

	        System.out.println("Server started");
	
	        System.out.println("Waiting for a client ...");
	
	        socket = server.accept();
	        System.out.println("Client accepted");
	
	        // takes input from the client socket
	        in = new DataInputStream(
	            new BufferedInputStream(socket.getInputStream(
	            		)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readMessages() {
		String line = "";
		
		while (!line.equals("Over"))
        {
            try
            {
                line = in.readUTF();
                System.out.println(line);

            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        System.out.println("Closing connection");
	}
	
	public void closeConnection() {
		try {
			socket.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server(5000);
		server.closeConnection();

	}

}
