package interruptor;

enum Season {
	SUMMER, WINTER, SPRING, FALL
}

public class Main {

	public static void main(String[] args) {

		
		//OK TEM O PONTEIRO NA POSICAO 2 COM TODOS OS ATRIBUTOS QUE POSSAM EXISTIR, 
			//EXEMPLO data de inicio da estacao, temperatura media....
		Season s = Season.SPRING;

		switch (s) {
		case SUMMER:
			System.out.println("SUMMER");
		case FALL:
			System.out.println("FALL");
		case WINTER:
			System.out.println("WINTER");
		case SPRING:
			System.out.println("SPRING");
		}

		System.out.println(s.ordinal());
	}
}

/*
Season s = Season.SPRING;
switch(s){
case SUMMER : System.out.println("SUMMER");
case default : System.out.println("SEASON"); <-- NAO COMPILA CASE+DEFAULT mesma instrucao.
case WINTER : System.out.println("WINTER");
}
*/