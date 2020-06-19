package guru.springframework.recipeproject.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;
import guru.springframework.recipeproject.command.IngredientCommand;
import guru.springframework.recipeproject.converters.IngrediantToIngrediantCommand;
import guru.springframework.recipeproject.converters.IngredientCommandToIngredient;
import guru.springframework.recipeproject.domain.Ingredient;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.repositories.RecipeRepository;
import guru.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final IngrediantToIngrediantCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository uomRepository;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(IngrediantToIngrediantCommand ingredientToIngredientCommand,
			RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

		if (!ingredientCommand.isPresent()) {
			log.debug("Recipe Ingredient Not Found: " + ingredientId);
		}

		return ingredientCommand.get();

	}

	// Need to understand the lambda code

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		// TODO Auto-generated method stub

		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

		if (recipeOptional.isPresent()) {

			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngrediant().stream()
					.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

			if (ingredientOptional.isPresent()) {

				Ingredient ingredientFetched = ingredientOptional.get();

				ingredientFetched.setDescription(command.getDescription());
				ingredientFetched.setAmount(command.getAmount());
				ingredientFetched.setUom(uomRepository.findById(command.getUom().getId())
						.orElseThrow(() -> new RuntimeException("UOM Not Found")));
			} else {
				// add new ingredient
				Ingredient createIngredient = ingredientCommandToIngredient.convert(command);
				createIngredient.setRecipe(recipe);
				recipe.addIngrediant(createIngredient);
			}

			Recipe savedRecipe = recipeRepository.save(recipe);

			Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngrediant().stream()
					.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId())).findFirst();

			// Check By Description
			if (!savedIngredientOptional.isPresent()) {

				savedIngredientOptional = savedRecipe.getIngrediant().stream()
						.filter(recipeIngredients -> recipeIngredients.getDescription()
								.equals(command.getDescription()))
						.filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
						.filter(recipeIngredients -> recipeIngredients.getUom().getId()
								.equals(command.getUom().getId()))
						.findFirst();
			}

			return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
		} else {
			return new IngredientCommand();
		}

	}

	public void deleteIngredientById(Long recipeId, Long Id) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		

		if (recipeOptional.isPresent()) {

			Recipe recipe = recipeOptional.get();

			Optional<Ingredient> ingredientOptional = recipe.getIngrediant().stream()
					.filter(ingredient -> ingredient.getId().equals(Id)).findFirst();
			
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientDelete = ingredientOptional.get();
				
				ingredientDelete.setRecipe(null);
				recipe.getIngrediant().remove(ingredientDelete);
				
				recipeRepository.save(recipe);
			}
		}else {
			log.debug("Recipe is not found");
		}
	}

}
