package com.lucas.springbootmongo.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.springbootmongo.domain.User;
import com.lucas.springbootmongo.dto.UserDTO;
import com.lucas.springbootmongo.services.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserResource {
	

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/findAll")
	public ResponseEntity<List<UserDTO>> findAll(){		
		
		List<User> listUser = userService.findAll();
		List<UserDTO> listUserDto = listUser.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listUserDto);

	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/findById/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
		
 	
    }
	


}
