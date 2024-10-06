package com.app.services.interfaces;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.UserDto;

public interface UserService {	
	public Long getUserIdByEmail(String email);
	 
	boolean isPasswordCorrect(String email, String password);
	 
	public boolean isAdminRoleExists();
	
	public String getRoleByEmail(String email) ;
	
	public UserDto getUserById(Long id);
	
	public Long getIdByEmail(String email);
	 
	UserDto addUser(UserDto userdto);
	
	List<UserDto>getAllUsers();
	
	ApiResponse deleteUser(Long userId);
	
	UserDto updateUser(Long userId, UserDto userdto);
}
