package ca.noogle.nooglecabackend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ca.noogle.nooglecabackend.exception.ResourceNotFoundException;
import ca.noogle.nooglecabackend.model.User;
import ca.noogle.nooglecabackend.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	
	//Retrieves all users - need to determine we need this
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	//create a new user
	// @Valid will return 400 BadRequest error if email or pw are not supplied
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) { //The @RequestBody annotation is used to bind the request body with a method parameter.
		return userRepository.save(user);
	}
	
	//get a single user
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value="id") Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
	}
	
	//update a User
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value="id") Long userId, 
			@Valid @RequestBody User userDetails) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setUserEmail(userDetails.getUserEmail());
		user.setUserPassword(userDetails.getUserPassword());
		
		User updatedUser = userRepository.save(user);
		
		return updatedUser;
	}
	

	
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }
	
	
	
}
