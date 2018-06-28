package br.com.caelum.leilao.servico;

import java.util.Calendar;

/**
 * File: RelogioDoSistema.java
 * 
 * @author Rodrigo Cirino
 * @since Jun 28, 2018 12:11:33 AM
 */
public class RelogioDoSistema implements Relogio {
	public Calendar hoje() {
		return Calendar.getInstance();
	}
}