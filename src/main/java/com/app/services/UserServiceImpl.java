package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.CustomExcp;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.UserDto;
import com.app.entities.UserEntity;
import com.app.services.interfaces.EmailSender;
import com.app.services.interfaces.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailSender mailsender;

	@Autowired
	private ModelMapper mapper;


	  
	@Override
	public UserDto addUser(UserDto userdto) {
		UserEntity user = mapper.map(userdto, UserEntity.class);
		userDao.save(user);

		String body = "Hello " + user.getFirstName() + ",\n\n"
			    + "A warm welcome to eKala, your gateway to a world of artistic brilliance! 🎨✨\n\n"
			    + "As you step into our virtual gallery, you're entering a realm where imagination knows no bounds and creativity flourishes. Our collection is a curated journey through diverse art forms, ranging from the timeless classics to the avant-garde. Each piece has been carefully selected to evoke emotion, provoke thought, and inspire creativity.\n\n"
			    + "Take your time to wander through the galleries—each corridor and corner reveals a new story, a new perspective, a new masterpiece. Whether you're drawn to the vibrant strokes of contemporary paintings, the intricate details of sculptures, or the profound simplicity of abstract art, there's something here that will resonate with your inner artist.\n\n"
			    + "We believe that art is not just something to be seen, but something to be experienced. As you explore, we invite you to immerse yourself in the emotions and ideas conveyed by each work. Feel the passion of the artists, connect with their vision, and let the art speak to you in its unique language.\n\n"
			    + "At eKala, we are more than just an art gallery—we're a community of art lovers, creators, and enthusiasts. We encourage you to share your thoughts, engage with other visitors, and even contribute your own creations to our ever-growing collection.\n\n"
			    + "So go ahead, lose yourself in the beauty, the color, and the emotion of it all. Your artistic journey begins now, and we're honored to have you with us.\n\n"
			    + "With boundless creativity,\n"
			    + "The eKala Team";

		String status = mailsender.sendEmail(user.getEmail(),"User Registration Successful",body);
		System.out.println(status);
		return mapper.map(user, UserDto.class);
	}

	@Override
	public boolean isPasswordCorrect(String email, String password) {
	    UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new CustomExcp("User with email " + email + " not found"));
	    return password.equals(user.getPassword()); // Directly compare plain-text passwords
	}
	
	@Override
	public String getRoleByEmail(String email) {
	    UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new CustomExcp("User with email " + email + " not found"));
	    return user.getRole(); // Directly compare plain-text passwords
	}
	
	@Override
	public Long getIdByEmail(String email) {
	    UserEntity user = userDao.findByEmail(email).orElseThrow(() -> new CustomExcp("User with email " + email + " not found"));
	    return user.getId(); // Directly compare plain-text passwords
	}
	
	public UserDto getUserById(Long id) {
	    UserEntity user = userDao.findById(id).orElseThrow(() -> new CustomExcp("User with id " + id + " not found"));		
	    return mapper.map(user, UserDto.class);
	}
	
	
	//here write the code to return true if one admin role is exist otherwise false
    @Override
    public boolean isAdminRoleExists() {
        // Check if there's at least one user with admin role
        return userDao.findAll().stream()
                .anyMatch(user -> user.getRole().contains("admin"));
    }
	
	// create list of users using collectors helper class
	@Override
	public List<UserDto> getAllUsers() {
		return userDao.findAll().stream().map(e -> mapper.map(e, UserDto.class)).collect(Collectors.toList());
	}
	
	// Method to get user ID by email
    public Long getUserIdByEmail(String email) {
        UserEntity user = userDao.findByEmail(email)
        		.orElseThrow(() -> new CustomExcp("User with email " + email + " not found"));
        System.out.println(user+" \n\n"+user.getId());
        return user.getId();
    }	
	
	@Override
	public ApiResponse deleteUser(Long userId) {
//		User user=userDao.findById(userId).orElseThrow(()->new CustomExcp("Id not found"));
		UserEntity user = userDao.findById(userId).orElseThrow();
		userDao.delete(user);
		return new ApiResponse("User details with Id" + user.getId() + "deleted successfully");
	}

	@Override
	public UserDto updateUser(Long userId, UserDto userdto) {
		UserEntity user = userDao.findById(userId).orElseThrow();
		mapper.map(userdto, user);
		userDao.save(user);
		return mapper.map(user, UserDto.class);
	}
}
