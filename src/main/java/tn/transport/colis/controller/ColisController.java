package tn.transport.colis.controller;


import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import tn.transport.colis.entity.Colis;
import tn.transport.colis.repository.IColisRepository;
import tn.transport.colis.service.ColisServiceImpl;
import tn.transport.colis.service.SMSService;
import tn.transport.colis.service.UploadFileServiceImpl;


@Controller
@RequestMapping("/transportcolis")
public class ColisController {
	

	
	@Autowired
	ColisServiceImpl coliservice;
	
	@Autowired
	UploadFileServiceImpl uploadfileservice;
	

	@Autowired
    SMSService servicesms;
	


	 
	 @Autowired
	    private SimpMessagingTemplate webSocket;

	    private final String  TOPIC_DESTINATION = "/lesson/sms";

	 
	    @GetMapping("/confirmColis/{idcolis}")
	    
	    public void confirmationColis(@PathVariable("idcolis") int idcolis)
	    
	    {
	    	coliservice.confirmationColis(idcolis);
	    }
	    
	 
	   @PostMapping("/sendsms/{idcolis}")
	   
	    public HashMap<String, String> smsSubmit(@PathVariable("idcolis") int id)
	{
		   
		   HashMap<String, String> result;
			result = new HashMap<String,String>();
		   
			
			if ( 	servicesms.send(id) )
			{
				result.put("status", "success");
				result.put("result","colis a été envoyé avec succés" );
			}
	
	        
			else 
			{
				result.put("status", "failure");
				result.put("result","erreur dans l'envoi du sms" );
			}
	        
	       return result;
       
    }
	   
	   private String getTimeStamp() {
	       return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	    }
	
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
	
	@GetMapping("/listecolisParFournisseur/{idfournisseur}")
	@ResponseBody
	
	public List<Colis> listecolisParFournisseur(@PathVariable("idfournisseur") int id)
	{
		return coliservice.listecolisParFournisseur(id);
	}
	
	@GetMapping("/listecolis")
	@ResponseBody
	
	public List<Colis> listecolis() 
	{
		return coliservice.listecolis();
	}
	
	@DeleteMapping("/deleteMapping/{id}")

	
	public void supprimerColis(int id)
	{
		 coliservice.supprimerColis(id);
	}
	
	

}
