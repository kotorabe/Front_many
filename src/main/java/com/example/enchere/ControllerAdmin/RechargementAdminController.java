package com.example.enchere.ControllerAdmin;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.ModelAdmin.Rechargement;
import com.example.enchere.ModelAdmin.Vue.V_Utilisateur_Rechargement;

@RestController
@CrossOrigin
@RequestMapping("/RechargementAdmin")
public class RechargementAdminController {
	
	@GetMapping("/validation/{idrechargement}")
	public boolean validation_rechargement(@PathVariable("idrechargement") int idrechargement) throws Exception
	{
		return new Rechargement().validation_insertion(idrechargement);
	}
	
	@GetMapping
	public ArrayList<V_Utilisateur_Rechargement> selectall() throws Exception
	{
		ArrayList<V_Utilisateur_Rechargement> liste  = new V_Utilisateur_Rechargement().selectAll();
		return liste;
	}
	
	@GetMapping("/{id}")
	public ArrayList<V_Utilisateur_Rechargement> selectById(@PathVariable("id") int id) throws Exception
	{
		ArrayList<V_Utilisateur_Rechargement> liste  = new V_Utilisateur_Rechargement().selectById(id);
		return liste;
	}
	
	@GetMapping("/liste_non_valide")
	public ArrayList<V_Utilisateur_Rechargement> select_non_valide() throws Exception
	{
		ArrayList<V_Utilisateur_Rechargement> liste  = new V_Utilisateur_Rechargement().select_non_valide();
		return liste;
	}
}
