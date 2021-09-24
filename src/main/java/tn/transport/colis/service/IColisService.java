package tn.transport.colis.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.transport.colis.entity.Colis;

public interface IColisService {

	
	public int ajouterColis (Colis c);
	
	public void supprimerColis(int id);
	
	public void mettreAjourColis(Date dateStart, Date dateEnd, int idcolis);
	
	public List<Colis> listecolisParFournisseur( int idfournisseur);
	
	public void mettreAjourEtatColis(int id);

	public List<Colis> listecolis();
	
	public void affecterFournisseurColis(int idcolis, int fournissId);
	
	public HashMap<String, String> affecterImage(int id, MultipartFile file);
	
	public int updateColis(Colis colis) ;
	
	public void confirmationColis (int idcolis);
	

}
