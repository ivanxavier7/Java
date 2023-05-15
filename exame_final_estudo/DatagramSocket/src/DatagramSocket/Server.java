package DatagramSocket;

import java.net.*;
import java.io.*;
import DatagramSocket.Collaborator;

public class Server
{
 //initialize socket and input stream
 private Socket          socket   = null;
 private ServerSocket    server   = null;
 private DataInputStream in       = null;
 private Collaborator 	 collab   = null;

 // constructor with port
 public Server(int port)
 {
     // starts server and waits for a connection
     try
     {
         server = new ServerSocket(port);
         System.out.println("Server started");

         System.out.println("Waiting for a client ...");

         socket = server.accept();
         System.out.println("Client accepted");

         // takes input from the client socket
         in = new DataInputStream(
             new BufferedInputStream(socket.getInputStream()));

         String line = "";

         // reads message from client until "Over" is sent
         while (!line.equals("Over"))
         {
             try
             {
                 //line = in.readUTF();
                 //System.out.println(line);
            	 String filename = "file.ser";
            	 FileInputStream file = new FileInputStream(filename);
                 ObjectInputStream in = new ObjectInputStream(file);
                 collab = (Collaborator)in.readObject();
             }
             catch(IOException i)
             {
                 System.out.println(i + " - 1");
             } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         System.out.println("Closing connection");

         // close connection
         socket.close();
         in.close();
         
         System.out.println("Object has been deserialized ");
         System.out.println("a = " + collab.a);
         System.out.println("b = " + collab.b);
     }
     catch(IOException i)
     {
         System.out.println(i);
     }
 }

 public static void main(String args[])
 {
     Server server = new Server(5000);
 }
}