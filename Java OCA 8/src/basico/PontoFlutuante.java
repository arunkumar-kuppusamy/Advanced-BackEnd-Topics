package basico;

public class PontoFlutuante {

	public static void main(String[] args) {

		// Since one of the operands (9.9) is a double, it wil perform a real division
		// and will print 10.1010101010101
		System.out.println(100 / 9.9);// 10.1010101010101
		System.out.println(99.0 / 7.0);// 14.142857142857142

		// Se tem ponto é DOUBLE logo o resultado tera sempre um PONTO FLUTUANTE
		System.out.println(100 / 10.0);// 10.0
		System.out.println(99 / 8.0);// 12.375

		// nao tem ponto nao tem resultado com ponto flutuante
		System.out.println(100 / 10);// 10
		System.out.println(99 / 7);// 14

		// logica maluca, mas não tem nenhum ponto flutuante logo o resultado sempre
		// será um INTEIRO
		System.out.println(3 + 100 / 10 * 2 - 13);// 10

		// CAST de Object para Integer, só pode resultar em um inteiro sem ponto flutuante
		Object o = new Integer(106);
		// Integer k = (Integer) t/10;//obrigatório o DOWNCAST
		int k = ((Integer) o).intValue() / 10;// obrigatório o DOWNCAST
		System.out.println(k);// 10

		//Number superclass of classes BigDecimal, BigInteger, Byte, Double, Float, Integer, Long, and Short.
		Number n = new Integer(106);
		System.out.println(n);// 106
		int i = (Integer) n;
		System.out.println(i);// 106

		//Boolean ja e uma superclasse e nao depende de Number
		Object b = new Boolean(true);
		
		
		
	}
}
