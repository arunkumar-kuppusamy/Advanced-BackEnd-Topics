package excecoes_tryblock;

class NewException extends Exception {
	private static final long serialVersionUID = 1L;
}

class AnotherException extends Exception {
	private static final long serialVersionUID = 1L;
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