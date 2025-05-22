import java.util.ArrayList;
import java.util.Scanner;

public class FalsaPosicion {
	public static void main(String[] args) {
	
		double a = 0.0, b = 0.0, xn = 0.0, xnAnterior = 0.0; //variables del intervalo 
		
		String signo = ""; //variables del término
		int coeficiente = 0;
		int exponente = 0;
		int termIndependiente = 0;
		
		int iteracion = 0; //indicador de iteración
		
		double tolerancia = 0.0; //variable de tolerancia
		
		double error = 1.0; //variable de error
		
		double fA = 0, fB = 0, fXN = 0; //variable de resultados de función

		String termino = ""; //opciones
		
		ArrayList<Termino> terminos = new ArrayList<>(); //variables para manejar objetos de tipo Termino
		Termino TerminoAlgebraico = null;
		
		Scanner leer = new Scanner(System.in); //variable para leer datos desde consola
		
			
		System.out.println("\n/////Método de Falsa Posición/////");
		
		do { //TÉRMINOS ALGEBRAICOS
			
			System.out.println("\n¿Agregar término algebraico? (si/no)"); 
			termino = leer.next().toLowerCase();
			
			if(termino.equals("si")) {
				System.out.print("\nSigno: "); 
				signo = leer.next(); 
				
				System.out.print("\nCoeficiente: "); 
				coeficiente = leer.nextInt();
				
				
				System.out.print("\nExponente: "); 
				exponente = leer.nextInt(); 
				
				TerminoAlgebraico = new Termino(signo, coeficiente, exponente);
				System.out.print("\n " + TerminoAlgebraico.getSigno() + TerminoAlgebraico.getCoeficiente() + "X^" + TerminoAlgebraico.getExponente() + "\n"); //imprimir término
				
				terminos.add(TerminoAlgebraico); 
			}
			
		}while(termino.equals("si"));
		
		//término independiente
		System.out.println("\nTérmino independiente: "); 
		termIndependiente = leer.nextInt(); 
		
		//obtener 'a'
		System.out.println("\na: "); 
		a = leer.nextInt(); 
        
		//obtener 'b'
		System.out.println("\nb: "); 
		b = leer.nextInt(); 
		
		//obtener tolerancia
		System.out.println("\nTolerancia: "); 
		tolerancia = leer.nextDouble();
		
		//calcular valores
		fA = calcularFuncion(terminos, a, termIndependiente); //función de 'a'
		fB = calcularFuncion(terminos, b, termIndependiente); //función de 'b'
		
		//comenzar iteración
		if(fA*fB<0) { //existe raíz
			do {
				fA = calcularFuncion(terminos, a, termIndependiente); //función de 'a'
				fB = calcularFuncion(terminos, b, termIndependiente); //función de 'b'
				
				xnAnterior = xn;
				xn = a-((fA*(b-a))/(fB-fA));
				
				fXN = calcularFuncion(terminos, xn, termIndependiente); //función de 'm'
				
				//imprimir valores
				System.out.print("\n Iteración: " + iteracion);
				System.out.print("\n a: " + a + ", b: " + b + ", xn: " + xn);
				System.out.print("\n F(a): " + fA + ", F(b): " + fB + ", F(xn): " + fXN);
				System.out.print("\n F(a) x F(xn): " + (fA*fXN));
				
				//intercambiar valores según el resultado
				if(fA*fXN<0) 
					b = xn;
				else 
					a = xn;
				
				
				//error
				if(iteracion==0) //primera iteración
					System.out.println("\n Error: N/A"); 
				else { //iteración 1 en adelante
					error = Math.abs(((xnAnterior-xn)/xn)); //obtener error con valor absoluto
					System.out.println("\n Error: " + error);
				}
				
				iteracion++; //incrementar número de iteración
				
			}while(error>tolerancia);
		}else 
			System.out.println("No existe raíz");
		
		
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
}
