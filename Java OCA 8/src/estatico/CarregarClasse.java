package estatico;

class Super {
	static {
		System.out.print("super ");
	}
}

class One extends Super {
	static {
		System.out.print("one ");
	}
}

class Two extends Super {
	static {
		System.out.print("two ");
	}
}

public class CarregarClasse {

	public static void main(String[] args) {

		Super o;
		Two t;
		One n;
	}

}
