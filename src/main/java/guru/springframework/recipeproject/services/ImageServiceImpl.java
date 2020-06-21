package guru.springframework.recipeproject.services;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.classic.Logger;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{
	
	private final RecipeRepository recipeRepository;
	
	

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}



	@Override
	public void saveImage(Long id, MultipartFile file) {
		// TODO Auto-generated method stub
		
		try {
			Recipe recipe = recipeRepository.findById(id).get();
			
			Byte[] byteObject = new Byte[file.getBytes().length];
			
			int i = 0;
			
			for(byte b: file.getBytes()) {
				byteObject[i++] = b;
			}
			
			recipe.setImage(byteObject);
			
			recipeRepository.save(recipe);
		}catch(IOException e){
			log.debug("Failed Image Save");
			
			e.printStackTrace();
		}
	}

}
