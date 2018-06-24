package br.com.leilao.dominio;

import org.junit.Test;
import org.junit.Assert;

/*
 * JUNIT esta no Eclipse por padrão, nao precisa baixar JAR
 * 1 - Botao direito no projeto > Add Libraries > JUNIT 5. pronto!!
 * 2 - O metodo deve ser public void, e nao ter argumentos
 * 3 - O metodo principal deve ser anotado com @org.junit.Test
 */
/**
 * Testes automatizados TDD tera as seguintes etapas
 * 1 - Monte um cenario, 2 - Executa uma acao, 3 - Avalia a saida
 * 
 * @author Rodrigo Cirino
 */

public class TesteDoAvaliador {

	public static void main(String[] args) {
        // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double maiorEsperado = 400;
        double menorEsperado = 250;

        System.out.println(maiorEsperado == leiloeiro.getMaiorLance());
        System.out.println(menorEsperado == leiloeiro.getMenorLance());
    }
	
	@Test
	public void doubleSaoIguaisComVariancia() {

		double d = 1.00;
		double d2 = 1.09;
		Assert.assertEquals(d, d2, 0.1);//delta e o valor permitido de variancia
	}

	@Test
	public void stringComecamCom() {

		String s = "";
		String s2 = "";
		Assert.assertEquals(s, s2);//delta e o valor permitido de variancia
	}

	
	
	
}
