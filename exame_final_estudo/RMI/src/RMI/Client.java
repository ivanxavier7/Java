package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import RMI.RMIInterface;

public class Client {

	private static MyRemote look_up;

	public static void main(String[] args) 
		throws MalformedURLException, RemoteException, NotBoundException {
		
		look_up = (MyRemote) Naming.lookup("//localhost/MyServer");
		Scanner txt = new Scanner(System.in);
		System.out.println("What is your name?\"");
		
		System.out.println("Hello " + txt + "!");

	}

}