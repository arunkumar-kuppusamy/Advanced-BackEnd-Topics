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
import org.mockito.InOrder;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.servico.EncerradorDeLeilao;
import br.com.caelum.leilao.servico.EnviadorDeEmail;
import br.com.caelum.leilao.servico.RepositorioDeLeiloes;

/*
 * � imposs�vel mockar m�todos est�ticos! 
 * USANDO MOCKITOS PARA SIMULAR SIMULAR O COMPORTAMENTO DE UMA CLASSE
 * 
 */

/**
 * File: EncerradorDeLeilaoTest.java
 * @author Rodrigo Cirino
 * @since Jun 27, 2018 10:53:39 PM
 */
public class EncerradorDeLeilaoTest {


   @Test
    public void deveEncerrarLeiloesQueComecaramUmaSemanaAtras() {

        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
            .naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
            .naData(antiga).constroi();

        /* 
         * Em vez de usar a classe LeilaoDaoFalso para simular um banco de dados
         * vamos usar o mockit sem alterar nossas classes.
         */
        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        //LeilaoDao daoFalso = mock(LeilaoDao.class);
        
        //Quando voc� invoca um m�todo no mock que n�o foi previamente ensinado a responder algo, o Mockito faz o seguinte
        //Se o m�todo retorna um inteiro, double, ou um tipo primitivo qualquer, ele retornar� 0 - 
        //Se o m�todo retorna uma lista, o Mockito retornar� uma lista vazia - 
        //Se o m�todo retorna uma outra classe qualquer, o Mockito retorna null.
        /*
         * Estamos usando a ideia de MOCKS OBJECTS (dao), OBJETOS DUBL�s
	     * ENTENDA: quando o daofalseo for chamado, entao retorne, uma lista de leiloes.
         */
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));

        EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
        EncerradorDeLeilao encerrador = 
            new EncerradorDeLeilao(daoFalso, carteiroFalso);
        encerrador.encerra();

        // busca no banco a lista de encerrados
        //List<Leilao> encerrados = dao.encerrados();
        
        assertEquals(2, encerrador.getTotalEncerrados());
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
    }
   
    @Test
    public void naoDeveEncerrarLeiloesQueComecaramMenosDeUmaSemanaAtras() {

	  Calendar ontem = Calendar.getInstance();
	  ontem.set(Calendar.DAY_OF_MONTH, -1);
	
	  Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
	      .naData(ontem).constroi();
	  Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
	      .naData(ontem).constroi();
	
	  RepositorioDeLeiloes daoFalso = (RepositorioDeLeiloes) mock(LeilaoDao.class);
	  when(daoFalso.correntes())
	      .thenReturn(Arrays.asList(leilao1, leilao2));
	
	  EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
	  EncerradorDeLeilao encerrador = 
	      new EncerradorDeLeilao(daoFalso, carteiroFalso);
          
        encerrador.encerra();

        assertEquals(0, encerrador.getTotalEncerrados());
        assertFalse(leilao1.isEncerrado());
        assertFalse(leilao2.isEncerrado());
        
        // verifys aqui
        verify(daoFalso, never()).atualiza(leilao1);
        verify(daoFalso, never()).atualiza(leilao2);
    }
    
    @Test
    public void naoDeveEncerrarLeiloesCasoNaoHajaNenhum() {

        RepositorioDeLeiloes daoFalso = (RepositorioDeLeiloes) mock(LeilaoDao.class);
        when(daoFalso.correntes())
            .thenReturn(new ArrayList<Leilao>());

        EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
        EncerradorDeLeilao encerrador = 
            new EncerradorDeLeilao(daoFalso, carteiroFalso);

        encerrador.encerra();

        assertEquals(0, encerrador.getTotalEncerrados());
    }
    
    @Test
    public void deveAtualizarLeiloesEncerrados() {

    	  Calendar antiga = Calendar.getInstance();
          antiga.set(1999, 1, 20);

          Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
              .naData(antiga).constroi();

          RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
          when(daoFalso.correntes())
              .thenReturn(Arrays.asList(leilao1));

          EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
          EncerradorDeLeilao encerrador = 
              new EncerradorDeLeilao(daoFalso, carteiroFalso);

          encerrador.encerra();

        //verify do Mockito. Nele, indicaremos qual m�todo que queremos verificar se foi invocado
        //Veja o times(1). Ele tamb�m � um m�todo da classe Mockito. Ali passamos a quantidade de vezes que o m�todo deve ser invocado;
        //Por curiosidade, se fizermos a classe EncerradorDeLeilao invocar duas vezes o DAO, nosso teste falhar�. Ele nos avisar� que o m�todo foi invocado 2 vezes, o que n�o era esperado:
        /*
         	Ainda podemos passar atLeastOnce(), atLeast(numero) e atMost(numero) para o verify().
        	O m�todo atLeastOnce() vai garantir que o m�todo foi invocado no m�nimo uma vez.
        	O m�todo atLeast(numero) funciona de forma an�loga ao m�todo acima, com a diferen�a de que passamos para ele o n�mero m�nimo de invoca��es que um m�todo deve ter.
        	Por fim, o m�todo atMost(numero) nos garante que um m�todo foi executado at� no m�ximo N vezes. Ou seja, se o m�todo tiver mais invoca��es do que o que foi passado para o atMost, o teste falha.
        */
        verify(daoFalso, times(1)).atualiza(leilao1);

    }
    
    @Test
    public void deveEnviarEmailAposPersistirLeilaoEncerrado() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
            .naData(antiga).constroi();

        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1));

        EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
        EncerradorDeLeilao encerrador = 
            new EncerradorDeLeilao(daoFalso, carteiroFalso);

        encerrador.encerra();

        //Para isso, fa�a uso do m�todo inOrder() do Mockito. Veja um exemplo de utiliza��o:
        InOrder inOrder = inOrder(daoFalso, carteiroFalso);
        inOrder.verify(daoFalso, times(1)).atualiza(leilao1);    
        inOrder.verify(carteiroFalso, times(1)).envia(leilao1);    
    }
    
    @Test
    public void deveContinuarAExecucaoMesmoQuandoDaoFalha(){
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
            .naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
            .naData(antiga).constroi();

        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));

        /*
         * Fa�a uso do doThrow() para simular a exce��o.
         * doThrow simulamos o lan�amento de um erro, porque exemplo NullPointer ou RuntimeException em caso de erro no banco de dados 
         */
        doThrow(new RuntimeException()).when(daoFalso).atualiza(leilao1);

        EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
        EncerradorDeLeilao encerrador = 
            new EncerradorDeLeilao(daoFalso, carteiroFalso);

        encerrador.encerra();

        verify(daoFalso).atualiza(leilao2);
        verify(carteiroFalso).envia(leilao2);
    }
    
    @Test
    public void deveContinuarAExecucaoMesmoQuandoEnviadorDeEmaillFalha() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
            .naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
            .naData(antiga).constroi();

        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));

        EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
        doThrow(new RuntimeException()).when(carteiroFalso).envia(leilao1);

        /*
         * Usando
         * doThrow(new Exception()).when(carteiroFalso).envia(leilao1);
         * org.mockito.exceptions.base.MockitoException: Checked exception is invalid for this method!. Ou seja, o Mockito percebe isso e falha o teste!
         */
        
        EncerradorDeLeilao encerrador =
            new EncerradorDeLeilao(daoFalso, carteiroFalso);

        encerrador.encerra();

        verify(daoFalso).atualiza(leilao2);
        verify(carteiroFalso).envia(leilao2);
    }
    
    @Test
    public void deveDesistirSeDaoFalhaPraSempre() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);

        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
            .naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira")
            .naData(antiga).constroi();

        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));

        EnviadorDeEmail carteiroFalso = mock(EnviadorDeEmail.class);
        /*
	        Veja que, no teste passado, repetimos algumas linhas, como por exemplo:
	        Podemos escrever isso com apenas uma linha, informando ao Mockito que n�o importa qual leil�o o mock vai receber usando o m�todo any() da classe org.mockito.Matchers:
	        doThrow(new RuntimeException()).when(daoFalso).atualiza(leilao1);
	        doThrow(new RuntimeException()).when(daoFalso).atualiza(leilao2);
        */
        doThrow(new RuntimeException()).when(daoFalso).atualiza(any(Leilao.class));
        
        /*
        	A classe Matchers possui diversos m�todos que podem ser usados para especificarmos que argumentos nosso mock pode receber numa chamada de m�todo. 
        	Podemos, por exemplo, garantir que um mock vai ser chamado com uma String come�ando com "Importante:". Veja s�:
    		verify(meuMock).imprimeMensagem(startsWith("Importante:"));
        */
        
        EncerradorDeLeilao encerrador = 
            new EncerradorDeLeilao(daoFalso, carteiroFalso);

        encerrador.encerra();

        //Escreva esse teste e garanta que o carteiro nunca � invocado. Para isso, voc� pode usar times(0) ou, melhor ainda, o m�todo never() junto ao verify:
        verify(carteiroFalso, never()).envia(leilao1);
        verify(carteiroFalso, never()).envia(leilao2);
    }
    
}