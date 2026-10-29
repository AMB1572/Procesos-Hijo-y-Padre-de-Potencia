package dam.psp;

import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class LanzadorPotencias {

	private static int PedirNumPotencia(Scanner lector, int numProceso) {
		System.out.printf("Número para el proceso %d: ", numProceso);
		return lector.nextInt();
	}
	
	public static void main(String[] args) {
		ProcessBuilder pb1, pb2;
		
		pb1 = new ProcessBuilder("java", "-cp", "bin", "dam.psp.Potencias");
		pb2 = new ProcessBuilder("java", "-cp", "bin", "dam.psp.Potencias");
		
		pb1.redirectOutput(new File("salida1.txt"));
		pb1.redirectError(new File("errores1.txt"));
		pb2.redirectOutput(new File("salida2.txt"));
		pb2.redirectError(new File("errores2.txt"));
		
		try {
			Process p1 = pb1.start();
			Process p2 = pb2.start();
			
			try(Scanner sc = new Scanner(System.in);
					BufferedWriter escritor1 = new BufferedWriter(new OutputStreamWriter(p1.getOutputStream()));
					BufferedWriter escritor2 = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream()));
			){
				int num1 = PedirNumPotencia(sc, 1);
				escritor1.write(String.valueOf(num1));
				escritor1.newLine();
				escritor1.flush();
				
				int num2 = PedirNumPotencia(sc, 2);
				escritor2.write(String.valueOf(num2));
				escritor2.newLine();
				escritor2.flush();
				
				int codigoSalida1 = p1.waitFor();
				int codigoSalida2 = p2.waitFor();
				
				System.out.printf("Los procesos han terminado con: %d y %d", codigoSalida1, codigoSalida2);
				
			}
		} catch (IOException | InterruptedException e) {
			System.err.println("Error de E/S o interrupción del proceso: " + e.getLocalizedMessage());
		}		
		
	}

}
