package com.example.enchere.ControllerClient;

import org.springframework.web.bind.annotation.*;

import com.example.enchere.ModelAdmin.Rechargement;

@RestController
@RequestMapping("/Rechargement")
@CrossOrigin
public class RechargementClient {
	
	@PostMapping
	public boolean insertion(@RequestBody Rechargement recharge) throws Exception
	{
		boolean rech = new Rechargement().create(recharge);
		return rech;
	}
}
