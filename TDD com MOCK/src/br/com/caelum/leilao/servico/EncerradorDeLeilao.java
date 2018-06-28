package br.com.caelum.leilao.servico;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.infra.dao.LeilaoDaoFalso;


public class EncerradorDeLeilao {

	private int total = 0;
	//private int encerrados;
    
    private final RepositorioDeLeiloes dao;
	//private final LeilaoDao dao;//NOSSO MOCK REAL
	private final EnviadorDeEmail carteiro;
    
    public EncerradorDeLeilao(RepositorioDeLeiloes dao, EnviadorDeEmail carteiro) {
        this.dao = dao;
        this.carteiro = carteiro;
    }

	public void encerra() {

        // DAO falso aqui!
        //LeilaoDaoFalso dao = new LeilaoDaoFalso();
        List<Leilao> todosLeiloesCorrentes = dao.correntes();

        for (Leilao leilao : todosLeiloesCorrentes) {
            if (comecouSemanaPassada(leilao)) {
                System.out.println("oi");
                leilao.encerra();
                total++;
                
                /* Se não estivéssemos "mockando" o DAO seria fácil: bastaria fazer um SELECT no banco de dados e verificar que a coluna foi alterada! Mas agora, com o mock, precisamos perguntar para ele se o método foi invocado!*/
                //se este metodo lançar uma exececao
                dao.atualiza(leilao);
                //enviar email
                carteiro.envia(leilao);
            }
        }
        
    }


	private boolean comecouSemanaPassada(Leilao leilao) {
		return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}

		return diasNoIntervalo;
	}

	public int getTotalEncerrados() {
		return total;
	}

	public Object getQuantidadeDeEncerrados() {
		// TODO Auto-generated method stub
		return null;
	}
}
