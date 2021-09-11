package tn.transport.colis.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	public void affecterFournisseurColis(int idcolis, int fournissId) 
	{
		
				Colis colis = colisRepo.findById(idcolis).get();
				User forniss = useRepo.findById(fournissId).get();
				
				colis.setFournisseur(forniss);
				
				colisRepo.save(colis);
		
	}
	
	
	
	
	
	@Override
	public void mettreAjourColis(Date dateStart, Date dateEnd, int idcolis) 
	{
		colisRepo.updateColisJPQL(dateStart, dateEnd, idcolis);
		
	}


	@Override
	public List<Colis> listecolisParFournisseur (int idfournisseur) 
	{
		
		List<Colis> listecolis = (List<Colis>) colisRepo.findAll();
		
		return listecolis.stream().filter(c -> c.getFournisseur().getId() == idfournisseur).collect(Collectors.toList());
		
	}


	@Override
	public List<Colis> listecolis() 
	{
		return (List<Colis>) colisRepo.findAll();
	}


	@Override
	public void supprimerColis(int id) 
	{
		Colis c = colisRepo.findById(id).get();
		
		 colisRepo.delete(c);
	}





	
}
