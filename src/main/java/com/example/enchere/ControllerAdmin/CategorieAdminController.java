package com.example.enchere.ControllerAdmin;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.ModelAdmin.Categorie;

@RestController
@CrossOrigin
@RequestMapping("/CategorieAdmin")
public class CategorieAdminController {
	
	@GetMapping
	public ArrayList<Categorie> selectAll() throws Exception
	{
		ArrayList<Categorie> liste = new Categorie().selectall();
		return liste;
	}
	
	@PostMapping
	public boolean insertion(@RequestBody Categorie categorie) throws Exception
	{
		boolean cat = new Categorie().insert(categorie);
		return cat;
	}
}
