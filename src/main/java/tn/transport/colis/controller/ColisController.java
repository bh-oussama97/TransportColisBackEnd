package tn.transport.colis.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tn.transport.colis.entity.Colis;
import tn.transport.colis.service.ColisServiceImpl;
import tn.transport.colis.service.UploadFileServiceImpl;

@Controller
@RequestMapping("/transportcolis")
public class ColisController {
	
	
	@Autowired
	ColisServiceImpl coliservice;
	
	@Autowired
	UploadFileServiceImpl uploadfileservice;
	
	
	@PostMapping("/ajouterColis")
	@ResponseBody	
	public int ajouterColis (@RequestBody Colis c)
	{
		
		return coliservice.ajouterColis(c);
	}
	
	
	@PutMapping("/affecterImage/{idcolis}")
	@ResponseBody
	
	public HashMap<String, String> affecterImage(@PathVariable("idcolis") int idcolis,@RequestParam("file") MultipartFile file)
	{
		HashMap<String, String> result;
		result = new HashMap<String,String>();
		
		 if (uploadfileservice.addFile(file))
		 {
		
				result = coliservice.affecterImage(idcolis, file);
				
		 }
	
		 return result;
		 
	}
	 
	
	@PutMapping("/affecterColisFournisseur/{idfour}/{idcolis}")
	@ResponseBody
	
	public void affecterColisFournisseur (@PathVariable("idfour") int idfour,@PathVariable("idcolis") int idcolis)
	
	{
		
		coliservice.affecterFournisseurColis(idcolis, idfour);
		
	}

}
