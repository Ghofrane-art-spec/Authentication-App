package MiniProject.project.Login.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import MiniProject.project.Login.Entity.Image;
import MiniProject.project.Login.Entity.User;
import MiniProject.project.Login.Service.ImageService;
import MiniProject.project.Login.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService service;
	@Autowired
	private ImageService imageService;
	
	
	@GetMapping("/login/{email}/{pwd}")
	public ResponseEntity<User> Login(@PathVariable("email")String email,@PathVariable("pwd")String pwd) throws IOException {
		User user = service.Login(email);
		if(user==null) {
			return null;
		}else if(user.getPassword().equals(pwd)) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else {
			return null;
		}
	}
	
	
	@GetMapping("/register/{email}/{pwd}/{name}/{surname}/{phone}/{bio}")
	public ResponseEntity<User> Register(@PathVariable("email")String email,@PathVariable("pwd")String pwd,
			@PathVariable("name")String name,@PathVariable("surname")String surname,
			@PathVariable("phone")Long phone,@PathVariable("bio")String bio) throws IOException {
		User test =service.Login(email);
		if(test!=null) {
			return null;
		}else {
			User user = new User(email,pwd,name,surname,bio,phone,null);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		
		
	}
	
	
	@PostMapping("/register/{imageName}")
	public ResponseEntity<User> SaveUser(@RequestBody User user,
			@PathVariable("imageName")String Imagename) throws IOException {
		service.SaveUser(user);
		Image image=imageService.findImage(Imagename);
		image.setUser(user);
		imageService.saveImage(image);
		user.setImage(image);
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
		
	}

	@GetMapping("/verify/{id}")
	public ResponseEntity<Image> verify(@PathVariable("id")Long id) throws IOException {
		
		User user = service.findUser(id);
		Image image=user.getImage();
		return new ResponseEntity<>(image, HttpStatus.OK);
		
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<User> EditUser(@PathVariable("id")Long id,@RequestBody User user) throws IOException {
		User oldUser = service.findUser(id);
		oldUser.setBio(user.getBio());
		oldUser.setEmail(user.getEmail());
		oldUser.setName(user.getName());
		oldUser.setSurname(user.getSurname());
		oldUser.setPassword(user.getPassword());
		oldUser.setPhoneNumber(user.getPhoneNumber());
		service.SaveUser(oldUser);
		return new ResponseEntity<User>(oldUser,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/editImage/{imageName}")
	public ResponseEntity<User> EditUserImage(@PathVariable("imageName")String imageName,@RequestBody User user) throws IOException {
		Image image=imageService.findImage(imageName);
		image.setUser(user);
		imageService.saveImage(image);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	

}
