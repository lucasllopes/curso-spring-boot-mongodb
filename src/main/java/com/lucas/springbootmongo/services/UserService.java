package com.lucas.springbootmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.springbootmongo.domain.User;
import com.lucas.springbootmongo.dto.UserDTO;
import com.lucas.springbootmongo.repository.UserRepository;
import com.lucas.springbootmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
   public User insert(User user) {
	   return userRepository.insert(user);
	   
   }
   
   public void delete(String id) {
	   findById(id);
	   userRepository.deleteById(id);
   }
   
   public User update(User user) {
	   User newUser = findById(user.getId());
	   updateData(newUser,user);
	   return userRepository.save(newUser);
	   
   }
   
   private void updateData(User newUser, User user) {
	newUser.setName(user.getName());
	newUser.setEmail(user.getEmail());
	
}

public User fromDTO(UserDTO dto) {
	   return new User(dto);
   }
   

   
   

}
