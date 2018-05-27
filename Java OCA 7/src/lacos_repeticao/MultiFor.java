package lacos_repeticao;

/*
 * Codigo basico com aninhamento de lacos for
 * 	Saber olhar com calma e fazer uma tabela dos valores sem se perder.
 * 	Sysout eu coloquei para visualizar e como deve ser feita a tabela da verdade. 
 * 
 */

public class MultiFor {

	public static void main(String args[]) {
		int c = 0;
		A: for (int i = 0; i < 2; i++) {
			B: for (int j = 0; j < 2; j++) {
				C: for (int k = 0; k < 3; k++) {
					c++;
					System.out.println(i+""+j+""+k+" c:"+c);
					if (k > j) {
						System.out.println("--------break");
						break;
					}
				}
			}
		}
		System.out.println(c);//prints 10
	}

}

/*
  Output
	000 c:1
	001 c:2
	------break
	010 c:3
	011 c:4
	012 c:5
	------break
	100 c:6
	101 c:7
	------break
	110 c:8
	111 c:9
	112 c:10
	------break
	10 
*/
