
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

/**
 * Classe responsável em apontar o caminho do GLog para o file server.
 * Configurar a ferramenta de log usada pela GLOG.
 * 
 * @author rcirino@scopus.com.br
 * @version 1.0
 * @since 2018-jul-10
 */
public class GLogFileServer {

	private static Logger rootLogger = (Logger) LoggerFactory.getLogger(GLogFileServer.class);
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(Logger.class);// gerar log comum

	private static String targetPathGLog = "";
	private static final String BSMB_NEGOCIO = "MOBI_NEGOCIO";
	private static final String JNDI_FILE_SERVER_GLOG = "cell/persistent/str/PathFileServer/BSMB-ComAPPremiavel";
	private final static String tipoLog = "00002";// tipo 0002 GLOG de negocio
	private static final String HEADER_ARQUIVO = "GLOGEL01\t1.7\t000000000\t" + tipoLog	+ "\t00000\t04312\t066\t00237\n";

	/**
	 * Fim da linha para tratamento de execoes tratar aqui todas as devidas.
	 * 
	 * @param logger Override do metodo para configuracao do Logger
	 * @return Logger Configurado de acordo com a especificacao do GLOG
	 */
	public static Logger getLogger(String logger) {
		try {
			if (logger.equalsIgnoreCase("LoggerNegocio")) {
				rootLogger = configuraLoggerNegocio();
			}
			//else if (logger.equalsIgnoreCase("LoggerNavegacao")) { System.out.println("Log de navegacao esta em desuso."); }
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return rootLogger;
	}

	/**
	 * @return String Retorna o caminho gravado no servidor com o caminho do fileserver.
	 * @throws Exception
	 */
	private static String obterPathFileServer() throws Exception {
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			return (String) ctx.lookup(JNDI_FILE_SERVER_GLOG);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			//TODO MOCK throw new Exception(e);
			return "src/saida/";//TODO MOCK
		}
	}

	/**
	 * @return Logger Configurado de acordo com a especificacao do GLOG
	 * @throws Exception
	 */
	private static Logger configuraLoggerNegocio() throws Exception {
		try {
			// Caminho que sera gravado o arquivo no fileServer.
			targetPathGLog = getCaminhoFileServerGLOG();
			// Level padrao GLOG eh INFO
			rootLogger.setLevel(Level.INFO);

			LoggerContext loggerContext = rootLogger.getLoggerContext();
			PatternLayoutEncoder encoder = new PatternLayoutEncoder();
			encoder.setContext(loggerContext);
			encoder.setPattern("%d{yyyy-MM-dd-HH.mm.ss.SSS000}\t%msg%n");
			encoder.start();

			FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
			appender.setFile(targetPathGLog);
			appender.setContext(loggerContext);
			appender.setEncoder(encoder);
			appender.start();// Cria o arquivo fisico em disco no file server.

			rootLogger.addAppender(appender);

			//Adiciona ao arquivo de GLOG, um cabecalho especifico.
			GLogFileServer.adicionaCabecalhoAoArquivo();
		} catch (Exception e) {
			LOGGER.error("[Erro Critico] Ocorreu um erro ao carregar o caminho do fileserver via JNDI, ou ao configurar o GLOG de Negocio.",e);
			throw new Exception(e);
		}
		return rootLogger;
	}

	/**
	 * Coloca o cabecalho no arquivo de GLOG
	 */
	private static void adicionaCabecalhoAoArquivo() throws Exception {
		try {
			// Inclui na criacao do arquivo o header do GLOG, nao cria o arquivo porque ja foi criado.
			File activeFile = new File(targetPathGLog);
			if (activeFile.exists() && activeFile.isFile() && activeFile.length() == 0) {
				FileUtils.writeStringToFile(activeFile, HEADER_ARQUIVO);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * Método pai que retorna o caminho a ser gravado o GLog
	 * 
	 * @throws Exception
	 */
	private static String getCaminhoFileServerGLOG() throws Exception {
		StringBuilder path = new StringBuilder();
		try {
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			String data = format.format(Calendar.getInstance().getTime());
			path.append(obterPathFileServer());
			path.append(File.separator);
			path.append("GLog");
			path.append(File.separator);
			path.append(BSMB_NEGOCIO);
			path.append(data);
			path.append(".log");
			path.append(File.separator);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new Exception(e);
		}
		return path.toString();
	}

}