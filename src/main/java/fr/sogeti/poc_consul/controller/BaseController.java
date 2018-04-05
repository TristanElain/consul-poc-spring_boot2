package fr.sogeti.poc_consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.sogeti.poc_consul.manager.HealthManager;
import fr.sogeti.poc_consul.provider.ConfigurationProvider;

@Controller
public class BaseController {

	@Autowired
	private ConfigurationProvider provider;
	
	@Autowired
	private HealthManager healthManager;

	@RequestMapping("/configuration")
	public String showConfiguration(Model model) {		
		model.addAttribute("propertyMap", provider.getProperties("poc/consul/global"));
		return "config";
	}
	
	@RequestMapping("/health")
	public ResponseEntity healthCheck() {
		if(healthManager.isHealthy()) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showHealthForm(Model model) {
		model.addAttribute("health", healthManager.isHealthy());
		return "healthForm";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String changeHealth(@RequestParam("health") boolean health, Model model) {
		healthManager.setHealth(health);
		return showHealthForm(model);
	}
}
