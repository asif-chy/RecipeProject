package guru.springframework.recipeproject.services;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
	
	public void saveImage(Long id, MultipartFile file);

}
