package polygons;

public class rectangle {
	public double width;
	public double height;
	
	public void Area() {
		System.out.printf("AREA = %.2f%n", width * height);
	}
	
	public void Perimeter() {
		System.out.printf("PERIMETER = %.2f%n", 2 * width + 2 * height);
	}
	
	public void Diagonal() {
		System.out.printf("DIAGONAL = %.2f%n", Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2)));
	}
}
