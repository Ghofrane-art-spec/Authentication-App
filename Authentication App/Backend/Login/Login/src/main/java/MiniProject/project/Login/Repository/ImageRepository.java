package MiniProject.project.Login.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import MiniProject.project.Login.Entity.Image;


public interface ImageRepository extends JpaRepository<Image, Long>{
	
	Optional<Image> findByName(String fileName);

}
