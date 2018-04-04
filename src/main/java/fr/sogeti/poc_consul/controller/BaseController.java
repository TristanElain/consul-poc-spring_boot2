package fr.sogeti.poc_consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.sogeti.poc_consul.provider.ConfigurationProvider;

@Controller
public class BaseController {

	@Autowired
	private ConfigurationProvider provider;

	@RequestMapping("/")
	public String showConfiguration(Model model) {		
		model.addAttribute("propertyMap", provider.getProperties("poc/consul/global"));
		return "config";
	}
	
	@RequestMapping("/health")
	@ResponseStatus(value = HttpStatus.OK)
	public void healthCheck() {	}
}
