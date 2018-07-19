

import org.apache.log4j.Logger;

public class Principal {

	//criando um logger com o nome de LoggerNegocio, se nao existir
	private static final Logger logJ = GLOGFactory.getLogger("LoggerNegocio");
	
	public static void main(String[] args) {
		
		Principal p = new Principal();
		p.prepara();
	}

	private void prepara() {
		
	     encaminhaLog(logJ);
	}

	private void encaminhaLog(Logger logJ) {
		//if(logJ.isEnabledFor(Level.INFO)) {
		logJ.debug("Hello, my name is Homer Simpson.");
		logJ.info("Mmmmmm .... Chocolate.");
		logJ.warn("Mmm...forbidden donut.");
		logJ.error("YamYam...so delicious.");
	}

}


/*       <appender name="log-negocio" class="br.com.bradseg.mobi.bradescoseguroscelular.mobile.log.MobileSegurosRollingFileAppender">
             <file>${targetPathGLog}/MOBI_NEGOCIO</file>
             <encoder>
                    <pattern>%d{yyyy-MM-dd-HH.mm.ss.SSS000}\t%msg%n</pattern>
             </encoder>
             <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                    <fileNamePattern>${targetPathGLog}/MOBI_NEGOCIO%d{yyyyMMdd}.log</fileNamePattern>
             </rollingPolicy>
             <tipoLog>00002</tipoLog>
       </appender>
*/