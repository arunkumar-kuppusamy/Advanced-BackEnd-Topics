package basico;

/*
 * METODO MAIN SOMENTE PODE SER PUBLICO
 * 	NAO PODE HAVER RESTRICAO NO ACESSO A JVM
 * 	OU SEJA NAO PODE SER PRIVATE, DEFAULT, PROTECTED, ABSTRACT
 * 	Nao pode usar modificadores unicos para variaveis como TRANSIENT e VOLATILE
 * 	Nem modificador apenas para metodos abstratos como NATIVE
 * 
 * METODO MAIN pode usar modificadores de "alteracao"
 * 	STRICTFP(IEEE-754), SYNCHRONIZED e FINAL
 * 
 */
final public class MainModificadoresAcesso {

	private int i = 0;
	
	strictfp public static void main(String[] args) {
		System.out.println("strictfp");
		
		main(new String());
		
		main(new String(),new String());

	}
	synchronized public static void main(String args) {
		System.out.println("synchronized");

	}
	final public static void main(String s, String ...args) {
		System.out.println("final");

	}

}
