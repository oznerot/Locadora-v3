package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.service.spec.ICompanyService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private ICompanyService service;

	@GetMapping("")
	public String list(@RequestParam(value = "city", required = false) String city, ModelMap model) {
		if (city == null || city == "") {
			model.addAttribute("companies", service.findAll());
		} else {
			model.addAttribute("companies", service.findByCity(city));
		}
		return "/index";
	}
}