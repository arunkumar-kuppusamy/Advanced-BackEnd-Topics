package estudos;

public class Static_blocks {

	static int i = 0;
	
	Static_blocks(int x) {
		i = x;
		System.out.println("1-arg const "+i);
	}
	
	{
		System.out.println("1st instance Main "+i++);
	}

	static {
		System.out.println("1st static Main "+i++);
	}
		
	{
		System.out.println("2nd instance Main "+i++);
	}
	
	static {
		System.out.println("2nd static Main "+i++);
	}
	
	Static_blocks() {
		System.out.println("no-arg const "+i++);
	}

	public static void main(String[] args) {
		System.out.println("------main vai dar um new agora----");
		new Static_blocks(99);
	}


}
