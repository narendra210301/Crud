package com.crud.operations.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.operations.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{

}
