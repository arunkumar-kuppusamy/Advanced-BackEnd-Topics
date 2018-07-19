package lambda;

//interfaceName variavel = (undeclaredParams) -> { return; };
interface Zeros {
	default Integer somenteInteiro(String number) {
		return Integer.parseInt(number);
	}
}

public class Lambda_simple2 {

	public static void main(String[] args) {

		//Zeros z = (x) -> { return x};
		
		String number = "0123456789";
		//System.out.println(z.somenteInteiro(number));

	}

}
