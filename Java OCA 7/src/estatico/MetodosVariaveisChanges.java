package estatico;

public class MetodosVariaveisChanges {

	static int z = 10;
	
	static class Wrapper {
		int w = 10;
	}

	static Wrapper changeWrapper(Wrapper w) {
		w = new Wrapper();//cria um novo w
		w.w += 9;
		
		MetodosVariaveisChanges.z += 5;
		
		return w;//returna 19
	}

	public static void main(String[] args) {
		
		//Acessando uma Variavel Statica
		MetodosVariaveisChanges.z += 5;
		System.out.println(MetodosVariaveisChanges.z);//15
		
		changeWrapper(new Wrapper());
		System.out.println(MetodosVariaveisChanges.z);//20

		
		//Acessando um Classe Statica
		Wrapper w = new Wrapper();
		w.w = 20;
		
		changeWrapper(w);//METODO INUTIL JA QUE NAO ATRIBUI A NADA O VALOR
		w.w += 30;//20+30
		System.out.println(w.w);//50
		
		w = changeWrapper(w);//19
		System.out.println(w.w);

	}

}
