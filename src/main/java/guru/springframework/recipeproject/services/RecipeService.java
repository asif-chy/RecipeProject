package guru.springframework.recipeproject.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipeproject.command.RecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;

@Service
public interface RecipeService {
	
	public Set<Recipe> getRecipe();
	
	public Recipe findById(Long L);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	
	RecipeCommand findCommandById(Long L);
	
	public void deleteById(Long L);

}
