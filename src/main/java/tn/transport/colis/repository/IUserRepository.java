package tn.transport.colis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.transport.colis.entity.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer>{

	 
	@Query("select u from User u where u.email=:email")
	public User findByEmail(@Param("email") String email);
	
}
