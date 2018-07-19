package lambda;

/*
 * LAMBDA deve ser criado atravez de interfaces funcionais
 * Interface funcional somente tem um ÚNICO método.
 * 
 */
interface classLambdaI {
	String mostrar(int keys, String region);
	//void semRetorno();
}

public class lambda_simple {

	public static void main(String... args) {

		
		//interfaceName variavel = (undeclaredParams) -> { return; };
		
		classLambdaI acessoLambda = (inteiros, caracteres) -> {
			//Coloque qualquer lógica aqui
			return inteiros + caracteres;
		};

		System.out.println(acessoLambda.mostrar(32, "AB"));
	}

}