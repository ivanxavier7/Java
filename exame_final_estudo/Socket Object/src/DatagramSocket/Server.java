package DatagramSocket;

import java.net.*;
import java.io.*;

public class Server
{

 private Socket client = null;
 private ObjectOutputStream out = null;
 private ObjectInputStream in = null;


 public Server(int port)
 {
	try {
		ServerSocket server = new ServerSocket(8888);
		System.out.println("Server is listening...");
		client = server.accept();
		out = new ObjectOutputStream(client.getOutputStream());
		in = new ObjectInputStream(client.getInputStream());
	
		Student student = (Student) in.readObject();

		System.out.println("Average: " + student.getStudentAvg() + " Name: " + student.getStudentName());
		 
		out.close();
		in.close();
		client.close();
		server.close();
		
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
	}
 }

 public static void main(String args[])
 {
     Server server = new Server(5000);
 }
}