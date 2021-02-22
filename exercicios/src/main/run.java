package main;

import java.util.Scanner;

import polygons.rectangle;

public class run {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter rectangle width and height:");
		double width = sc.nextDouble();
		double height = sc.nextDouble();
		rectangle rectangle = new rectangle();
		rectangle.width = width;
		rectangle.height = height;
		rectangle.Area();
		rectangle.Perimeter();
		rectangle.Diagonal();
		sc.close();
	}

}
