package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.infra.dao.RepositorioDePagamentos;
import br.com.caelum.leilao.servico.Avaliador;
import br.com.caelum.leilao.servico.GeradorDePagamento;
import br.com.caelum.leilao.servico.Relogio;
import br.com.caelum.leilao.servico.RepositorioDeLeiloes;

/**
 * File: GeradorDePagamentoTest.java
 * @author Rodrigo Cirino
 * @since Jun 27, 2018 11:57:59 PM
 */
public class GeradorDePagamentoTest {

    @Test
    public void deveGerarPagamentoParaUmLeilaoEncerrado() {

        RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
        RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);

        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation")
            .lance(new Usuario("José da Silva"), 2000.0)
            .lance(new Usuario("Maria Pereira"), 2500.0)
            .constroi();

        when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));
        
        //A diferença entre os dois testes foi discutida no exercício anterior. Não precisamos mockar classes que são fáceis de testar. E ainda aumentamos o feedback dos nossos testes em relação a qualidade externa do nosso código quando não os mockamos. Afinal, se algo não estiver funcionando, temos mais testes para tentar capturar tal falha.
        //Remova o mock de Avaliador e passe a instância concreta para a classe GeradorDePagamento.
        Avaliador avaliador = mock(Avaliador.class);
        when(avaliador.getMaiorLance()).thenReturn(2500.0);

        GeradorDePagamento gerador = 
            new GeradorDePagamento(leiloes, pagamentos, avaliador);
        gerador.gera();

        // como fazer assert no Pagamento gerado?
        
        /*
         Mas repare que a instância é passada para o RepositorioDePagamentos, que é um mock! Então, podemos pedir ao Mock para guardar esse objeto para que possamos o recuperar a fim de realizar as asserções! A classe do Mockito que faz isso é chamada de ArgumentCaptor, ou seja, capturador de argumentos.
		 Para a utilizarmos, precisamos instanciá-la, passando qual a classe que será recuperada. No nosso caso, é a classe Pagamento. Em seguida, fazemos uso do verify() e checamos a execução do método que recebe o atributo. Como parâmetro, passamos o método capture() do ArgumentCaptor:
         Veja que o ArgumentCaptor possibilita capturar a instância que foi passada para o Mock. Isso é especialmente útil em situações como essas: nossa classe de produção instancia um novo objeto, que é passado para uma das dependências. Assim, conseguimos garantir que o objeto criado está correto.
        */
        //Instanciamos o ArgumentCaptor dessa forma, colocando a classe que queremos capturar os valores na memoria ou seja ver se tem o valor lá dentro
        //ArgumentCaptor<ABC> argumento = ArgumentCaptor.forClass(ABC.class);
        
        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());
        //Simples assim! Agora o argumento contém a instância de Pagamento criada! Basta pegarmos a instância do Pagamento através do método argumento.getValue():
        Pagamento pagamentoGerado = argumento.getValue();
        assertEquals(2500.0, pagamentoGerado.getValor(), 0.00001);
        
    }

    //NAO VAI FUNCIONAR SE ESTIVER EM DIA UTIL, TEM QUE RODAR SABADO OU DOMINGO
	//@Test
	public void deveEmpurrarParaOProximoDiaUtil() {

		RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("José da Silva"), 2000.0)
				.lance(new Usuario("Maria Pereira"), 2500.0).constroi();

		when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));

		GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, new Avaliador());
		gerador.gera();

		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
		verify(pagamentos).salva(argumento.capture());
		Pagamento pagamentoGerado = argumento.getValue();

		//Nosso teste falha! Veja a implementação do método primeiroDiaUtil. Ele verifica se o dia de hoje é sábado ou domingo. Ou seja, esse teste só passará se você estiver fazendo esse curso no fim de semana!
		//A pergunta é: como mudar a data atual? 
		//Uma possível solução para o problema é criar uma abstração de um relógio; uma interface que teria um método hoje()
		assertEquals(Calendar.MONDAY, pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK));
	}
	
	@Test
	public void deveEmpurrarParaOProximoDiaUtilComRelogioTeste() {
		RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
		Relogio relogio = mock(Relogio.class);

		// dia 7/abril/2012 eh um sabado
		Calendar sabado = Calendar.getInstance();
		sabado.set(2012, Calendar.APRIL, 7);

		// ensinamos o mock a dizer que "hoje" é sabado!
		when(relogio.hoje()).thenReturn(sabado);

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("José da Silva"), 2000.0)
				.lance(new Usuario("Maria Pereira"), 2500.0).constroi();

		when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));

		GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, new Avaliador(), relogio);
		gerador.gera();

		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
		verify(pagamentos).salva(argumento.capture());
		Pagamento pagamentoGerado = argumento.getValue();

		assertEquals(Calendar.MONDAY, pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK));
		assertEquals(9, pagamentoGerado.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
    public void deveEmpurrarPagamentoNoDomingoParaOProximoDiaUtil() {
        RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
        RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
        Relogio relogio = mock(Relogio.class);

        Calendar domingo = Calendar.getInstance();
        domingo.set(2012, Calendar.APRIL, 8);
        when(relogio.hoje()).thenReturn(domingo);

        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation")
            .lance(new Usuario("José da Silva"), 2000.0)
            .lance(new Usuario("Maria Pereira"), 2500.0)
            .constroi();

        when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));

        GeradorDePagamento gerador = 
            new GeradorDePagamento(leiloes, pagamentos, new Avaliador(), relogio);
        gerador.gera();

        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());
        Pagamento pagamentoGerado = argumento.getValue();

        assertEquals(Calendar.MONDAY, 
            pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK));
        assertEquals(9, 
            pagamentoGerado.getData().get(Calendar.DAY_OF_MONTH));
    }
	
}