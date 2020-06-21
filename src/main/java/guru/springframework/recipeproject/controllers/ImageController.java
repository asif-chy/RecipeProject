package guru.springframework.recipeproject.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.recipeproject.command.RecipeCommand;
import guru.springframework.recipeproject.services.ImageService;
import guru.springframework.recipeproject.services.RecipeService;

@Controller
public class ImageController {
	
	private final RecipeService recipeService;
	private final ImageService imageService;
	
	public ImageController(RecipeService recipeService, ImageService imageService) {
		super();
		this.recipeService = recipeService;
		this.imageService = imageService;
	}
	
	
	@GetMapping("recipe/{id}/image")
	public String openImageUploadForm(@PathVariable String id, Model model) {
		
		model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
		
		return "recipe/imageForm";
	}
	
	@PostMapping("recipe/{id}/image")
	public String uploadImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
		
		imageService.saveImage(Long.valueOf(id), file);
		
		return"redirect:/recipe/" + id + "/show";
	}
	
	@GetMapping("recipe/{id}/recipeimage")
	public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException{
		
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
		
		byte[] byteArray = new byte[recipeCommand.getImage().length];
		
		int i = 0;
		
		for(Byte wrapperByte : recipeCommand.getImage()) {
			byteArray[i++] = wrapperByte;
		}
		
		response.setContentType("image/jpeg");
		InputStream is = new ByteArrayInputStream(byteArray);
		IOUtils.copy(is, response.getOutputStream());
		
	}

}
