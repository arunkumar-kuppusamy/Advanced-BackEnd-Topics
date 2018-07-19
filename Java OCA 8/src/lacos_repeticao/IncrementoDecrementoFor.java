package lacos_repeticao;

/*
 * Incremento e Decremento
 * 	Dentro de um laco for Não faz diferenca nenhuma
 * 	A não ser que seja feita uma operacao numerica dentro do terceiro parametro como mostrado abaixo.
 * 		O acesso pode ser interno ou externo, os dois terao seus valores diferentes.
 * 	
 * //Decremento: 0 0 1 2 3 4 5 6 7 8
 * //Incremento: 0 1 2 3 4 5 6 7 8 9
 * 
 */
public class IncrementoDecrementoFor {

	public static void main(String[] args) {

		int decremento = 0;
		for (int i = 0; i < 10; decremento = ++i) {
			System.out.print(decremento + " ");// 0 1 2 3 4 5 6 7 8 9
		}
		System.out.println("\ndecremento:\t" + decremento);// decremento: 10

		int incremento = 0;
		for (int i = 0; i < 10; incremento = i++) {
			System.out.print(incremento + " ");// 0 0 1 2 3 4 5 6 7 8
		}
		System.out.println("\nincremento:\t" + incremento);// incremento: 9

	}

}
