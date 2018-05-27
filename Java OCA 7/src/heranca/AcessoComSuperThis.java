package heranca;

/*
 * Como acessar apartir de C o metodo perfom de A?? 
 * 	Resposta: Não é possível porque super acessa somente construtores
 * 		e variaveis não-estaticas, e nunca um metodo.
 */

/*
 *  Entedimento final, this e super contem as mesmas regras logo.
 *  
 *  	super não pode ser inserido dentro de um método estatico nunca, mas pode acessar um. super.metodoStatico(); ok
 *  	this tambem não pode ser inserido DENTRO de um método estatico nunca, mas pode acessar métodos staticos, ex.this.main(new String[]{"a","b"});
 *  	super nunca acessa dois niveis superiores, e sim somente o pais superior indicado no extends da propria classe.
 *  		como nunca vai haver uma classe que extends dus ou mais super sempre vai indicar para a primeira classe superior, anterior, antecessor.
 *  
 *  	variaveis static nos pai , não precisa ser static no filho, regra de herança
 *  	metodo static no pai, obrigatoriamente deve ser static no filho, regra de heranca e sobreescrita
 *
 * 		super() acessa o construtor é um método da classe Object. não confunda com super.ponto
 *		super.super.metodo() - isso é um absurdo, assim como é uma absurdo algo como this.super.
 *		super().metodo() outro absurdo.
 *
 *		vale sempre lembrar que se existe um unico construtor com parametros chamar o super sem parametros vai dar erro, porque neste caso o construtor padrao nao foi criado.	
 */

class A {
	public static String a = " [A] ";
	public String perform_work() {
		return ("1<a em a> " + this.a);
	}
}

class B extends A {
	public String b = " [B] ";
	public String perform_work() {
		return ("2<b -> a> " + super.a + " - " + super.perform_work());
	}
}

class C extends B {
	public static String c = " [C] ";
	public String perform_work() {
		// STACKOVERFLOW ((A) this).perform_work();
		return ("3<c -> b> " + super.a + " - " + super.perform_work());
	}
}

public class AcessoComSuperThis {

	public static void main(String[] args) {
		AcessoComSuperThis t = new AcessoComSuperThis();
		t.acesso();
	}
	public void acesso() {
		//THIS acessa um método static DENTRO de um método NÃO-Statico, REDUNDANTE mas OK FUNCIONA
		//this.main(new String[]{"a","b"});
		C c = new C();
		System.out.println(c.perform_work());
		// ESTUDAR OS USOS DO SUPER NESTE CASO
	}
}
