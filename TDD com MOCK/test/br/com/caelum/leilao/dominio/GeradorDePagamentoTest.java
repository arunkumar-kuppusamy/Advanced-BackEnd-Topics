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
            .lance(new Usuario("Jos� da Silva"), 2000.0)
            .lance(new Usuario("Maria Pereira"), 2500.0)
            .constroi();

        when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));
        
        //A diferen�a entre os dois testes foi discutida no exerc�cio anterior. N�o precisamos mockar classes que s�o f�ceis de testar. E ainda aumentamos o feedback dos nossos testes em rela��o a qualidade externa do nosso c�digo quando n�o os mockamos. Afinal, se algo n�o estiver funcionando, temos mais testes para tentar capturar tal falha.
        //Remova o mock de Avaliador e passe a inst�ncia concreta para a classe GeradorDePagamento.
        Avaliador avaliador = mock(Avaliador.class);
        when(avaliador.getMaiorLance()).thenReturn(2500.0);

        GeradorDePagamento gerador = 
            new GeradorDePagamento(leiloes, pagamentos, avaliador);
        gerador.gera();

        // como fazer assert no Pagamento gerado?
        
        /*
         Mas repare que a inst�ncia � passada para o RepositorioDePagamentos, que � um mock! Ent�o, podemos pedir ao Mock para guardar esse objeto para que possamos o recuperar a fim de realizar as asser��es! A classe do Mockito que faz isso � chamada de ArgumentCaptor, ou seja, capturador de argumentos.
		 Para a utilizarmos, precisamos instanci�-la, passando qual a classe que ser� recuperada. No nosso caso, � a classe Pagamento. Em seguida, fazemos uso do verify() e checamos a execu��o do m�todo que recebe o atributo. Como par�metro, passamos o m�todo capture() do ArgumentCaptor:
         Veja que o ArgumentCaptor possibilita capturar a inst�ncia que foi passada para o Mock. Isso � especialmente �til em situa��es como essas: nossa classe de produ��o instancia um novo objeto, que � passado para uma das depend�ncias. Assim, conseguimos garantir que o objeto criado est� correto.
        */
        //Instanciamos o ArgumentCaptor dessa forma, colocando a classe que queremos capturar os valores na memoria ou seja ver se tem o valor l� dentro
        //ArgumentCaptor<ABC> argumento = ArgumentCaptor.forClass(ABC.class);
        
        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());
        //Simples assim! Agora o argumento cont�m a inst�ncia de Pagamento criada! Basta pegarmos a inst�ncia do Pagamento atrav�s do m�todo argumento.getValue():
        Pagamento pagamentoGerado = argumento.getValue();
        assertEquals(2500.0, pagamentoGerado.getValor(), 0.00001);
        
    }

    //NAO VAI FUNCIONAR SE ESTIVER EM DIA UTIL, TEM QUE RODAR SABADO OU DOMINGO
	//@Test
	public void deveEmpurrarParaOProximoDiaUtil() {

		RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("Jos� da Silva"), 2000.0)
				.lance(new Usuario("Maria Pereira"), 2500.0).constroi();

		when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));

		GeradorDePagamento gerador = new GeradorDePagamento(leiloes, pagamentos, new Avaliador());
		gerador.gera();

		ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
		verify(pagamentos).salva(argumento.capture());
		Pagamento pagamentoGerado = argumento.getValue();

		//Nosso teste falha! Veja a implementa��o do m�todo primeiroDiaUtil. Ele verifica se o dia de hoje � s�bado ou domingo. Ou seja, esse teste s� passar� se voc� estiver fazendo esse curso no fim de semana!
		//A pergunta �: como mudar a data atual? 
		//Uma poss�vel solu��o para o problema � criar uma abstra��o de um rel�gio; uma interface que teria um m�todo hoje()
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

		// ensinamos o mock a dizer que "hoje" � sabado!
		when(relogio.hoje()).thenReturn(sabado);

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("Jos� da Silva"), 2000.0)
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
            .lance(new Usuario("Jos� da Silva"), 2000.0)
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