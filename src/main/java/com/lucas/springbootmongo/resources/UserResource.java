package com.lucas.springbootmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.springbootmongo.domain.User;
import com.lucas.springbootmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/findAll")
	public ResponseEntity<List<User>> findAll(){		
		
		return ResponseEntity.ok().body(userService.findAll());
	}
	


}