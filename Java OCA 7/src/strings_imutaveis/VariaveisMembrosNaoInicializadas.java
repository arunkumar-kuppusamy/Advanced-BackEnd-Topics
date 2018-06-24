package strings_imutaveis;

public class VariaveisMembrosNaoInicializadas {

	String str;//static ou nao tanto faz, variaveis membros sao inicializadas com seu valor default.
	public static void main(String[] args) {
		
		VariaveisMembrosNaoInicializadas t = new VariaveisMembrosNaoInicializadas();
		t.print();
	}

	void print() {
		System.out.println(str);//null
	};
}
/*
Correct Option is : C
A. It will not compile.
B. It will compile but throw an exception at runtime.
C. It will print 'null' (without quotes).
D. It will print nothing.
E. None of the above.
 */