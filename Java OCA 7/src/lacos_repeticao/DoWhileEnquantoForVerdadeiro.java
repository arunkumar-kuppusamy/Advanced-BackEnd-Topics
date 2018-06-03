package lacos_repeticao;

/*
 * WHILE com ou sem DO, funciona sempre da mesma maneira
 * 	Enquanto for verdadeiro executa, quando der false, saia imediatamente.
 * 	Por isso em caso de == na todas? as vezes ele vai executar uma unica vez, porque ou sera verdadeiro é igual ou falso não é igual
 * 		em caso de <= ou >=, ou quaquer igual qualquer coisa, tem que se a avaliar se esse instrucao adicional sera ou nao avaliada. 
 * 
 * 
 */
public class DoWhileEnquantoForVerdadeiro {

	public static void main(String[] args) {

	//Mais importante abaixo e fazer o Teste de Mesa Passo A Passo.
		
		System.out.println(" i > j\t| break | j-- | ++i");
		int i = 0, j = 11;
		do {
			System.out.print(" "+i+" > "+j);
			if (i >= j) {
				System.out.print("\t break Nunca vai entrar aqui");
				break;
			}else {
				System.out.print("\t   x");	
			}
			j--;
			System.out.print("\t  "+j+" ");
			System.out.println("\t  "+(i+1)+" ");
		} while (++i < 5);
		System.out.println("["+i + " > " + j+"]");
		System.out.println("==========");
	
		
		
	//Questão na prova OCA, pergunta qual codigo abaixo imprime 12
		int num = 10;
		do {
			num++;
		} while (num < 12);
		System.out.println(num);

		num = 10;
		while(num < 12) {
			num++;
		}
		System.out.println(num);
		
		
	//Agora pegadinhas com incrementos dentro da comparacao
		num = 10;
		do {
		} while (++num < 12);//OK 12
		System.out.println(num);
		
		num = 10;
		do {
			
		} while (num++ < 12);//compara 12<12 e seta como 13 e compara novamente 13<12
		System.out.println(num);//ERROR vai imprimir 13
		
	//Exatamente o mesmo código podemos colocar em while
		num = 10;
		while (++num < 12);//OK 12
		System.out.println(num);
		
		num = 10;
		while (num++ < 12);//OK 12
		System.out.println(num);//ERROR vai imprimir 13		
		
	}

}
