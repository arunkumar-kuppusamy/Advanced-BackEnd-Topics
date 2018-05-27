package interfaces;

public interface ITeste {

	int i = 0;
	
	//TODOS SAO PUBLIC+STATIC+FINAL
	public static final int x = 99;
	
	public void metodoPublico();
	
	void metodoSemModificador();
	
	//final void metodoFinal();
	
	//TODOS SAO PUBLICS + ABSTRACT
	abstract public void metodoAbstrato();	
	
	//METODOS COM CORPO PODEM SER
	//STATIC ou DEFAULT, nunca os dois juntos.
	static public void staticMethod() {
		System.out.println("STATIC METHOD INTERFACE");
	}
	
	default public void mostrar() {
		System.out.println("DEFAULT METHOD mostrar");
	}
	
}
