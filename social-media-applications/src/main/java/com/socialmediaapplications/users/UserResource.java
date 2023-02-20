package com.socialmediaapplications.users;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	private UserDaoService service;
	public UserResource(UserDaoService service) {
		this.service = service;
	}
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.getAll();
	}
	@GetMapping("/user/{id}")
	public User retriveUsers(@PathVariable("id") Integer id) {
		User user= service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("id:"+id);
		}
		return user;
	}
	@DeleteMapping("/user/{id}")
	public void deleteUsers(@PathVariable("id") Integer id) {
		service.deleteById(id);
	}
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		URI loaction = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(loaction ).build();
	}
}
