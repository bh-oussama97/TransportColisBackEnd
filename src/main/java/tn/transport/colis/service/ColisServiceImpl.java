package tn.transport.colis.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.agent.ByteBuddyAgent.AttachmentProvider.ForUserDefinedToolsJar;
import tn.esprit.spring.entity.Extra;
import tn.esprit.spring.entity.KinderGarten;
import tn.transport.colis.entity.Colis;
import tn.transport.colis.entity.User;
import tn.transport.colis.enumClass.Etat;
import tn.transport.colis.repository.IColisRepository;
import tn.transport.colis.repository.IUserRepository;

@Service
public class ColisServiceImpl implements IColisService
{

	@Autowired
	IColisRepository colisRepo;
	
	@Autowired
	IUserRepository useRepo;
	
	
	@Override
	public int  ajouterColis(Colis c) 
	{
		c.setEtatColis(Etat.nonLivre); 
		
		colisRepo.save(c);
		 
		return c.getId();
	}
	
	
	@Override
	public void affecterFournisseurColis(int idcolis, int fournissId) {
		
				Colis colis = colisRepo.findById(idcolis).get();
				User forniss = useRepo.findById(fournissId).get();
				
				colis.setFournisseur(forniss);
				
				colisRepo.save(colis);
		
	}
	
	
	@Override
	public List<Colis> listecolis() 
	{
		
		return (List<Colis>) colisRepo.findAll();
		
	}
	
	
	@Override
	public void mettreAjourColis(Date dateStart, Date dateEnd, int idcolis) 
	{
		colisRepo.updateColisJPQL(dateStart, dateEnd, idcolis);
		
	}





	

	
}
