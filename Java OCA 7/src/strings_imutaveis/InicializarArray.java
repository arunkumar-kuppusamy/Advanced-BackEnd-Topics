package strings_imutaveis;

public class InicializarArray {

	public static void main(String[] args) {

		
		//NAO PODEMOS DEFINIR O TAMANHO E INICIALIZAR AO MESMO TEMPO
		
		int[] i1[] = { { 1, 2 }, { 1 }, {}, { 1, 2, 3 } };
		// int i2[ ] = new int[2] {1, 2} ;
		int i3[][] = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		int i4[][] = { { 1, 2 }, new int[2] };
		// int i5[4] = { 1, 2, 3, 4 } ;
		
		//ao inicializar diretamente ao criar estamos apenas substituindo os zeros por novos valores
			//ou seja o codigo abaixo não faz nada, ja que tudo já é zero.

		int [] zeros1 = { 0 };
		int [][] zeros2 = { {0}, {0}, {0,0} , {0,0} , {0,0,0,0,0} };
		int [][][] zeros3 = { 	{ { 0 } }, 	{ {0}, {0}, {0,0,0} } 	 };
		
		
	}

}
