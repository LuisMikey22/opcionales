public class Termino{
			
	private String signo;
	private int coeficiente;
	private int exponente;
	
	//constructor
	public Termino(String signo, int coeficiente, int exponente) {
		this.signo = signo;
		this.coeficiente = coeficiente;
		this.exponente = exponente;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public int getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(int coeficiente) {
		this.coeficiente = coeficiente;
	}

	public int getExponente() {
		return exponente;
	}

	public void setExponente(int exponente) {
		this.exponente = exponente;
	}

}