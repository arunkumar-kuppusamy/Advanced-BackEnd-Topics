package lambda;

//interfaceName variavel = (undeclaredParams) -> { return; };

/*
 * LAMBDA deve ser criado atravez de interfaces funcionais
 * Interface funcional somente tem um ÚNICO método.
 * 
 */
interface LeadingZeros {
	//Para usar lambdas deve ser uma interface funcional ou seja um metodo unico.
	int remove(String number);
}

public class Lambda_simple1 {

	public static void main(String[] args) {

		/*
		 * Usando LAMBDAS 
		 */
		LeadingZeros somenteInteiro = (number) -> {
			return Integer.parseInt(number);
		};

		/*
		 * Usando o formato antigo com innerclass. 
		 */
		LeadingZeros inteiros = new LeadingZeros() {
			@Override
			public int remove(String number) {
				return Integer.parseInt(number);
			}
		};		
		
		String number = "0123456789";
		System.out.println(somenteInteiro.remove(number));
		System.out.println(inteiros.remove(number));
	}

}
