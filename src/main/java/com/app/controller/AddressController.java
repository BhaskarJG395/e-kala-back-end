package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.UserDao;
import com.app.dto.AddressRespDto;
import com.app.dto.request.AddressReqDto;
import com.app.entities.UserEntity;
import com.app.services.interfaces.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addService;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping
	public ResponseEntity<?> getAllAddress(){
		List<AddressRespDto> list=addService.getAllAddress();
		if(list==null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No address register yet...!");
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/add/{userId}")
	public ResponseEntity<?> addAddress(@PathVariable Long userId, @RequestBody AddressReqDto addressdto){
		System.out.println("userId: "+userId+" address__: "+addressdto.toString());
		return ResponseEntity.ok(addService.addAddress(userId,addressdto));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAddressByUser(@PathVariable Long userId) {
		AddressRespDto address=addService.getAddressByUser(userId);
		if(address==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No address found...!");
		}
		
		return ResponseEntity.ok(address);
	}
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> UpdateAddress(@PathVariable Long userId,@RequestBody AddressReqDto dto){
	//	AddressReqDto address=addService.addAddress(userId,dto);
		//if(address==null)
		//	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No address found..!");
		return ResponseEntity.ok(addService.updateAddress(userId, dto));			
	}
	
}
