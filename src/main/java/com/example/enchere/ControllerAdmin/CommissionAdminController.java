package com.example.enchere.ControllerAdmin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.ModelAdmin.Commission;

@RestController
@CrossOrigin
@RequestMapping("/CommissionAdmin")
public class CommissionAdminController {
	@PostMapping
	public boolean insertion(@RequestBody Commission commission) throws Exception
	{
		boolean com = new Commission().create(commission);
		return com;
	}
}
