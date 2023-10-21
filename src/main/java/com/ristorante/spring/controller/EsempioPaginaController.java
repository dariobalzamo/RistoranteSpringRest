package com.ristorante.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EsempioPaginaController {

	@RequestMapping("/pagina")
	public String mostraPagina(Model model) {
		model.addAttribute("messaggio", "Ciao Dario");
		return "paginaWelcome";
	}
}
