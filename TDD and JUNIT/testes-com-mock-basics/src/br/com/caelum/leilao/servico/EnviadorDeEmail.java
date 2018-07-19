package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Leilao;

/**
 * File: EnviadorDeEmail.java
 * @author Rodrigo Cirino
 * @since Jun 27, 2018 11:34:04 PM
 */
public interface EnviadorDeEmail {
	void envia(Leilao leilao);
}
