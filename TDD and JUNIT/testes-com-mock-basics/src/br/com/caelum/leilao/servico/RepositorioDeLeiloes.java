package br.com.caelum.leilao.servico;

import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;

/**
 * File: RepositorioDeLeiloes.java
 * @author Rodrigo Cirino
 * @since Jun 27, 2018 11:05:18 PM
 */
public interface RepositorioDeLeiloes {
    void salva(Leilao leilao);
    List<Leilao> encerrados();
    List<Leilao> correntes();
    void atualiza(Leilao leilao);
}