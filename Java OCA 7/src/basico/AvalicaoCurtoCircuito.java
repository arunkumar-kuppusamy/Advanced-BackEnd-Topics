package basico;

/*
 * Single Ampercent (&)
 * Short-Circuit Evaluation ou Eager Evauation (Ansioso)
 * &  - Compara ambos, ambos devem ser true
 * |  - Compara ambos, apenas um deles deve ser true.
 * && - Encontrando um FALSE, sai da verificacao, ambos devem ser true
 * || - Encontrando TRUE sai da verificacao, se o primeiro for false verifica o seguinte
 */
public class AvalicaoCurtoCircuito {

	static boolean a;
	static boolean b;
	static boolean c;

	public static void main(String[] args) {
		
		boolean bool = (a = true) || (b = true) && (c = true);
		//System.out.print(a + ", " + b + ", " + c);//true, false, false

		
		//Somente teremos diferenca entre short-circuit ou eager evaluation
		//Se tivermos uma operacao durante a comparacao
		//Operacao que pode ser de atribuicao, soma, decremento..........
		int E=1;
		if( false && (++E > 0) ) { System.out.println(E+"x"); }
		System.out.println(E+"\n\n");//&& print 1, & print 2.
		
		
		//| e || somente fara alguma diferenca em caso de obter um true ja na primeira instrucao
		// true | xxx compara xxx
		// true || xxx NAO compara xxxx, se tornando uma parte inacessivel do código
		int OU =1;
		if( true | (++OU > 0) ) { System.out.println(OU+"x"); }
		System.out.println(OU+"\n\n");//|| print 1, | print 2.


	}

}