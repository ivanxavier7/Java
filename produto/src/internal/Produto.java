package internal;

public class Produto {
	private String name;
	private Double price;
	private int quantity;
	
	/* Construtor */
	public Produto(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	/* Sobrecarga, consoante os parametros recebidos faz coisas diferentes */
	public Produto(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantity = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double TotalValueInStock() {
		return price * quantity;
		}
	
	public void AddProducts(int quantity) {
		this.quantity += quantity;
	}
	
	public void RemoveProducts(int quantity) {
		this.quantity -= quantity;
	}
	/* Change the output "internal.Produto@6e8cf4c6" to the normal name*/
	public String toString() {
		return name
			+ ", $"
			+ String.format("%.2f", price)
			+ ", "
			+ quantity
			+ " units, Total: $ "
			+ String.format("%.2f", TotalValueInStock());
	}
}
