package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.services.interfaces.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	@Autowired
	UserService userservice;
	
	//check connectivity
	@GetMapping("/connectioncheck")
	public String checkConnection() {
		return "successful Connection";
	}
	
	//get all users
		@GetMapping("/all")
		public ResponseEntity<?> getAllUser(){
			return ResponseEntity.ok(userservice.getAllUsers());
	}
	
	//get the id from email
	@GetMapping("/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable String email){
		System.out.println("hiiii");
		try {
			return ResponseEntity.ok(userservice.getUserIdByEmail(email));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@GetMapping("./users/getUserById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		System.out.println("hiiii");
		try {
			return ResponseEntity.ok(userservice.getUserById(id));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@GetMapping("/getRoleByEmail/{email}")
	public ResponseEntity<String> getRoleByMail(@PathVariable String email){
		return new ResponseEntity<>(userservice.getRoleByEmail(email),HttpStatus.OK);
	}
	
	@GetMapping("/getIdByEmail/{email}")
	public ResponseEntity<Long> getIdByMail(@PathVariable String email){
		return new ResponseEntity<>(userservice.getIdByEmail(email),HttpStatus.OK);
	}
	
	//check whether password is correct or not
	@GetMapping("/logincheck/{email}/{password}")
	public ResponseEntity<Boolean> checkPassword(@PathVariable String email , @PathVariable String password) {
	    System.out.println("hello email: "+email+" password: "+password);	    
	    return new ResponseEntity(userservice.isPasswordCorrect(email, password),HttpStatus.OK);
	}
	
	//check whether admin exists or not 
	@GetMapping("/admin/exists")
	public ResponseEntity<Boolean> checkAdminExists(){
		System.out.println("inside checkAdminExists.");
		return new ResponseEntity(userservice.isAdminRoleExists(),HttpStatus.OK);
	}
	
	//adding an user
	@PostMapping("/add")
	public ResponseEntity<?> addNewUser(@RequestBody UserDto userdto){
		System.out.println("userdto: "+userdto);
		return ResponseEntity.ok(userservice.addUser(userdto));
	}
	
	
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDto userdto){
		try {
			return ResponseEntity.ok(userservice.updateUser(userId, userdto));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//deleting an user with its id
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId){
		try {
			return ResponseEntity.ok(userservice.deleteUser(userId));
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
