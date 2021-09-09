package tn.transport.colis.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.transport.colis.entity.User;
import tn.transport.colis.repository.IUserRepository;


@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	IUserRepository userR;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userR.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Email " + username + " not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getMotDePasse(),
				getGrantedAuthority(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthority(User user) {

		Collection<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(user.getType().toString()));
		

		/*
		 * if (user.getRole().toString().equalsIgnoreCase("admin")) {
		 * authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); }
		 */
		// authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}


}
