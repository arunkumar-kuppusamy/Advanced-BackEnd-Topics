package lacos_repeticao;

public abstract class ForTestLoop {

	abstract String abstrato() throws Error;

	public static void main(String[] args) {

		/*
		 * Qual abaixo produz a mesma saida?
		 * Resposta: Nenhum deles 
		 */
		
		final int INT1 = 0, INT2 = 1;
		for(int i=INT1; i<INT2; i++){
			System.out.println("0º "+ i);
		}

		System.out.println();
		for(int i=INT1; i<INT2; System.out.println("1º "+ ++i));

		System.out.println();
		for(int i=INT1; i++<INT2; System.out.println("2º "+ i));
		
		System.out.println();
		int i=INT1; while(i++<INT2) { System.out.println("3º "+i); }
		
		System.out.println();
		i=INT1; do { System.out.println("4º "+i); }while(i++<INT2);

	}

}
