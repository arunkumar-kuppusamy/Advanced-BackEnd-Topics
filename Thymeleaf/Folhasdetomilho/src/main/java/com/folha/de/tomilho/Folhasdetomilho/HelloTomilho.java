package com.folha.de.tomilho.Folhasdetomilho;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloTomilho {

	@RequestMapping
	public String hello(HttpServletRequest request, Model model) {
		
		model.addAttribute("name", "Folhas de Tomilho.");
		
		return "hello";//nome do arquivo sem a extensao
	}

}
