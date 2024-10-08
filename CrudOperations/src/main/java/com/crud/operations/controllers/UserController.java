package com.crud.operations.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.operations.entities.User;


@RestController
public class UserController {
	

    @Autowired
    private com.crud.operations.services.UserService userService;

    // Create User  uri:http://localhost:8080/create
    @PostMapping("/create")

    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userd = userService.saveUser(user);
        return new ResponseEntity<User>(userd,HttpStatus.CREATED);
    }

    // Get All Users uri:http://localhost:8080/get
    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<User>>(allUsers,HttpStatus.OK);
    }

   // Get User by ID uri:http://localhost:8080/get/{id}
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
         Optional<User> userById = userService.getUserById(id);
         return new ResponseEntity(userById,HttpStatus.OK);
         
    }

    // Update User uri:http://localhost:8080/update/{id}
    @PutMapping ("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.getUserById(id).orElseThrow();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        User saveUser = userService.saveUser(user);
        return new ResponseEntity(saveUser,HttpStatus.OK);
        
    }

   // Delete User uri:http://localhost:8080/delete/21
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
