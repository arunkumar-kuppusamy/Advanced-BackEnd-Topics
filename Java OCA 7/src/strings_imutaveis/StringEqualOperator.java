package strings_imutaveis;


/*
 *  Para entender a diferenca entre equal e ==
 *  Precisamos lembrar do pool(nuvem) de criacao de Strings que a JVM gerencia, algo especifico do Java
 *  	e como a JVM trabalha para reutilizar as strings ja criadas e cria novas dentro e fora do pool.
 *  
 */

public class StringEqualOperator {

	public static void main(String[] args) {

		//Se for comparar string literais concatenadas ok ele pode verificar no pool e usar a mesma referencia
		//Se tivermos uma variavel concatenando com um literal, temos um local da memoria com a variavel e outro local da memoria com a string literal
			//logo o java nao vai conseguir usar a referencia dubia, e retorna false na comparacao.
		
		String a = "a";
		String ab = "a"+"b";
		System.out.println( ab == "ab" );//true
		System.out.println( ab == (a+"b") );//false, duas referencias na memoria variavel a / literal b. 
		System.out.println();
		
		//Abaixo veja que o java reutiliza strings na memoria se forem identicas, exceto se usar new.
		String t1 = "texto";
		System.out.println(t1.toLowerCase() == t1);//true porque nao houve atribuicao apenas devolveu a mesma
		String t2 = t1.toLowerCase();
		System.out.println(t1 == t2);//true - o java utiliza a mesma String que nao foi modificada. 
		String t3 = t1.toUpperCase();
		System.out.println(t1.toUpperCase() == t3);//false, String no Java e sempre case-sensitive, logo ele vai criar outra referencia
		System.out.println();
		
		//Double iw = new Double(0.0);
		Byte iw = new Byte((byte) 0);
		double ip = 0;
		//byte ip = 0;

		System.out.println(iw.equals(ip));//true
		System.out.println(iw == ip);//true, NAO PRECISA SER MESMA CLASSE, == vai converter para o mais abrangente.

		// Ao fazer toString convertemos na classe String qualquer coisa.
		String s = new String("Java");
		String s1 = "Java";
		StringBuilder sb = new StringBuilder("Java");
		StringBuffer sf = new StringBuffer("Java");
		String s2 = s;
		String s3 = sb.toString();
		String s4 = new String(s1);
		String s5 = null;
		s5 = "Java";
		String s6 = "Java";
		String s7 = new String("Java");
		
		System.out.println("\nequals: Mesma classe, mesmo valor");
		System.out.println(s.equals(s1));
		System.out.println(s1.equals(s));
		System.out.println(sb.toString().equals(sf.toString()));
		System.out.println(s2.equals(s));
		System.out.println(s3.equals(s2));
		System.out.println(s3.equals(sf.toString()));
		System.out.println(s3.equals(sb.toString()));
		System.out.println(s4.equals(s1));
		System.out.println(s5.equals(s1));

		//Parece que ao dar new ele cria outro espaco na memoria logo fica false
		//ao continuar criando strings de mesma classe ele aloca no mesmo e resulta true.
		//== verifica se esse objeto aponta para o mesma referencia do objeto.
			//Lembre-se que existe um pool e o java pode usar a mesma referencia
		//Ao usar new o Java não consegue procurar se existe algo do pool e sempre vai criar outra referencia!!!
		System.out.println("\n==: Mesmo referencia no pool de memoria, mesmo valor");
		System.out.println(s == s1);
		System.out.println(s1 == s);
		System.out.println(sb.toString() == sf.toString());
		System.out.println(s2 == s);//true - s = new String("x"); String a = s;
		System.out.println(s3 == s2);
		System.out.println(s3 == sf.toString());
		System.out.println(s3 == sb.toString());
		System.out.println(s4 == s1);
		System.out.println(s4 == s);
		System.out.println(s7 == s1);
		//Abaixo e true, porque o java é esperto se vc tem criar um literal String com o mesmo valor
		//o java reutiliza apontando o novo para o anterior, logo na verdade ele fez
		//String s5 = s1;//mesmo que vc tenha colocado o valor ele vai utilizar o do pool
		System.out.println(s5 == s1);//String s = "a" e String b = "a";
		System.out.println(s6 == s1);
		System.out.println();
		
		//QUANTAS STRINGS FORAM CRIADAS
		String q = new String("hello ");//2 uma com new e outra ao usar aspas duplas no literal
		String h  = "hello ";//0 - criamos hello na linha de cima ele vai utilizar esta e nao cria nenhum objeto.
		String w = "world";//1 - ok world nunca foi criado ele cria um novo objeto agora.
		
		System.out.println("hello ");//0 - e igual a primeira linha nao cria nenhum objeto
		System.out.println(h+"world");//1 - nao temos nada como hello world junto, logo coloca fora do pool esta nova string.
		
		
	}

}
