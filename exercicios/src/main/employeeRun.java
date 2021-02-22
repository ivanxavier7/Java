package main;

import java.util.Locale;
import java.util.Scanner;

import ex2.Employee;
import ex2.Teste;

public class employeeRun {
	
	public static final double PI = 3.14159;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);;
		Scanner sc = new Scanner(System.in);
		/*
		Employee employee = new Employee();
		System.out.print("Name: ");
		employee.name = sc.nextLine();
		System.out.print("Gross salary: ");
		employee.grossSalary = sc.nextDouble();
		System.out.print("Tax: ");
		employee.tax = sc.nextDouble();
		System.out.printf("Emplyoee: %s, $ %.2f%n", employee, employee.NetSalary());
		System.out.print("Which percentage to increase salary? ");
		double percentage = sc.nextDouble();
		employee.IncreaseSalary(percentage);
		*/
		Teste aluno = new Teste();
		aluno.name = sc.nextLine();
		aluno.addGrade1(sc.nextDouble());
		aluno.addGrade2(sc.nextDouble());
		aluno.addGrade3(sc.nextDouble());
		System.out.printf("FINAL GRADE = %.2f%n", aluno.finalGrade());
		System.out.println(aluno.checkNote());
		sc.close();
	}
	/* Para chamar métodos dentro do main é preciso declarar com estatico, porque ser um metodo estatico tambem
	 * Tambem permite chamar o metodo sem intanciar a objeto*/ 
}
