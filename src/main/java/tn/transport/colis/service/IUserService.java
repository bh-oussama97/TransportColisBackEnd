package tn.transport.colis.service;

import tn.transport.colis.entity.User;

public interface IUserService {

	
	public void add(User u);
	
	public User findByEmail(String email);
}
