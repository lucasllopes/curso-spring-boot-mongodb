package com.lucas.springbootmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.springbootmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method = RequestMethod.GET,value = "/findAll")
	public ResponseEntity<List<User>> findAll(){
		
		User alex = new User("1","Alex","alex@gmail.com");
		User maria = new User("2","Maria","maria@gmail.com");
		
		List<User> listUsers = new ArrayList<>();
		listUsers.addAll(Arrays.asList(alex,maria));	
		return ResponseEntity.ok().body(listUsers);
	}
	


}
