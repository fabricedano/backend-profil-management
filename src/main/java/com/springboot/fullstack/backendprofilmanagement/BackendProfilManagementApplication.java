package com.springboot.fullstack.backendprofilmanagement;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.fullstack.backendprofilmanagement.model.TypeUser;
import com.springboot.fullstack.backendprofilmanagement.model.User;
import com.springboot.fullstack.backendprofilmanagement.repository.TypeUserRepository;
import com.springboot.fullstack.backendprofilmanagement.repository.UserRepository;

@SpringBootApplication
public class BackendProfilManagementApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(BackendProfilManagementApplication.class, args);
	}
	
	/*@Bean
    CommandLineRunner init(UserRepository userRepository, TypeUserRepository typerUserReposity) {
        return args -> {
            Stream.of("Paul").forEach(name -> {
                TypeUser typeUser = new TypeUser("admin");

                User user = new User(name, "Don" ,name.toLowerCase() + "@domain.com");
                
                user.setTypeUser(typeUser);
                typeUser.setUser(user);
                
               userRepository.save(user);
            });
            userRepository.findAll().forEach(e -> System.out.println(e.toString()));
        };
    }*/

}
