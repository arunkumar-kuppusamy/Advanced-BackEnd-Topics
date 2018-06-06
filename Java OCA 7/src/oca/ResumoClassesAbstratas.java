package oca;

public abstract class ResumoClassesAbstratas {

	//Sou obrigado a declarar como publico na subimplementacao, porque nao existe nenhum modificador mais abrangente
	//Nao sou obrigado a declarar o Throws, mas deve ser igual ou menos abrangente que o declarado. ex. Exception - > Throwable, nao pode redefinir para Throwable.
	//o nome do parametro i posso mudar para qualquer xyz, mas o tipo do parametro nao posso
	//se o parametro for uma classe que exista subclasses posso usar Covariant Return, e declarar uma subclasse dessa. ex. List -> Arraylist, Carro -> Volante
	//Todo metodo abstrato tem que ser declarado como abstrato, a nao ser em caso de interface que e implicito
	//Um método abstratao é obrigatorio sempre estar dentro de uma classe abstrata, mesmo que seja o metodo main, declare a classe com a keyword abstract
	//Mesma ordem dos parametros - Nao pode mudar a ordem senao será overloading e não overriding
	abstract public String abstrato(int i) throws Exception;

	public static void main(String[] args) {

	}

}
