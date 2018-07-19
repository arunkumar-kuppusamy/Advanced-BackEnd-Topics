package br.com.caelum.leilao.infra.email;

import br.com.caelum.leilao.dominio.Leilao;

/**
 * File: Carteiro.java
 * @author Rodrigo Cirino
 * @since Jun 27, 2018 11:39:31 PM
 */
public interface Carteiro {

	void envia(Leilao leilao);
}

