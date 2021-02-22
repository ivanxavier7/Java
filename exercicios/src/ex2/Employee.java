package ex2;

import java.util.Scanner;

public class Employee {
	public String name;
	public double grossSalary;
	public double tax;
	
	public double NetSalary() {
		return grossSalary - tax;
	}
	
	public String toString() {
		return name;
	}
	
	public void IncreaseSalary(double percentage) {
		System.out.printf("Updated data: %s, $ %.2f", name, grossSalary * (1 + percentage/100) - tax);
	}
}

