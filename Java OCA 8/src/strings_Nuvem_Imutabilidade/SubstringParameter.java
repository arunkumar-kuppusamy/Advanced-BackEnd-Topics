package strings_Nuvem_Imutabilidade;

public class SubstringParameter {

	public static void main(String[] args) {

		/*
		 * Substring esta regra foi tirada da especificacao oficial do java
		 * BEGINindex - INCLUSIVE
		 * ENDindex  - EXCLUSIVE
		 * A regra para o segundo parametro e subtrair o indice inicial do final
		 * 	substring( BEGINindex, (ENDindex - BEGINindex) )
		 * 		Com essa regra temos por consequencia o numero de casas a mostrar o comprimento, ou a posicao final a mostrar.
		 */
			
		//M I N I M U M
		//0 1 2 3 4 5 6
		String s = "MINIMUM";
		System.out.println(s.length());
		System.out.println(s.substring(4, 7)); // 1
		System.out.println(s.substring(4)); // 2
				
		//Index Of retorna uma substring apartir de um caracter
		//retorna erro StringIndexOut se nao encontrar ou exceder o limite
		//ZeroBased inclusivo logo nao precisa somar um a posicao inicial
		System.out.println(s.substring(s.indexOf('M', 6))); //3
		System.out.println(s.substring(s.indexOf('U'))); //3

		//CompareTo verifica lexicograficamente e case-sensitive
		//return zero se sao iguais
		System.out.println(s.compareTo("MINIMUM"));
		System.out.println("João".compareTo("Joao"));//nao e igual

		
		/*
		 * Erro muito comum na prova de OCA.
		 * Usar um valor negativo no endIndex de Substring.
		 * comprimento é uma medida positiva e nunca pode ser negativa, ou seja ou é zero ou não existe.
		 * 
		 * Muito comum colocar indexOf que retorna -1 como parametro de substring.
		 * DICA: ao ver indexOf verifique se existe na string verificada, senão ele pode nao ser aplicado como parametro.
		 * 
		 * indexOf aceita char ou string.
		 */
		System.out.println( s );
		System.out.println( s.substring(s.indexOf('I'), s.indexOf("")) );
	}

}
