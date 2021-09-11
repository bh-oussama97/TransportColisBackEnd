package tn.transport.colis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.transport.colis.entity.Colis;
import tn.transport.colis.service.IColisService;

@Controller
@RequestMapping("/transportcolis")
public class ColisController {
	
	
	@Autowired
	IColisService coliservice;
	
	
	@PostMapping("/ajouterColis")
	@ResponseBody	
	public void ajouterColis (@RequestBody Colis c)
	{
		 coliservice.ajouterColis(c);
	}
	
	
	

}
