package tn.transport.colis.service;

import java.util.Date;
import java.util.List;

import tn.transport.colis.entity.Colis;

public interface IColisService {

	
	public int ajouterColis (Colis c);
	
	public void supprimerColis(int id);
	
	public void mettreAjourColis(Date dateStart, Date dateEnd, int idcolis);
	
	public List<Colis> listecolisParFournisseur( int idfournisseur);
	
	
	public List<Colis> listecolis();
	
	
	public void affecterFournisseurColis(int idcolis, int fournissId);
	
}
