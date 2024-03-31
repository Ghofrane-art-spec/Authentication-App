package MiniProject.project.Login.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import MiniProject.project.Login.Entity.Image;
import MiniProject.project.Login.Service.ImageService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {
	@Autowired
	private ImageService service;
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		Image uploadImage = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName){
		byte[] images=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.body(images);
		}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> DeleteImage(@PathVariable("id") Long id) throws IOException {
		service.DeleteImage(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
