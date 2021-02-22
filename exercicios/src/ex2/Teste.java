package ex2;

import java.util.Scanner;

public class Teste {
	public String name;
	double grade1;
	double grade2;
	double grade3;
	
	public double finalGrade() {
		return grade1 + grade2 + grade3;
	}
	
	public void addGrade1(double grade) {
		if (grade >= 0 && grade <= 30) {
			this.grade1 = grade;
		}
		else {
			System.out.println("Invalid value!");
		}
	}
	
	public void addGrade2(double grade) {
		if (grade >= 0 && grade <= 35) {
			this.grade2 = grade;
		}
		else {
			System.out.println("Invalid value!");
		}
	}
	
	public void addGrade3(double grade) {
		if (grade >= 0 && grade <= 35) {
			this.grade3 = grade;
		}
		else {
			System.out.println("Invalid value!");
		}
	}
	
	public String checkNote() {
		if (this.finalGrade() >= 60) {
			return "PASS";
		}
		else {
			return "FAILED" + String.format("%nMISSING %.2f POINTS%n", 60 - this.grade1 - this.grade2 - this.grade3);
		}
	}
}
