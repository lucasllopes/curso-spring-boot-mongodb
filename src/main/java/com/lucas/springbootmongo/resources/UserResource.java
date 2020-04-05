package com.lucas.springbootmongo.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucas.springbootmongo.domain.Post;
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
	
	@RequestMapping(method = RequestMethod.POST)
     public ResponseEntity<Void> insert(@RequestBody UserDTO userDto){
		User user = userService.fromDTO(userDto);
		user = userService.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/findById/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();

		
	}
	

	@RequestMapping(method = RequestMethod.DELETE,value = "/deleteById/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){		
		userService.delete(id);
		return ResponseEntity.noContent().build();
 	
    }
	
	@RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDto,@PathVariable String id){
		User user = userService.fromDTO(userDto);
		user.setId(id);
		userService.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/findpostusers/{id}")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user.getPosts());		
 	
    }
	


}
