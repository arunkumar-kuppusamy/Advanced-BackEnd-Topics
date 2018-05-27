package abstrato;



class Segunda extends Primeira{

	@Override
	void mostrar() {
		System.out.println("SEGUNDA REIMPLEMENT ABSTRACT METHOD");
	}
	
}

abstract class Primeira{
	
	abstract void mostrar();
}


public class Main {

	public static void main(String[] args) {
		Primeira p = new Segunda();
		p.mostrar();
	}
	
}
