package tn.transport.colis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.transport.colis.entity.User;
import tn.transport.colis.service.IUserService;

@RestController
@RequestMapping("/transportcolis")
public class UserController {

	@Autowired
	IUserService userservice;
	
	@PostMapping("/Register")
	@ResponseBody
	public void addUser(@RequestBody User u)
	{
		userservice.add(u);
	}
	
	

}
