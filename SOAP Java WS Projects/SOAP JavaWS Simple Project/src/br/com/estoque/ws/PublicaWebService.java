package br.com.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublicaWebService {

	public static void main(String[] args) {

		EstoqueWS service = new EstoqueWS();
		String url = "http://localhost:8080/estoquews";//acesse com http://localhost:8080/estoquews?wsdl

		Endpoint.publish(url, service);//Publica o @Webservice em uma url acessivel

	}

}
