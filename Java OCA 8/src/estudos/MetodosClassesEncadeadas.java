package estudos;

public class MetodosClassesEncadeadas {

	public static void main(String[] args) {
		
		new Primeiro().primeiro().segundo().terceiro();
		
	}
}

class Primeiro {

	public Segundo primeiro() {
		return new Segundo();
	}

}

class Segundo {

	public Terceiro segundo() {
		return new Terceiro();
	}

}

class Terceiro {

	public void terceiro() {
		System.out.println("FIM DA LINHA");
	}

}