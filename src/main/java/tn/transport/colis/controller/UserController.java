package tn.transport.colis.controller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.transport.colis.SpringSecurity.JwtTokenProvider;
import tn.transport.colis.entity.User;
import tn.transport.colis.repository.IUserRepository;
import tn.transport.colis.service.IUserService;

@RestController
@RequestMapping("/transportcolis")
public class UserController {

	@Autowired
	IUserService userservice;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	
	private IUserRepository userRepo;
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/Register")
	@ResponseBody
	public User addUser(@RequestBody User u)
	{
		userservice.add(u);
		
		return u;
	}
	
	
	
	@GetMapping("/getUserById/{iduser}")
	@ResponseBody
	
	public User getUserByid(@PathVariable("iduser") int iduser)
	{
		
		return userservice.getUserById(iduser);
	}


	
	@GetMapping("/getMailUser/{prenom}")
	public String getMail (@PathVariable("prenom") String prenom)
	{
		return userservice.findEmailByUsername(prenom);
	}
	
	
	
	@PutMapping("/updateStateUser/{iduser}")
	
	
	public String updateStatUser(@PathVariable("iduser") int iduser)
	{
		return userservice.updateStatUser(iduser);
	}
	
	
	
	
	@PostMapping("/Login")
	@ResponseBody
	public ResponseEntity<String> authenticate(@RequestBody User user) {

		log.info("UserResourceImpl : authenticate");
		JSONObject jsonObject = new JSONObject();
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getMotDePasse()));
			
			if (authentication.isAuthenticated()) {
				String email = user.getEmail();
					
				User userauthenticated = userservice.findByEmail(user.getEmail());
				
			//	String username = userauthenticated.getNom()+" " +userauthenticated.getPrenom();
			//	jsonObject.put("username", user);
				
				jsonObject.put("id",userauthenticated.getId());
				jsonObject.put("nom",userauthenticated.getNom());
				jsonObject.put("prenom",userauthenticated.getPrenom());
				jsonObject.put("adresse",userauthenticated.getAdresse());
				jsonObject.put("email",userauthenticated.getEmail());
				jsonObject.put("numeroTelephone",userauthenticated.getNumeroTelephone());
				jsonObject.put("motDePasse",userauthenticated.getMotDePasse());
				jsonObject.put("state",userauthenticated.getState());			
				jsonObject.put("name", authentication.getName());	
				
				jsonObject.put("authorities", authentication.getAuthorities());
				jsonObject.put("token",tokenProvider.createToken(email, userRepo.findByEmail(email).getType().toString()));
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} catch (JSONException e) {
			try {
				jsonObject.put("exception", e.getMessage());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
		}
		return null;
	}
	

}
