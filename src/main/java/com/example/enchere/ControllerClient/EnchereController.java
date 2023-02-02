package com.example.enchere.ControllerClient;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import com.example.enchere.ModelClient.Enchere;

@RestController
@RequestMapping("/Enchere")
@CrossOrigin
public class EnchereController {
	
	@PostMapping
	public boolean insert(@RequestBody Enchere enchere) throws Exception
	{
		return new Enchere().insertion(enchere);
	}
	
	@GetMapping
	public ArrayList<Enchere> selectValide() throws Exception
	{
		return new Enchere().select_valide();
	}
	@GetMapping("/All")
	public ArrayList<Enchere> selectAll() throws Exception
	{
		return new Enchere().List();
	}
	
	@GetMapping("/recherche/{valeur}")
	public ArrayList<Enchere> recherche(@PathVariable("valeur") String valeur) throws Exception
	{
		return new Enchere().selectRecherche(valeur);
	}
	
	@GetMapping("/{id}")
	public ArrayList<Enchere> selectById(@PathVariable("id") int id) throws Exception
	{
		return new Enchere().selectById(id);
	}

	@GetMapping("/list/{id}")
	public ArrayList<Enchere> NotUtilisateur(@PathVariable("id") int id) throws Exception
	{
		return new Enchere().getNotId(id);
	}
}
