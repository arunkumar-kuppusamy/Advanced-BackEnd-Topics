package interruptor;

enum Letra { A, B, C }

public class Letras {

	public static void main(String[] args) {
		
		Letra l = Letra.C;
		System.out.println(l.name());
		System.out.println(l.ordinal());
		System.out.println(l.hashCode());
		System.out.println(l.toString());
		System.out.println("-----");
		
		
		switch (l) {
		case A:
			System.out.println("A");
			break;
		case B:
			System.out.println("B");
			break;

		default:

			System.out.println("default");
			break;
		}
		
	}
	
}
