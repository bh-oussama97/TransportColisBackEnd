package tn.transport.colis.service;

import tn.transport.colis.entity.User;

public interface IUserService {

	
	public void add(User u);
	
	public User findByEmail(String email);
	
	public User getUserById(int id);
	
	public void changerMotDePasse(int id);
	
	public void update(User u);
	
	public void changePassWord(int id, String pwd);
	
	public String updateStatUser(int iduser);
}
