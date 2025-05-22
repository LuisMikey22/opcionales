import java.util.ArrayList;
import java.util.Scanner;

public class Biseccion {
	
	public static void main(String[] args) {
		//variables del intervalo y mitad
		double a = 0.0, b = 0.0, m = 0.0, mAnterior = 0.0;
		
		//variables del término
		String signo = "";
		int coeficiente = 0;
		int exponente = 0;
		int termIndependiente = 0;
		
		//indicador de iteración
		int iteracion = 0;
		
		//variable de tolerancia
		double tolerancia = 0.0;
		
		//variable de error
		double error = 1.0;
		
		//variable de resultados de función
		double fA = 0, fB = 0, fM = 0;

		//opciones
		String termino = "";
		String continuar = "";
		
		//variables para manejar objetos de tipo Termino
		ArrayList<Termino> terminos = new ArrayList<>();
		Termino TerminoAlgebraico = null;
		
		//variable para controlar casos de valores incorrectos
		String validarString = "";
		
		//variable para leer datos desde consola
		Scanner leer = new Scanner(System.in);
		
		do { //mientras el usuario calcule problemas
			
			System.out.println("\n/////Método de Bisección o Bolzano/////");
			
			do {
				//TÉRMINOS
				do { 
					System.out.println("\n¿Agregar término algebraico? (si/no)"); 
					termino = leer.next().toLowerCase();
				}while(!termino.equals("si") && !termino.equals("no")); //si se introduce texto diferente a 'si' y 'no'
				
				if(termino.equals("si")) {
					do { 
						System.out.print("\nSigno: "); 
						signo = leer.next(); 
					}while(!signo.equals("+") && !signo.equals("-")); //si se introduce un carácter diferente a '+' o '-'
					
					do {
						System.out.print("\nCoeficiente: "); 
						validarString = leer.next(); 
					}while(!numero(validarString) || validarString.equals("0")); //si se introduce un valor diferente a un número o igual a '0'
					coeficiente = Integer.parseInt(validarString); //asignar valor una vez que salga de do while
					
					do {
						System.out.print("\nExponente: "); 
						validarString = leer.next(); 
					}while(!numero(validarString) || validarString.equals("0")); //si se introduce un valor diferente a un número o igual a '0'
					exponente = Integer.parseInt(validarString); //asignar valor una vez que salga de do while
					
					TerminoAlgebraico = new Termino(signo, coeficiente, exponente);
					System.out.print("\n " + TerminoAlgebraico.getSigno() + TerminoAlgebraico.getCoeficiente() + "X^" + TerminoAlgebraico.getExponente() + "\n"); //imprimir término
					
					terminos.add(TerminoAlgebraico); 
				}
				
			}while(termino.equals("si"));
			
			//término independiente
			do { 
				System.out.println("\n¿Agregar término independiente? (si/no)"); 
				termino = leer.next().toLowerCase();
			}while(!termino.equals("si") && !termino.equals("no")); //si se introduce texto diferente a 'si' y 'no'
			
			if(termino.equals("si")) {
				do {
					System.out.print("\nTermino independiente: ");
					validarString = leer.next(); 
				}while(!numero(validarString) || validarString.equals("0")); //si se introduce un valor diferente a un número o igual a '0'
				termIndependiente = Integer.parseInt(validarString); //asignar valor una vez que salga de do while
			}
			
			//obtener 'a'
			do {
				System.out.print("\nValor de 'a': "); 
				validarString = leer.next(); 
			}while(!numero(validarString) || validarString.equals("0")); //si se introduce un valor diferente a un número o igual a '0'
			a = Integer.parseInt(validarString); //asignar valor una vez que salga de do while
	        
			//obtener 'b'
			do {
				System.out.print("\nValor de 'b': "); 
				validarString = leer.next(); 
			}while(!numero(validarString) || validarString.equals("0")); //si se introduce un valor diferente a un número o igual a '0'
			b = Integer.parseInt(validarString); //asignar valor una vez que salga de do while
			
			//obtener tolerancia
			do {
				System.out.print("\nTolerancia: "); 
				validarString = leer.next(); 
			}while(!numeroDcml(validarString)); //si se introduce un valor diferente a un número o igual a '0'
			tolerancia = Double.parseDouble(validarString); //asignar valor una vez que salga de do while
			
			System.out.println(tolerancia);
			
			//calcular valores
			fA = calcularFuncion(terminos, a, termIndependiente); //función de 'a'
			fB = calcularFuncion(terminos, b, termIndependiente); //función de 'b'
			
			//comenzar iteración
			if(fA*fB<0) { //existe raíz
				do {
					fA = calcularFuncion(terminos, a, termIndependiente); //función de 'a'
					fB = calcularFuncion(terminos, b, termIndependiente); //función de 'b'
					
					mAnterior = m;
					m = (a+b)/2;
					
					fM = calcularFuncion(terminos, m, termIndependiente); //función de 'm'
					
					//imprimir valores
					System.out.print("\n Iteración: " + iteracion);
					System.out.print("\n a: " + a + ", b: " + b + ", m: " + m);
					System.out.print("\n F(a): " + fA + ", F(b): " + fB + ", F(m): " + fM);
					System.out.print("\n F(a) x F(m): " + (fA*fM));
					
					//intercambiar valores según el resultado
					if(fA*fM<0) {
						b = m;
					}else {
						a = m;
					}
					
					//error
					if(iteracion==0) { //primera iteración
						System.out.println("\n Error: N/A"); 
					}else { //iteración 1 en adelante
						error = Math.abs(((mAnterior-m)/m)); //obtener error con valor absoluto
						System.out.println("\n Error: " + error);
					}
					
					iteracion++; //incrementar número de iteración
				}while(error>tolerancia);
			}else {
				System.out.println("No existe raíz");
			}
			
			//continuar programa
			do { 
				System.out.println("\n¿Desea calcular otra raíz? (si/no)"); 
				continuar = leer.next().toLowerCase();
			}while(!continuar.equals("si") && !continuar.equals("no")); //si se introduce texto diferente a 'si' y 'no'
			
		}while(continuar.equals("si"));  
		
		leer.close();
		
	}
	
	//métodos
	public static boolean numero(String text) {
        for(char character:text.toCharArray()) {
            if(!Character.isDigit(character) && character!='-' && character!='+') { //si es diferente a números
                return false;
            }
        }
        
        return true;
    }
	
	public static boolean numeroDcml(String text) {
        for(char character:text.toCharArray()) {
            if(!Character.isDigit(character) && character!='.') { //si es diferente a números
                return false;
            }
        }
        
        return true;
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
