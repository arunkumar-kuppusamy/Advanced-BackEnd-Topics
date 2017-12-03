package br.com.caelum.estoque.modelo.item;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
