
import ch.qos.logback.classic.Logger;

public class Main {

	// criando um logger com o nome de LoggerNegocio, se nao existir
	private static final Logger LOGGER = GLogFileServer.getLogger("LoggerNegocio");

	public static void main(String[] args) {

		Main main = new Main();
		main.geraLogTeste();
		
	}

	private void geraLogTeste() {
		if (LOGGER.isEnabledFor(ch.qos.logback.classic.Level.DEBUG))
		LOGGER.debug("Mmmmmm .... Chocolate.");
		LOGGER.info("Hello, my name is Homer Simpson.");
		LOGGER.warn("Mmm...forbidden donut.");
		LOGGER.trace("Mmmmmm .... Ice Cream.");
		LOGGER.error("YamYam...so delicious.");
	}

}
