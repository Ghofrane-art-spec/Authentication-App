package MiniProject.project.Login.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MiniProject.project.Login.Entity.User;
import MiniProject.project.Login.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	public UserRepository repository;
	
	public UserService(UserRepository repo){
		this.repository=repo;
		}
	
	public User Login(String email) {
		Optional<User> u =repository.findByemail(email);
		if((u.isEmpty())) {
			return null;
		}else {
			return u.get();
		}
	}
	
	
	public void SaveUser(User user) {
		repository.save(user);
	}
	
	public User findUser(Long id) {
		Optional<User> user=repository.findById(id);
		return user.get();
	}

}
