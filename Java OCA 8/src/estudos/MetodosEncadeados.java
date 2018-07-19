package estudos;

import java.util.ArrayList;

/*
 * BEIZIQUE - Alteracao de conteudo por valor e por referencia.
 * 
 * Este código demonstro que quando invocamos a chamada de um metodo dentro de outro
 * Nao copiamos seu codigo para dentro do atual, nem passamos seus valores.
 * Porem este tem acesso as posicao de memória que estão sendo utilizadas no momento, 
 * afinal agora este novo metodo tambem esta no mesmo heap de memoria
 * Logo para alterar um valor sem fazer passagem por valor e usando o return do metodo
 * podemos alterar por referencia aqueles que não são imutaveis ou seja
 * que podemos alterar seu conteudo após criado sem a criacao de um novo objeto
 * Abaixo temos dois exemplos que funcionam
 * 	StringBuilder, StringBuffer e ArrayList.
 * e funcionara para qualquer um que usando o operador ponto altere o valor atual dos objetos.
 * 
 */

public class MetodosEncadeados {

	public static void main(String[] args) {

		String s1 = new String("java");
		StringBuilder s2 = new StringBuilder("java");
		//Integer i = new Integer(1234);
		int i = 1234;
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1234);
		
		replaceInt(i);
		replaceList(a);
		replaceString(s1);//lava
		replaceStringBuilder(s2);//javac
		
		System.out.println(i+ s1 + s2 + a.toString());

	}

	static void replaceList(ArrayList<Integer> a) {
		a.add(56789);
	}

	static void replaceString(String s) {
		s = s.replace('j', 'l');
	}
	
	static void replaceInt(int i) {
		i = i*2;
	}

	static void replaceStringBuilder(StringBuilder s) {
		s.append("c");
	}

}
