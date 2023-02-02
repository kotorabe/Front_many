package com.example.enchere.ControllerAdmin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.ModelAdmin.Admin_User;

@RestController
@RequestMapping("/Admin")
@CrossOrigin

public class UserAdminController {
	@PostMapping("/LoginAdmin")
	public Admin_User login(@RequestBody Admin_User user) throws Exception
	{
		Admin_User login = new Admin_User().login(user);
		return login;
	}
}
