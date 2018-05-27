package excecoes;

//in file Test.java
class E1 extends Exception {
}

class E2 extends E1 {
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
