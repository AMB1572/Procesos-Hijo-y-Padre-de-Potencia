package dam.psp;

import java.util.Scanner;

public class Potencias {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			System.out.print("Potencias del número: ");
			int num = sc.nextInt();
			
			if(num < 0) {
				System.err.println("Error: has introducido un número negativo");
				System.exit(1);
			}
			
			int resultado = 0;
			
			for(int i = 1; i<=5; i++) {
				resultado = (int) Math.pow(num, i);
				System.out.printf("%d elevado a %d = %d%n", num, i, resultado);
			}
		}
	}

}
