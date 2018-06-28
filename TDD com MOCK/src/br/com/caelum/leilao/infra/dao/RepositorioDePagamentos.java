package br.com.caelum.leilao.infra.dao;

import br.com.caelum.leilao.dominio.Pagamento;

/**
 * File: RepositorioDePagamentos.java
 * @author Rodrigo Cirino
 * @since Jun 27, 2018 11:53:11 PM
 */
public interface RepositorioDePagamentos {

	void salva(Pagamento capture);

}

