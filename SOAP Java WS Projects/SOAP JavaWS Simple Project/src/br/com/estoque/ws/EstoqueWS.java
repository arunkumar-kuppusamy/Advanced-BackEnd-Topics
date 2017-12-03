package br.com.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWS {

	private ItemDao dao = new ItemDao();

	//metodo disponivel para o webservice
	@WebMethod(operationName="todosOsItens")//alias, apelido para o ponto de acesso
	@WebResult(name="itens")//alias para a tag de resultado, o padrao eh return
	public ListaItens getItens(){//usando uma classe auxiliar para poder agrupar os resultados em [itens]

		//public List<Item> getItens(){//usaremos outra classe para agrupar
		System.out.println("Chamando getItens()");

		List<Item> lista = dao.todosItens();

		return new ListaItens(lista);
	}



}
