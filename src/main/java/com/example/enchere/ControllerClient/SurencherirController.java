package com.example.enchere.ControllerClient;

import com.example.enchere.ModelAdmin.Vue.V_Utilisateur_Rechargement;
import org.springframework.web.bind.annotation.*;

import com.example.enchere.ModelClient.Surencherir;

import java.util.ArrayList;

@RestController
@RequestMapping("/Surencherir")
@CrossOrigin
public class SurencherirController {
	@PostMapping
	public boolean surenchere(@RequestBody Surencherir surenchere) throws Exception
	{
		return new Surencherir().insertion(surenchere);
	}

	@GetMapping("/{id}")
	public ArrayList<Surencherir> FindbyId(@PathVariable("id") int id) throws Exception
	{
		ArrayList<Surencherir> liste  = new Surencherir().selectById(id);
		return liste;
	}
}
