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
import com.springboot.fullstack.backendprofilmanagement.model.TypeUser;
import com.springboot.fullstack.backendprofilmanagement.model.User;
import com.springboot.fullstack.backendprofilmanagement.repository.TypeUserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1")
public class TypeUserController {

	@Autowired
	private TypeUserRepository typeUserRepository;
	
	@GetMapping("/typesuser")
	public List<TypeUser> getAllTypesUser() {
		return typeUserRepository.findAll();
	}
	
	@PostMapping("/typesuser")
    void addTypeUser(@RequestBody TypeUser typeUser) {
        typeUserRepository.save(typeUser);
    }
	
	@GetMapping("/typesuser/{id}")
    public ResponseEntity<TypeUser> getEmployeeById(@PathVariable(value = "id") Long typeUserId)
        throws ResourceNotFoundException {
		TypeUser typeUser = typeUserRepository.findById(typeUserId)
          .orElseThrow(() -> new ResourceNotFoundException("TypeUser not found for this id :: " + typeUserId));
        return ResponseEntity.ok().body(typeUser);
    }
	
	@PutMapping("/typesuser/{id}")
    public ResponseEntity<TypeUser> updateTypeUser(@PathVariable(value = "id") Long typeUserId,
    		@Valid @RequestBody TypeUser typeUserDetails) throws ResourceNotFoundException {
		TypeUser typeUser = typeUserRepository.findById(typeUserId)
    			.orElseThrow(() -> new ResourceNotFoundException("TypeUser not found for this id :: " + typeUserId));
    	
    	final TypeUser updateTypeUser = typeUserRepository.save(typeUserDetails);
    	return ResponseEntity.ok(updateTypeUser);
    }
    
    @DeleteMapping("/typesuser/{id}")
    public Map<String, Boolean> deleteTypeUser(@PathVariable(value = "id") long typeUserId)
    		throws ResourceNotFoundException {
    	TypeUser typeUser = typeUserRepository.findById(typeUserId).orElseThrow(() -> new ResourceNotFoundException("TypeUser not found for this id :: " + typeUserId));
    	
    	typeUserRepository.delete(typeUser);
    	Map<String, Boolean> response = new HashMap<>();
    	response.put("delete", Boolean.TRUE);
    	return response;
    }
}
