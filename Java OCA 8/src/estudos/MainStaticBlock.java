package estudos;

class Secundaria extends Principal {
	
	static int i = 0;
	
	static {
		System.out.println("Secundaria 1st static Main "+i++);
	}
	
	static {
		System.out.println("Secundaria 2nd static Main "+i++);
	}
	
	{
		System.out.println("Secundaria 1st instance Main "+i++);
	}

	{
		System.out.println("Secundaria 2nd instance Main "+i++);
	}
	
	Secundaria() {
		System.out.println("Secundaria no-arg const "+i++);
	}
	
	Secundaria(int x) {
		i = x;
		System.out.println("Secundaria 1-arg const "+i);
	}
}

class Principal{
	
	static int i = 0;
	
	static {
		System.out.println("Principal 1st static Main "+i++);
	}
	
	static {
		System.out.println("Principal 2nd static Main "+i++);
	}
	
	{
		System.out.println("Principal 1st instance Main "+i++);
	}

	{
		System.out.println("Principal 2nd instance Main "+i++);
	}
	
	Principal() {
		System.out.println("Principal no-arg const "+i++);
	}
	
	Principal(int x) {
		i = x;
		System.out.println("Principal 1-arg const "+i);
	}
}


public class MainStaticBlock {

	static int i = 0;
	
	static {
		System.out.println("1st static Main "+i++);
	}
	
	static {
		System.out.println("2nd static Main "+i++);
	}
	
	{
		System.out.println("1st instance Main "+i++);
	}

	{
		System.out.println("2nd instance Main "+i++);
	}
	
	MainStaticBlock() {
		System.out.println("no-arg const "+i++);
	}
	
	MainStaticBlock(int x) {
		i = x;
		System.out.println("1-arg const "+i);
	}

	public static void main(String[] args) {
		//System.out.println("------main vai dar um new agora----");
		//new Main(99);
		
		//Secundaria p = new Secundaria();
	}


}
