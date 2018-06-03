package basico;


/* 
 Seguindo o modelo na prova separe por grupos dessa forma
	// teste ? caso_verdade : caso_falso
 	Na prova cai questoes sem parenteses e fica dificil saber qual o valor final. 
 */

public class OperadorTernario {

	public static void main(String[] args) {

		//int i = true ? 0 : 1;
		//int i = false ? (true ? 0 : 1) : 2;
		boolean i = (true ? true : (true ? true : false)) ? (true ? true : false) : (true ? true : false);
		//boolean i = ((true ? true : true) ? true : false) ? (true ? true : false) : (true ? true : false);
		System.out.println(i);
		
		//:Na prova havia algo assim
		int a = 99, b=100, c=101, d=102;
		//d = a>0 ? b==c-- ? a<0 ? b++ : d-- : c : a++;
		//Separe da esquerda para direita
		d = (a>0 ? (b==c-- ? (a<0 ? b++ : d--) : c) : a++); 
		System.out.println(a+" "+b+" "+c+" "+d+" ");

	}

}
