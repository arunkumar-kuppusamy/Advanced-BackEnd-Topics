package br.com.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

import br.com.leilao.dominio.Avaliador;
import br.com.leilao.dominio.Lance;
import br.com.leilao.dominio.Leilao;
import br.com.leilao.dominio.Usuario;

//O padrão de nome de metodo eh sempre NomeDaClasseTest. Isso facilita a rastreabilidade das classes de teste.
public class AvaliadorTest {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// cenario: 3 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		//A ordem é sempre (esperado, calculado).
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveCalcularAMedia() {
		// cenario: 3 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 500.0));

		// executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saida com o esperado
		Assert.assertEquals(400, leiloeiro.getMedia(), 0.0001);
	}

	@Test
	public void testaMediaDeZeroLance() {

		// cenario
		Usuario ewertom = new Usuario("Ewertom");

		// acao
		Leilao leilao = new Leilao("Iphone 7");

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		//validacao
		Assert.assertEquals(0, avaliador.getMedia(), 0.0001);

	}
}