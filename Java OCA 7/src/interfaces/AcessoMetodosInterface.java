package interfaces;

interface Flyer {
	String name = new String("CONSTANTE QUE NUNCA VAI MUDAR");//public + static + final
	String getName();
}

class Bird implements Flyer {
	public String name;

	public Bird(String name) {
		this.name = name;
	}

	public String getName() {
		System.out.println("BIRD");
		return name;
	}
}

class Eagle extends Bird {
	public Eagle(String name) {
		super(name);
	}
}

public class AcessoMetodosInterface {
	public static void main(String[] args) throws Exception {
		Flyer f = new Eagle("American Bald Eagle");
		// PRINT NAME HERE
		
		//TODOS ABAIXO ACESSAM OS METODOS E VARIAVEIS DE BIRD!!!
		
		
		System.out.println(f.getName());//getName em Bird, nao acessa getname da interface nao diga isso
		
		//Tanto Eagle como Bird, uma extende a outra tem visualizacao da variavel da interface
			//Logo tanto faz usar uma classe ou outra.
		
		System.out.println( ( (Eagle) f)  .name      );//name em Bird
		System.out.println( ( (Bird)  f)  .getName() );//getName de Bird

		System.out.println(f.name);//Interface não tem o atributo name, se tivesse seria VAZIO porque toda variavel na interface é constante e vai manter seu valor da criacao na interface.
		//System.out.println(Eagle.name);//ERROR name is not a static field in class Eagle.
		//System.out.println(Eagle.getName(f));//ERROR f não é parametro de getName
		
	}
}