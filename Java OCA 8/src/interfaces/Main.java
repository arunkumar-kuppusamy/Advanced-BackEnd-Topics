package interfaces;

/*
 * A missao de implementar os metodos é da primeira classe NAO ABSTRATA
 * 	Ou seja a missao de colocar abstract é nao obrigar a utilizar os metodos de uma interface
 * Quando usamos extends temos o implements das outras classes com isso
 * 	podemos usar diversos implements em arvores assim como diversos extends
 * VARIOS IMPLEMENTS, SOMENTE UM EXTENDS
 * 
*/

interface Primeira { }

interface Segunda { }

class Terceira implements ITeste, Primeira, Segunda {
	@Override
	public void metodoPublico() {	}
	@Override
	public void metodoSemModificador() {	}
	@Override
	public void metodoAbstrato() {	}
}

public class Main {

	public static void main(String[] args) throws Throwable {

		// DEFAULT ACESSA DIRETO PELA CLASSE FILHA
		// PODEMOS SOBREESCREVER NO METODO REIMPLEMENTANDO
		Terceira t = new Terceira();
		t.mostrar();

		// METODO STATIC ACESSA DIRETO PELA INTERFACE

		ITeste.staticMethod();
	}

}
