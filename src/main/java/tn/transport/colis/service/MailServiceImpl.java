package tn.transport.colis.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import tn.transport.colis.MailConfig.EmailRequestDTO;
import tn.transport.colis.repository.IUserRepository;


@Service
public class MailServiceImpl implements IMailService{
	
	
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private Configuration configuration;
	
	@Autowired
	private IUserRepository userrepo;
	
	private static Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

	@Override
	public void sendSimpleMail( String emailfournisseur,String objet,String contenu) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();
		
		log.info("username of connected user :"+username);
		
		String email = userrepo.findEmailUserByName(username);
		
		// Create a Simple MailMessage.
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(email);
		message.setTo(emailfournisseur);
		message.setSubject(objet);
		message.setText(contenu);

		// Send Message!
		this.emailSender.send(message);
		
		
	}

	
	
}
