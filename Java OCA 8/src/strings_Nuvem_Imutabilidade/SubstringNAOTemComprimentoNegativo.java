package strings_Nuvem_Imutabilidade;


/*
 * Erro muito comum na prova de OCA.
 * Usar um valor negativo no endIndex de Substring.
 * comprimento é uma medida positiva e nunca pode ser negativa, ou seja ou é zero ou não existe.
 * 
 * Muito comum colocar indexOf que retorna -1 como parametro de substring.
 * DICA: ao ver indexOf verifique se existe na string verificada, senão ele pode nao ser aplicado como parametro.
 * 
 */
public class SubstringNAOTemComprimentoNegativo {

	public static void main(String[] args) {

		
		String s = "123456789";

		System.out.println( s.indexOf('2') );
		System.out.println( s.indexOf("0") );

		//ERROR : StringIndexOutOfBoundsException: String index out of range: -1
		System.out.println( s.substring(s.indexOf('2'), s.indexOf("1")).concat("0") );

	}

}
