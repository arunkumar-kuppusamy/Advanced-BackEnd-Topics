package interruptor;

/*
 * Switch variavel boolean pode ser criada em algum
 * 	catch ou default e ser usada dentro do proprio switch
 * 	switch vai ser criado em tempo de compilacao logo no runtime ele já tera todas as variaveis e constantes
 * 		por isso precisamos colocar uma constante como comparacao porque sao verificados bem antes do runtime.
 */

public class SwitchCreateVariavelInside {

	public static void main(String[] args) {
		switch (args.length) // 1
		{
		case -1:
			boolean b = false;// 2
			System.out.println("b created "+b);
			break;
		case 0:
			b = true;
			System.out.println("b exists "+b);
			break;
		}
		//if (b)
		//	System.out.println(args[2]);
	}

}
