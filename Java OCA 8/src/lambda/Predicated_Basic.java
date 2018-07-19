package lambda;

import java.util.function.Predicate;

/*
 * Predicate<Object> variavel = (z) -> z == valorAComparar;
 * Onde == pode ser qualquer operador que se quer comparar
 * 
 * Predicate is functional interface. @FunctionalInterface
 * Logo tem apenas um método o test que retorna boolean
 * se o valor é igual ao passado a função.
 * 
 */
public class Predicated_Basic {

	public static void main(String[] args) {
	
			int x = 0;
			
		Predicate<Integer> maior = (i) -> i > x;
		Predicate<Integer> maiorTernary = (i) -> i > x ? true : false;//usando ternary muito comum na prova.
		
			System.out.println(maior.test(1));//valor é maior que x
			System.out.println(maiorTernary.test(1)+"\n");

		
			/*
			 * Predicate usa == ou equals?
			 * 
			 * Pode confundir os juniores essa questão, mas
			 * Voce especifica qual operador o Predicate deve usar para comparar
			 */
			String heap = new String("heap de memoria");
			String nova = new String(heap);
		
		Predicate<String> heapEquals = (i) -> i == (heap);
		Predicate<String> heapCase   = (i) -> i.equals(heap);
			
			System.out.println(heap==nova);
			System.out.println(heapEquals.test(nova)+"\n");
			
			System.out.println(heap.equals(nova));
			System.out.println(heapCase.test(nova)+"\n");
		
		
	}

}
