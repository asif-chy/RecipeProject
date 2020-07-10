package guru.springframework.recipeproject.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.recipeproject.command.RecipeCommand;
import guru.springframework.recipeproject.converters.RecipeCommandToRecipe;
import guru.springframework.recipeproject.converters.RecipeToRecipeCommand;
import guru.springframework.recipeproject.domain.Recipe;
import guru.springframework.recipeproject.exceptions.NotFoundException;
import guru.springframework.recipeproject.repositories.RecipeRepository;

@Service
public class RecipeServicesImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;

	public RecipeServicesImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
		
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipe() {

		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;

	}

	@Override
	public Recipe findById(Long L) {
		// TODO Auto-generated method stub

		System.out.println("Find By Id Method" + L);
		
		Optional<Recipe> recipeById = recipeRepository.findById(L);

		if (!recipeById.isPresent()) {
			throw new NotFoundException("Recipe By Id Not Found " + L.toString());
		}
		
		Recipe recipe = recipeById.get();
		
		System.out.println("findById executed " + recipe.getId());
		
		return recipeById.get();
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand command) {
		Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		System.out.println("Saved RecipeId:" + savedRecipe.getId());
		return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	@Transactional
	public RecipeCommand findCommandById(Long L) {
		// TODO Auto-generated method stub
		return recipeToRecipeCommand.convert(findById(L));
	}

	@Override
	public void deleteById(Long L) {
		// TODO Auto-generated method stub
		recipeRepository.deleteById(L);
		
	}
	

}
