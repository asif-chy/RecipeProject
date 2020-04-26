package guru.springframework.recipeproject.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipeproject.domain.Recipe;

@Service
public interface RecipeService {
	
	public Set<Recipe> getRecipe();

}
