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
	public String updateStatUser(int iduser)
	{
		User u = userRepo.findById(iduser).get();
		
		if (u!= null)
		{
			u.setState(!u.getState());
			userRepo.save(u);
			return "stat user updated successfully " ;
		}
		
		else
			
			return "error";
		
	}


	@Override
	public User findByEmail(String email) 
	{
		return userRepo.findByEmail(email);
	}


	@Override
	public User getUserById(int id) {
		return userRepo.findById(id).get();
	}


	@Override
	public void changerMotDePasse(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(User u) {
		
		User userbyid = userRepo.findById(u.getId()).get();
		
		String pwd = new BCryptPasswordEncoder().encode(u.getMotDePasse());
			
		u.setType(userbyid.getType());
		u.setMotDePasse(pwd);
		u.setDateCreation(new Date());
		
		userRepo.save(u);
		
	}


	@Override
	public void changePassWord(int id, String pwd) 
	{
		User u = userRepo.findById(id).get();

		if (u != null) {

			u.setMotDePasse(pwd);

			this.update(u);
		}
		
	}
	
	
	

}
