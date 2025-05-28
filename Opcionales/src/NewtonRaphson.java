import java.util.ArrayList;
import java.util.Scanner;

public class NewtonRaphson {
	public static void main(String[] args) {
	
		double xn = 0.0, xnAnterior = 0.0, xnDerivada = 0.0; //variables del intervalo 
		
		String signo = ""; //variables del término
		int coeficiente = 0;
		int exponente = 0;
		int termIndependiente = 0;
		
		int iteracion = 0; //indicador de iteración
		
		double tolerancia = 0.0; //variable de tolerancia
		
		double error = 1.0; //variable de error
		
		double fXN = 0; //variable de resultados de función

		String termino = ""; //opciones
		
		ArrayList<Termino> terminos = new ArrayList<>(); //variables para manejar objetos de tipo Termino
		Termino TerminoAlgebraico = null;
		
		Scanner leer = new Scanner(System.in); //variable para leer datos desde consola
		
			
		System.out.println("\n/////Método de Newton-Raphson/////");
		
		do { //TÉRMINOS ALGEBRAICOS
			
			System.out.println("\n¿Agregar término algebraico? (si/no)"); 
			termino = leer.next().toLowerCase();
			
			if(termino.equals("si")) {
				System.out.print("\nSigno: "); 
				signo = leer.next(); 
				
				System.out.print("Coeficiente: "); 
				coeficiente = leer.nextInt();
				
				
				System.out.print("Exponente: "); 
				exponente = leer.nextInt(); 
				
				TerminoAlgebraico = new Termino(signo, coeficiente, exponente);
				System.out.print("\n " + TerminoAlgebraico.getSigno() + TerminoAlgebraico.getCoeficiente() + "X^" + TerminoAlgebraico.getExponente() + "\n"); //imprimir término
				
				terminos.add(TerminoAlgebraico); 
			}
			
		}while(termino.equals("si"));
		
		//término independiente
		System.out.print("\nTérmino independiente: "); 
		termIndependiente = leer.nextInt(); 
		
		//xn
		System.out.print("\nxn: "); 
		xn = leer.nextInt();
		
		//calcular valores
		xnDerivada = calcularDerivadaSimple(terminos, xn, termIndependiente); //derivada de 'xn'
		
		double xnNueva = 0.0;
		
		//comenzar iteración
		if(xnDerivada!=0) { //existe raíz
			do {
			
				xnDerivada = calcularDerivadaSimple(terminos, xn, termIndependiente); //derivada de 'xn'
				fXN = calcularFuncion(terminos, xn, termIndependiente); //función de 'xn'
				xnNueva = xn - (fXN/xnDerivada);
				
				//imprimir valores
				System.out.print("\n Iteración: " + iteracion);
				System.out.print("\n xn: " + xn);
				System.out.print("\n F(xn): " + fXN);
				System.out.print("\n F'(xn): " + xnDerivada);
				
				/*if (Math.abs(fXN) < tolerancia) {
			        System.out.println("Convergencia alcanzada");
			        break;
			    }*/
				
				//error
				if(iteracion==0) //primera iteración
					System.out.println("\n Error: N/A"); 
				else { //iteración 1 en adelante
					error = Math.abs(((xn-xnNueva)/xnNueva)); //obtener error con valor absoluto
					System.out.println("\n Error: " + error);
				}
				
				xn = xnNueva;
				iteracion++; //incrementar número de iteración
				
			}while(error>tolerancia);
		}else 
			System.out.println("\nNo existe raíz");
		
		
		leer.close();
		
	}
	
	public static double calcularFuncion(ArrayList<Termino> terminos, double constante, int termIndependiente) {
		double valor = 0.0;
		for(Termino termino:terminos) {
			if(termino.getSigno().equals("+")) {
				valor += termino.getCoeficiente() * (Math.pow(constante, termino.getExponente()));
			}else {
				valor += -termino.getCoeficiente() * (Math.pow(constante, termino.getExponente()));
			}
		}
		
		valor += termIndependiente; //sumar o restar el término independiente
		
		return valor;
	}
	
	public static double calcularDerivadaSimple(ArrayList<Termino> terminos, double constante, int termIndependiente) {
		double valor = 0.0;
		for(Termino termino:terminos) {
			if(termino.getSigno().equals("+")) {
				valor +=  termino.getExponente()*termino.getCoeficiente() * (Math.pow(constante, termino.getExponente()-1));
			}else {
				valor += -termino.getExponente()*termino.getCoeficiente() * (Math.pow(constante, termino.getExponente()-1));
			}
		}
		
		return valor;
	}
}