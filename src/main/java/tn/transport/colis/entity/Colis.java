package tn.transport.colis.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tn.transport.colis.enumClass.Etat;

@Entity
public class Colis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	private String nom;
	
	private String image;
	
	private String adresseDomicile;
	
	private double prix ;
	
	
	private Etat etatColis; 
	
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	private Date datefin;
	
	@ManyToOne
	private User fournisseur;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAdresseDomicile() {
		return adresseDomicile;
	}

	public void setAdresseDomicile(String adresseDomicile) {
		this.adresseDomicile = adresseDomicile;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Etat getEtatColis() {
		return etatColis;
	}

	public void setEtatColis(Etat etatColis) {
		this.etatColis = etatColis;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public User getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(User fournisseur) {
		this.fournisseur = fournisseur;
	}

	
}