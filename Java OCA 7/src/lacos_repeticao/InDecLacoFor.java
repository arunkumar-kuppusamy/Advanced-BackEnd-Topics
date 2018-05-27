package lacos_repeticao;

/*
 * Incremento e Decremento dentro do laço FOR
 * Não importa neste caso, vale fazer a tabela e 
 * 	ver que ele nem entra dentro do for.
*/

public class InDecLacoFor {

	public static void main(String args[]) {
		int i = 99;
		int j = 88;
		for (i = 0, j = 0; j < i; ++j, i++) {
			System.out.println(i + " " + j);
		}
		System.out.println(i + " " + j);
	}

}