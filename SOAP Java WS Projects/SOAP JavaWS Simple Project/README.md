# SOAP Java Ws Project + SOAPUI

#### Crie um projeto Java Project simples

> Na classe Main, crie um EndPoint
	´´´´java
	import javax.xml.ws.Endpoint;
	public static void main(String[] args) {
		EstoqueWS service = new EstoqueWS();
		Endpoint.publish("http://localhost:8080/estoquews", service);//Publica o @Webservice em //acesse com http://localhost:8080/estoquews?wsdl
	}
	´´´´
	
> Webservice - Crie um que retorne uma List
	´´´´java
	@WebService
	public class EstoqueWS {
		@WebMethod(operationName="todosOsItens")
		@WebResult(name="itens")
		public ListaItens getItens(){
			List<Item> lista = dao.todosItens();
			return new ListaItens(lista);
		}
	}	
	´´´´
	
> XmlRoot - Anote as classes que serão as raizes da resposta Jax-WS
	
	´´´´java
	import javax.xml.bind.annotation.XmlRootElement;
	...
	@XmlRootElement
	public class Item {
	
		private String esse_e_outros_beans;
	
		//Construtor vazio, obrigatorio como varios frameworks
		Item() {
			super();
		}
	}
	´´´´

> DAO - Criamos um para gerar dados para nossa aplicacao, futuramente deve vir de um banco de dados ***nenhuma anotacao necessaria***
	´´´
	public class ItemDao {
	
		private static Map<String, Item> ITENS = new LinkedHashMap<>();
	
		public ItemDao() {
			popularItensNoMapa();
		}
	
		//este método existe apenas para facilitar o primeiro exercicio que não usa o filtro
		public ArrayList<Item> todosItens() {
			return new ArrayList<>(ITENS.values());
		}
		
		private void popularItensNoMapa() {
			ITENS.put("MEA", new Item.Builder().comCodigo("MEA").comNome("MEAN").comTipo("Livro").comQuantidade(5).build());
			ITENS.put("MEA", new Item.Builder().comCodigo("SEO").comNome("SEO na Prática").comTipo("Livro").comQuantidade(4).build());
			ITENS.put("RUB", new Item.Builder().comCodigo("RUB").comNome("Jogos com Ruby").comTipo("Livro").comQuantidade(8).build());
			ITENS.put("GAL", new Item.Builder().comCodigo("GAL").comNome("Galaxy Tab").comTipo("Tablet").comQuantidade(3).build());
			ITENS.put("IP4", new Item.Builder().comCodigo("IP4").comNome("IPhone 4 C").comTipo("Celular").comQuantidade(7).build());
			ITENS.put("IP5", new Item.Builder().comCodigo("IP5").comNome("IPhone 5").comTipo("Celular").comQuantidade(3).build());
			ITENS.put("IP6", new Item.Builder().comCodigo("IP6").comNome("IPhone 6 S").comTipo("Celular").comQuantidade(10).build());
			ITENS.put("MOX", new Item.Builder().comCodigo("MOX").comNome("Moto X").comTipo("Celular").comQuantidade(6).build());
			ITENS.put("MOG", new Item.Builder().comCodigo("MOG").comNome("Moto G").comTipo("Celular").comQuantidade(8).build());
			ITENS.put("MXX", new Item.Builder().comCodigo("MXX").comNome("Moto MAXX").comTipo("Celular").comQuantidade(2).build());
		}
	}
	´´´
	
> XmlAcessor - Criamos uma classe utilitaria para agrupar Todos os resultados
	´´´java	
	@XmlRootElement //eh uma tag que vai aparecer no soap
	@XmlAccessorType(XmlAccessType.FIELD) //gerenciar/acessar o campos e nao os metodos
	public class ListaItens {
	
		@XmlElement(name="item")
		private List<Item> itens;
	
		public ListaItens(List<Item> itens) {
			this.itens = itens;
		}
	
		ListaItens() {
		}
	
		public List<Item> getItens() {
			return itens;
		}
	
	}
	´´´

	