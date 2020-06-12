package guru.springframework.recipeproject.services;

import guru.springframework.recipeproject.command.IngredientCommand;

public interface IngredientService {
	
	IngredientCommand findByIngredientIdRecipeId(Long recipeId, Long ingredientId);

}
