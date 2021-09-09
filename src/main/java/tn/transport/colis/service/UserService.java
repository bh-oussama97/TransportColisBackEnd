package tn.transport.colis.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.transport.colis.entity.User;
import tn.transport.colis.repository.IUserRepository;

@Service
public class UserService implements IUserService{

	
	@Autowired
	IUserRepository userRepo;
	
	
	@Override
	public void add(User u) {
		
		u.setDateCreation(new Date());
		userRepo.save(u);
		
	}

}
