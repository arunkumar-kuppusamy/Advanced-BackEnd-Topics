package principal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class Dinamic {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//http://2min2code.com/articles/log4j_intro/custom_pattern_code
		Logger LOG = Logger.getLogger(Main.class);  
        Layout layout = new PatternLayout("%d [%t] %-5p (%F:%L) - %m%n");  
        RollingFileAppender rollingFileAppender = new RollingFileAppender();  
        rollingFileAppender.setLayout(layout);  
        rollingFileAppender.setFile("C:\\Users\\Casa\\Desktop\\log4j-1.2.17\\worker.log");    
        rollingFileAppender.activateOptions();  
        rollingFileAppender.setMaxBackupIndex(5);  
        rollingFileAppender.setMaximumFileSize(1000000);  
        
        LOG.addAppender(rollingFileAppender); 
	     
		System.out.println(LOG.getName());
		LOG.debug("Hello, my name is Homer Simpson.");
		LOG.info("Mmmmmm .... Chocolate.");
		LOG.warn("Mmm...forbidden donut.");
		LOG.error("YamYam...so delicious.");

	}
	
}
