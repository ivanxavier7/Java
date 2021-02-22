package external;

import java.util.Locale;
import java.util.Scanner;

import internal.Produto;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Data");
		System.out.println("Name: ");
		String name = sc.nextLine();
		System.out.println("Price: ");
		double price = sc.nextDouble();
		System.out.println("Quantity: ");
		int quantity = sc.nextInt();
		Produto product = new Produto(name, price);
		product.setQuantity(quantity);
		product.setPrice(price);
		product.setName(name);
		System.out.println(product.toString());
		/* Java chama o toString() automaticamente */
		System.out.println("Product data: " + product);
		System.out.printf("Enter the number of products to be added in stock: %n");
		quantity = sc.nextInt();
		product.AddProducts(quantity);
		System.out.println("Updated data: " +  product);
		
		System.out.printf("Enter the number of products to be removed from stock: %n");
		quantity = sc.nextInt();
		product.RemoveProducts(quantity);
		System.out.println("Updated data: " +  product);
		
		System.out.println(product.getName());
		System.out.println(product.getPrice());
		System.out.println(product.getQuantity());
		sc.close();
		
		/*
		System.out.println(product.getClass());
		System.out.println(product.equals(product));
		System.out.println(product.hashCode());
		*/
	}

}
