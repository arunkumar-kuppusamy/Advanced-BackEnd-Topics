package com.cxf.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

//http://localhost:8080/{contexto}/{url:pattern}/{jaxrs:server}/{path:class}/{path:method}/{params...}
//http://localhost:8080/cxf/cxf-web/cxf-server/classe-servico/funcao/JOSE MARIA
//HIERARQUIA - contexto -> cxf-web -> cxf-server  -> classe-servico  -> funcao  -> param

@Service
@Path("/classe-servico")
public class RestService {


	@GET
	@Path("/funcao/{param}")
	@Produces("text/plain; charset=UTF-8")
	public String testService(@PathParam("param") String name) {
		return "Olá "+name;
	}

}
