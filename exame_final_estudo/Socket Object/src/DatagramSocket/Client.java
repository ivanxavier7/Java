package DatagramSocket;

import java.io.*;
import java.net.*;
import DatagramSocket.Student;
 
public class Client {
    // initialize socket and input output streams
    private Socket client = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
 
    public Client(String address, int port)
    {
    	try {
    		client = new Socket("127.0.0.1", 8888);
    		out = new ObjectOutputStream(client.getOutputStream());
    		in = new ObjectInputStream(client.getInputStream());
    		
    		Student student = new Student(96, "John");
    		out.writeObject(student);
    		out.flush();
    		
    		System.out.println("Object sent");
    		
    		out.close();
    		in.close();
    		client.close();		
    	}
    	catch (UnknownHostException e) {
	    	e.printStackTrace();
	    	System.exit(1);
    	} catch (IOException e) {
	    	e.printStackTrace();
	    	System.exit(1);
    	}
    }
 
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}
