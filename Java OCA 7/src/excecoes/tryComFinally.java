package excecoes;

class NewException extends Exception {
}

class AnotherException extends Exception {
}

public class tryComFinally {
	public static void main(String[] args) throws Exception {
		
		//NAO EXISTE UM CATCH
			//logo ele vai direto para o try para finalizar a execucao
			//espera que o try trate a excecao mas ele lança outra excecao que é aceita como tratamento para m2.
		
		try {
			m2();
		} finally {
			m3();
		}
	}

	public static void m2() throws NewException {
		throw new NewException();
	}

	public static void m3() throws AnotherException {
		throw new AnotherException();
	}
}