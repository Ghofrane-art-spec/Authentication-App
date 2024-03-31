package MiniProject.project.Login.Service;

import java.io.IOException;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import MiniProject.project.Login.Entity.Image;
import MiniProject.project.Login.ImageUtil.ImageUtils;
import MiniProject.project.Login.Repository.ImageRepository;


@Service
public class ImageService {
	
	@Autowired
	private ImageRepository repository;
	
	ImageService(ImageRepository repo){
		this.repository=repo;
	}
	
	public Image uploadImage(MultipartFile file) throws IOException {
		Image image =new Image(file.getOriginalFilename(),
				file.getContentType(),
				ImageUtils.compressImage(file.getBytes()));
	    if (image != null) {
	    	repository.save(image);
	        return image;
	        }else {
	        	return null;
	        }
	      
	    }

	
    public byte[] downloadImage(String fileName){
	    Optional<Image> dbImage = repository.findByName(fileName);
	    byte[] images=ImageUtils.decompressImage(dbImage.get().getImageData());
	    return images;
	    }

    public void saveImage(Image image) {
    	repository.save(image);
    }
    
    public Image findImage(String imagename) {
    	Optional<Image> Image =repository.findByName(imagename);
    	return Image.get();
    }
    
    
    public void DeleteImage(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            // Handle case where entity with given ID doesn't exist
            // Log the exception or return an error response
        } catch (Exception ex) {
            // Handle other exceptions
            ex.printStackTrace(); // Log the exception or return an error response
        }
    }
}
