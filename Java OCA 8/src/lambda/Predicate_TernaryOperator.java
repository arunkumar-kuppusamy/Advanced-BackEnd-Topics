package lambda;

import java.util.function.Predicate;

class Season {
	String name;
}

public class Predicate_TernaryOperator {

	public static void main(String arguments[]) {

		//Popula 2 objetos
		Season season1 = new Season();
		season1.name = "Spring";
		Season season2 = new Season();
		season2.name = "Autumn";
		
		//Ternary operator
		Predicate<String> aSeason = (s) -> s == "Summer" ? true : false;//OK WORKS
		//Predicate<String> aSeason = (s) -> s == "Summer" ? season1.name : season2.name;//ERROR
		season1 = season2;
		System.out.println(season1.name);
		System.out.println(season2.name);
		
		
		//ERRO aqui porque estou passando esperando uma String mas teste apenas retorna BOOLEAN.
		System.out.println(aSeason.test(new String("Summer")));
	}
}


/*
Predicate<String> aSeason = (s) -> s == "Summer" ? season1.name : season2.name;

Com base nesse lambda acredito que a classe faça algo desse tipo
interface Predicate<String>{
	boolean test(String s) {
		return s.equals("Summer");
	}
}*/



