package tn.transport.colis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


import tn.transport.colis.enumClass.Role;

@Entity
public class User 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	
	private String nom;
	private String prenom ;
	private String adresse;
	private String email;
	private String  numeroTelephone;
	private String motDePasse;
	
	private Boolean state;
	
	
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	@Enumerated(EnumType.STRING)
	private Role type;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	public List<Colis> getListecolis() {
		return listecolis;
	}
	public void setListecolis(List<Colis> listecolis) {
		this.listecolis = listecolis;
	}
	@OneToMany(mappedBy = "fournisseur")
	@JsonIgnore
	private List<Colis> listecolis = new ArrayList<Colis>();
	
	
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumeroTelephone() {
		return numeroTelephone;
	}
	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public Role getType() {
		return type;
	}
	public void setType(Role type) {
		this.type = type;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
}
