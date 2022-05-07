package com.springboot.fullstack.backendprofilmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fullstack.backendprofilmanagement.model.TypeUser;

@Repository
public interface TypeUserRepository extends JpaRepository<TypeUser, Long> {

}