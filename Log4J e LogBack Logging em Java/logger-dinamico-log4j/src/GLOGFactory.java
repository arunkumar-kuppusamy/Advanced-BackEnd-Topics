

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class GLOGFactory {

	//criando um logger com o nome de LoggerNegocio, se nao existir
	private static Logger logJ = Logger.getLogger(GLOGFactory.class);
	
	//Local de saida do arquivo
	private static final String targetPathGLog = "src/saida";//targetPathGLog+"/MOBI_NEGOCIO";
	//private static final String JNDI_FILE_SERVER_GLOG = "cell/persistent/str/PathFileServer/BSMB-ComAPPremiavel";	
	
	public static Logger getLogger(String logger) {

		logJ = configLoggerNegocio();
		/*switch (logger) {
			case "LoggerNegocio":
			break;
			case "LoggerNavegacao":
				System.out.println("Log de navegacao esta em desuso.");
			break;
			default:
				break;
		}*/
		
		return logJ; 
		
	}

	private static Logger configLoggerNegocio() {
		//http://2min2code.com/articles/log4j_intro/custom_pattern_code
		
		logJ.setLevel(Level.ALL);
		logJ.setAdditivity(true);
		
		RollingFileAppender rollingFileAppender = new RollingFileAppender();  
		Layout layout = new PatternLayout("%d [%t] %-5p (%F:%L) - %m%n");
		rollingFileAppender.setLayout(layout);
		
		DateFormat format = new SimpleDateFormat("yyyyMMdd");//MOBI_NEGOCIO20180711
		String data = format.format(Calendar.getInstance().getTime());
		
		rollingFileAppender.setFile(targetPathGLog+"/MOBI_NEGOCIO_"+data+"log");    
		rollingFileAppender.activateOptions();
		
		logJ.addAppender(rollingFileAppender);
		return logJ;
	}

}