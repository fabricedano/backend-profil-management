package com.springboot.fullstack.backendprofilmanagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.fullstack.backendprofilmanagement.exception.ResourceNotFoundException;
import com.springboot.fullstack.backendprofilmanagement.model.User;
import com.springboot.fullstack.backendprofilmanagement.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
    public List<User> getUsers() {
		List<User> list = userRepository.findAll();
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
    	User user = userRepository.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
    		@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
    	User user = userRepository.findById(userId)
    			.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    	
    	final User updateUser = userRepository.save(userDetails);
    	return ResponseEntity.ok(updateUser);
    }
    
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") long userId)
    		throws ResourceNotFoundException {
    	User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    	
    	userRepository.delete(user);
    	Map<String, Boolean> response = new HashMap<>();
    	response.put("delete", Boolean.TRUE);
    	return response;
    }
}
