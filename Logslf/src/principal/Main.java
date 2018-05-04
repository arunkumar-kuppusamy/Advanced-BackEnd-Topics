package principal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * REGRAS
 * O arquivo tem que se chamar log4j.properties
 * O arquivo tem que ser colocado de modo que vá parar na WEB-INF/classes
 * 
 * 
 */


public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Main() { super(); }

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println(LOG.getName());
		LOG.debug("Hello, my name is Homer Simpson.");
		LOG.info("Mmmmmm .... Chocolate.");
		LOG.warn("Mmm...forbidden donut.");
		LOG.error("YamYam...so delicious.");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
