package lacos_repeticao;

/*
 * Visualizacao de como e dividido visualmente os elementos em um array.
 * em uma, duas e tres dimensoes.
 */
//ABSTRACT CLASS + MAINVOID METHOD
abstract class VisualizarArrayMultiDimensional {

	public static void main(String[] args) {
		
		// como o for se comporta com relacao ao comprimento de uma matriz
		System.out.println(" --------- 2ND ---------");
		int[][] biDimensional = new int[3][6];
		for (int i = 0; i < biDimensional.length; i++) {
			for (int j = 0; j < biDimensional[i].length; j++) {
				System.out.print("[" + i + j + "]");
			}
			System.out.println();
		}
		System.out.println("Linhas:"+biDimensional.length+" Colunas:"+biDimensional[0].length);

		System.out.println(" --------- 3ND ---------");
		int[][][] triDimensional = new int[2][4][2];
		for (int w = 0; w < triDimensional[0][0].length; w++) {
			System.out.println("FACE_"+w);
			for (int i = 0; i < triDimensional.length; i++) {
				for (int j = 0; j < triDimensional[i].length; j++) {
					System.out.print("[" + i + j + "]");
				}
				System.out.println();
			}
		}
		System.out.println("Linhas:"+triDimensional.length+" Colunas:"+triDimensional[0].length+" Faces: "+triDimensional[0][0].length);
	}
}
