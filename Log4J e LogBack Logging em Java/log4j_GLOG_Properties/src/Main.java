import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class Main {

	public static void main(String[] args) {

		/*
		 * CRIANDO O ARQUIVO NO JAVA SEM O PROPERTIES
		 */
		//http://2min2code.com/articles/log4j_intro/custom_pattern_code
        Logger CLASSE = Logger.getLogger(Main.class);  
        Layout layout = new PatternLayout("%d [%t] %-5p %F:%L - %m%n");  
        RollingFileAppender rollingFileAppender = new RollingFileAppender();  
        rollingFileAppender.setLayout(layout);  
        rollingFileAppender.setFile("src\\saida\\GLOG_Teste.txt");    
        rollingFileAppender.activateOptions();  
        rollingFileAppender.setMaxBackupIndex(5);  
        rollingFileAppender.setMaximumFileSize(1000000);  
        
        CLASSE.addAppender(rollingFileAppender);
        CLASSE.info("Hello, my name is Homer Simpson.");
        rollingFileAppender.close();//close
	     
        /*
         * CRIANDO O ARQUIVO USANDO CONFIG PROPERTIES
         */
        Logger LOG = Logger.getLogger("LoggerNavegacao");  
		System.out.println(LOG.getName());
		LOG.debug("Hello, my name is Homer Simpson.");
		LOG.info("Mmmmmm .... Chocolate.");
		LOG.warn("Mmm...forbidden donut.");
		LOG.error("YamYam...so delicious.");


	}

}
