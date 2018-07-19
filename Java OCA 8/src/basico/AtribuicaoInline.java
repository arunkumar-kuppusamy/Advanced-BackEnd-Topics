package basico;

public class AtribuicaoInline {

	static int x = 5;

	public static void main(String[] args) {
		
		int x = (x = 3) * 4; //x recebe 3 e multiplica 4.
		System.out.println(x);//prints 12
	}

}