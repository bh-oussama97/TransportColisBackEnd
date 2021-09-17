package tn.transport.colis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import tn.transport.colis.entity.Colis;
import tn.transport.colis.enumClass.Role;
import tn.transport.colis.repository.IColisRepository;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class SMSService {

	
	
	 @Autowired
	 IColisRepository colisrepo;
	
	private final String ACCOUNT_SID ="AC74fb0337be462f1c981de78155d26686";

    private final String AUTH_TOKEN = "6c30e79f0cc775850e594e4072f13c8d";

    private final String FROM_NUMBER = " +12017305168";
    
   

    public boolean send(int id) {
    	
    	
    	Colis colis = colisrepo.findById(id).orElse(null);
    	
    	
    	if (colis.getFournisseur().getType().toString().equals(Role.livreur.toString()))
    	{
    		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber("+216"+colis.getFournisseur().getNumeroTelephone()), new PhoneNumber(FROM_NUMBER),"Un colis est prêt à collecter :"  + colis.getNom() + ",prix" + colis.getPrix())
                    .create();
            
            return true;
    	}
    	
        else return false;
    }

  
}
