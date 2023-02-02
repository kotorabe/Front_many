package com.example.enchere.ControllerAdmin;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.ModelAdmin.Vue.V_Enchere_Solde;

@RestController
@CrossOrigin
@RequestMapping("/Solde_enchere")
public class Solde_EnchereController {
	
	@GetMapping
	public ArrayList<V_Enchere_Solde> selectall() throws Exception
	{
		ArrayList<V_Enchere_Solde> liste = new V_Enchere_Solde().selectall();
		liste.get(0);
		System.out.println(V_Enchere_Solde.getSolde());
		return liste;
	}
}
