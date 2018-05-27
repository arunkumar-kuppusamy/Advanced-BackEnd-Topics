package basico;

/*
 * char é criado por default como \u0000 ou seja uma string vazia
 * 	porém se usarmos em um contexto inteiro ele vira zero
 * 	na real um char é um inteiro e pode ser usado como
 * 	logo int i[charVar] = se char nao foi inicializada ficara posicao zero. 
 */

public class CharZeroDefaultOuStringVazia {

	static boolean b = true;
	static int[] ia = new int[1];
	static char ch;
	static boolean[] ba = new boolean[1];

	public static void main(String args[]) throws Exception {
		boolean x = false;
		if (b) {
			x = (ch == ia[ch]);//nao entra aqui
			System.out.println("ch: "+ch);
		} else
			x = (ba[ch] = b);
		System.out.println(x + " " + ba[ch]);
	}
}
