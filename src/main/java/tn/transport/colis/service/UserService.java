package tn.transport.colis.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.transport.colis.entity.User;
import tn.transport.colis.repository.IUserRepository;

@Service
public class UserService implements IUserService{

	
	@Autowired
	IUserRepository userRepo;
	
	
	@Override
	public void add(User u) {
		
		u.setMotDePasse(new BCryptPasswordEncoder().encode(u.getMotDePasse()));
		u.setDateCreation(new Date());
		userRepo.save(u);
		
	}


	@Override
	public User findByEmail(String email) 
	{
		return userRepo.findByEmail(email);
	}

}
