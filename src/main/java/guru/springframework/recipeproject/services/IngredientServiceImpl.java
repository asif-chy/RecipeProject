package guru.springframework.recipeproject.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;
import guru.springframework.recipeproject.command.IngredientCommand;
import guru.springframework.recipeproject.converters.IngrediantToIngrediantCommand;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngrediantToIngrediantCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;

	public IngredientServiceImpl(IngrediantToIngrediantCommand ingredientToIngredientCommand,
			RecipeRepository recipeRepository) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public IngredientCommand findByIngredientIdRecipeId(Long recipeId, Long ingredientId) {
		// TODO Auto-generated method stub

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {
			log.debug("Recipe Not Found: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommand = recipe.getIngrediant().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();
		
		if(!ingredientCommand.isPresent()) {
			log.debug("Recipe Ingredient Not Found: " + ingredientId);
		}
		
		return ingredientCommand.get();
		
	}

}
