package com.example.enchere.ControllerClient;

import org.springframework.web.bind.annotation.*;

import com.example.enchere.ModelClient.Utilisateur;
import com.example.enchere.Util.Token;

@RestController
@RequestMapping("/Utilisateur")
@CrossOrigin
public class UtilisateurController {
	@PostMapping("/login")
	public Token login(@RequestBody Utilisateur user) throws Exception
	{
		Token token = new Utilisateur().login(user);
		return token;
	}
	@GetMapping("/logToken")
	public Token loginToken(@RequestBody Utilisateur user) throws Exception
	{
		Token token = new Utilisateur().loginTok(user);
		return token;
	}
	
	@PostMapping
	public boolean incription(@RequestBody Utilisateur user) throws Exception
	{
		boolean bool = new Utilisateur().inscription(user);
		return bool;
	}
	
	@GetMapping("/solde/{token}")
	public float solde_utilisateur(@PathVariable("token") String token) throws Exception
	{
		return new Utilisateur().solde(token);
	}
}
