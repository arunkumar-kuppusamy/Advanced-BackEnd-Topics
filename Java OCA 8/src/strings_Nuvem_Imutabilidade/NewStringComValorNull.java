package strings_Nuvem_Imutabilidade;

public class NewStringComValorNull {

	public static void main(String[] args) {

		/*Exception in thread "main" java.lang.NullPointerException */
		String first = null;
		String second = new String(first);
		
		/*Exception in thread "main" java.lang.Error: Unresolved compilation problem: */
		//String second = new String(null);

	}

}
