package strings_Nuvem_Imutabilidade;

public class StringChaining {

	//abstract String abstrato() throws Throwable;

	public static void main(String[] args) {

		//replace é index zero-base, inclusivo ou exclusivo?
		StringBuilder s1 = new StringBuilder("0123456789");
		StringBuilder s2 = new StringBuilder("0123456789");

		StringBuilder s3 = s1.replace(0, 0, "x");//nao apaga nada, insere no inicio antes de tudo, e avanca tudo para frente.
		//StringBuilder s3 = s1.replace(0, 1, "x");//apaga 0 e coloca x no lugar
		//StringBuilder s3 = s1.replace(0, 3, "x");//substitui 3 valores 0,1,2 por x
		//StringBuilder s3 = s1.replace(3, 3, "x");//insere ENTRE 0 e 1, nao apaga nada
		//StringBuilder s3 = s1.replace(3, 3, "x");//insere ENTRE 2 e 3, nao apaga nada, SEMPRE que for igual ele nao apaga nada
		//StringBuilder s3 = s1.insert(1, "x");//insere entre 0 e 1, nao apaga nada
		//StringBuilder s3 = s1.insert(0, "x");//insere no inicio antes de tudo, e avanca tudo para frente.
		//StringBuilder s3 = s1.insert(s1.length(), "x");//insere no final, se s1.length()+1 StringIndexOutOfBounds

		//NAO EXISTE SUBSTRING NA CLASSE StringBuilder, logo
			// 1 devolve uma String e não um StringBuilder
			// 2 consequentemente gera uma string imutavel, que nao altera o valor original da StringBuilder
		int inclusivo = 2, exclusivo = 5;
		//String s3 = s1.substring(inclusivo, exclusivo);//nao pega o 5 pega ate a posicao 4 apenas.

		System.out.println(s3.toString());
		
		
		//Erro substring vem de String e insert vem de outra classe as duas nao podem se alterar de forma conjunta
		//s1.append("a").substring(0, 4).insert(2, "asdf"); - nao pode alterar uma substring
		//Ok estamos alterando String com String, classe com classe compativel 
		s1.append("a").substring(0, 4).trim().toLowerCase().concat("xyz").length();//ok apenas append a
		s1.append(s1.substring(0, 4).trim().toLowerCase().concat("xyz"));//ok apend 0123xyz
		s1.append("a").insert(2,"asdf").substring(0, 4);//Ok substring como ultimo parametro isolado
		
		System.out.println(s1);
		
		/*
		 * Questão de prova
		 * perguntava qual era o valor apos rodar separadamente?
		 * sem retornar o valor, para outra variavel
		 */
		System.out.println("\nExplanation:\r\n" + 
				"You need to understand how append, insert, delete, and substring methods of\r\n" + 
				"StringBuilder/StringBuffer work. Please go through JavaDoc API for these\r\n" + 
				"methods. This is very important for the exam. Observe that substring() does not\r\n" + 
				"modify the object it is invoked on but append, insert and delete do.\n");
		
		StringBuilder b1 = new StringBuilder("snorkler");
		StringBuilder b2 = new StringBuilder("yoodler");
		
		b1.append(b2.substring(2, 5).toUpperCase());
		System.out.println("\tB1: ["+b1+"]\t\tB2: ["+b2+"]");
		
		b1 = new StringBuilder("snorkler");b2 = new StringBuilder("yoodler");//RESET VALORES
		b2.insert(3, b1.append("a"));
		System.out.println("\tB1: ["+b1+"]\t\t\tB2: ["+b2+"]");
		
		b1 = new StringBuilder("snorkler");b2 = new StringBuilder("yoodler");//RESET VALORES
		b1.replace(3, 4, b2.substring(4)).append(b2.append(false));
		System.out.println("\tB1: ["+b1+"]\tB2: ["+b2+"]");

	}

}
