package interfaces;

public class VisibilidadeNaoPodeSerMenor {

	public static void main(String[] args) {

		/*Option (f) is incorrect. Although this line of code will compile successfully, it will
		throw a ClassCastException at runtime. You can explicitly cast any object to an inter-
		face, even if it doesn’t implement it to make the code compile. But if the object’s class
		doesn’t implement the interface, the code will throw a ClassCastException at runtime.*/
		
		//Codigo compila, mas não executa
		//Qualquer objeto pode ser casting para uma interface
		//Mas somente vai executar se o objeto implementar seus metodos
		//APARTIR DA LINHA 16 O COMPILA NAO RUNTIME MAIS E NEM CHEGA A CHAMAR OS METODOS
		Jumpable jI = (Jumpable) new Animal();
		//Como o metodo tem que vir de interface, se a interface for vazia nunca vai compilar
		//se a interface tiver um metodo a classe filha tem que implementar com IMPLEMENTS o metodo
		jI.mostrar();
		
		//NAO pode simplesmente usar o mesmo nome.
		//NAO existe nenhum cast possivel que evite runtimeexception
		((Animal)jI).mostrar();
		((Jumpable) new Animal()).mostrar();

		
		//METODO IMPLEMENTADO DE UMA INTERFACE PRECISA SER PUBLIC
		System.out.println("Cannot reduce the visibility of the inherited method from Jumpable");
		
	}

	
	
}

interface Jumpable {
	//"Illegal modifier for the interface method mostrar; only public, abstract, default, static and strictfp are permitted"
	void mostrar();
}
class Animal implements Jumpable {
	String s = "Le lion est le roi de la jungle.";
	public void mostrar() { System.out.println(s); }
}
class Lion extends Animal implements Jumpable {
	//LION NAO PRECISA IMPLEMENTAR JUMPABLE.mostrar();
	//SE ANIMAL QUE E A PRIMEIRA CLASSE CONCRETA A IMPLEMENTAR!!!!!!
}