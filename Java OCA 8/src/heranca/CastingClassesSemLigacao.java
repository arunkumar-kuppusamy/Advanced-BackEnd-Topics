package heranca;

class Z {
}

class Z1 extends Z {
}

class Z2 extends Z {
}

public class CastingClassesSemLigacao {

	public static void main(String args[]) {
		Z b = new Z();
		Z1 b1 = new Z1();
		Z2 b2 = new Z2();

		/*
		 * Z é uma classe simples que nada precisa saber de Z1 ou Z2
		 * Z1 e Z2 sao classes que utilizam por conta propria componenentes de Z
		 * 	Logo Z nao tem contrato e o cast força algo incorreto em orientacao a objetos 
		 */
		//b1 = (Z1) b;//runtime error Z cannot be cast Z1
		
		Z b11 = new Z1();
		Z b22 = new Z2();
		b = b1;//ok
		
		//Com casting, dizemos ao compilador esta errado mas sei oque estou fazendo.
		//b2 = b; //compile error
		//b1 e b2 sao classes distintas nada a ver fazer um Casting.
		//b2 = (Z2) b1;//compile error
		//b1 = (Z) b1;//compile error
		
	}

}
