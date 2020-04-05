package com.lucas.springbootmongo.resources;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.springbootmongo.domain.Post;
import com.lucas.springbootmongo.resources.util.URL;
import com.lucas.springbootmongo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	

	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/findById/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
		
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);		
 	
    }
	
	@RequestMapping(method = RequestMethod.GET,value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text){ // o value 'text' e referente ao nome do atributo e nao ao tipo
		text = URL.decodeParam(text); // decodificando o parametro
		List<Post> listPosts = postService.findByTitle(text);

		return ResponseEntity.ok().body(listPosts);		
 	
    }
	
	@RequestMapping(method = RequestMethod.GET,value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
    		@RequestParam(value = "text",defaultValue = "") String text,
    		@RequestParam(value = "minDate",defaultValue = "") String minDate,
    		@RequestParam(value = "maxDate",defaultValue = "") String maxDate){ 
		text = URL.decodeParam(text); // decodificando o parametro
		Date min = URL.converteDate(minDate, new Date(0L));
		Date max = URL.converteDate(maxDate, new Date());
		List<Post> listPosts = postService.fullSearch(text, min, max);

		return ResponseEntity.ok().body(listPosts);		
 	
    }
}
