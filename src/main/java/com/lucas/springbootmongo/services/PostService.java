package com.lucas.springbootmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.springbootmongo.domain.Post;
import com.lucas.springbootmongo.repository.PostRepository;
import com.lucas.springbootmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		
		// return postRepository.findByTitleContainingIgnoreCase(text);
		return postRepository.searchTitle(text);
		
	}
	
	public List<Post> fullSearch(String text, Date min, Date max){
		max = new Date(max.getTime() +24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, min, max);
		
	}
	

}
