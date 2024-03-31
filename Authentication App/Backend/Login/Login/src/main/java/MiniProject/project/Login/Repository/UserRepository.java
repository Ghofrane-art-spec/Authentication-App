package MiniProject.project.Login.Repository;


import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MiniProject.project.Login.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByemail(String email);
	Optional<User> findBypassword(String password);
	
	

}
