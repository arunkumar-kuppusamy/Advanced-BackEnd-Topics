package excecoes;

//in file Test.java
class E1 extends Exception {
	private static final long serialVersionUID = 1L;
}

class E2 extends E1 {
	private static final long serialVersionUID = -4041974098331249925L;
}

public class ExcecaoExtendida {

	public static void main(String[] args) {

		try {
			throw new E2();
		} catch (E1 e) {
			System.out.println("E1");
		} catch (Exception e) {
			System.out.println("E");
		} finally {
			System.out.println("Finally");
		}

	}

}
